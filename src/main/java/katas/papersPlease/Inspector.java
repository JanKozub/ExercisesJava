package katas.papersPlease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector {
    private final ArrayList<String> allowedNations = new ArrayList<>();
    private List<String> wantedCriminals;
    private final Map<String, ArrayList<String>> requiredDocuments = new HashMap<>();
    private Map<String, Document> userDocuments;
    private boolean isNative;
    private String nation;
    private int counter = 0;

    public void receiveBulletin(String bulletin) {
        this.wantedCriminals = new ArrayList<>();
        counter++;

        System.out.println("\nNew day nr." + counter + ": \n");
        for (String info : bulletin.split("\n")) {
            System.out.println(info);
            updateRequiredDocuments(info);
            getNations(info);
            getWantedCriminals(info);
        }
    }

    public String inspect(Map<String, String> person) {
        this.userDocuments = new HashMap<>();
        System.out.println("\nPerson:\n" + person);

        getDocuments(person);

        this.nation = getFieldIfExists("NATION");
        this.isNative = nation.equals("Arstotzka");

        String reason = checkIsWantedCriminal();
        if (reason != null) return reason;

        reason = checkForMismatch();
        if (reason != null) return reason;

        if (!isNative && userDocuments.get("passport") == null)
            return "Entry denied: missing required passport.";

        if (!allowedNations.contains(nation))
            return "Entry denied: citizen of banned nation.";

        reason = isDocumentMissing();
        if (reason != null) return reason;

        reason = anyDocumentExpired();
        if (reason != null) return reason;

        reason = checkVaccine();
        if (reason != null) return reason;

        if (requiredDocuments.values().stream().anyMatch(v -> v.contains("Workers")) &&
                getFieldIfExists("PURPOSE").equals("WORK") &&
                userDocuments.get("work pass") == null)
            return "Entry denied: missing required work pass.";

        if (!isNative)
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private void updateRequiredDocuments(String info) {
        info = info.replace("Citizens of ", "");

        if (info.contains("no longer")) {
            String[] infos = info.split(" no longer require ");
            ArrayList<String> val = requiredDocuments.get(infos[1]);
            val.removeAll(List.of((infos[0].split(", "))));
            requiredDocuments.replace(infos[1], val);
        } else if (info.contains("require")) {
            String[] infos = info.split(" require ");
            requiredDocuments.put(infos[1], new ArrayList<>(List.of((infos[0].split(", ")))));
        }
    }

    private void getNations(String info) {
        if (info.contains("Allow citizens"))
            allowedNations.addAll(Arrays.asList(info.split("citizens of ")[1].split(", ")));
        else if (info.contains("Deny citizens"))
            allowedNations.removeAll(Arrays.asList(info.split("citizens of ")[1].split(", ")));
    }

    private void getWantedCriminals(String info) {
        if (info.contains("Wanted")) {
            String[] infos = info.split(": ")[1].split(", ");
            wantedCriminals.clear();
            for (String i : infos) {
                String[] strings = i.split(" ");
                wantedCriminals.add(strings[1] + ", " + strings[0]);
            }
        }
    }

    private void getDocuments(Map<String, String> person) {
        for (Map.Entry<String, String> entry : person.entrySet())
            userDocuments.put(entry.getKey().replaceAll("_", " "), new Document(entry.getValue()));
    }

    private String checkIsWantedCriminal() {
        if (userDocuments.get("passport") != null)
            if (wantedCriminals.contains(userDocuments.get("passport").getData().get("NAME")))
                return "Detainment: Entrant is a wanted criminal.";
        return null;
    }

    private String checkForMismatch() {
        if (!userDocuments.values().stream().allMatch(d -> {
            if (d.getData().get("ID#") != null)
                return d.getData().get("ID#").equals(getFieldIfExists("ID#"));
            else return true;
        })) return "Detainment: ID number mismatch.";

        if (!userDocuments.values().stream().allMatch(d -> d.getData().get("NAME").equals(getFieldIfExists("NAME"))))
            return "Detainment: name mismatch.";

        if (!userDocuments.values().stream().allMatch(d -> {
            if (d.getData().get("NATION") != null)
                return d.getData().get("NATION").equals(nation);
            else return true;
        })) return "Detainment: nationality mismatch.";

        return null;
    }

    private String isDocumentMissing() {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            String docName = entry.getKey();
            if (docName.contains("vaccination")) docName = "certificate of vaccination";

            if (!userDocuments.containsKey(docName)) {
                if (doesEntryApplyToUser(entry)) {
                    switch (docName) {
                        case "access permit":
                            if (userDocuments.containsKey("grant of asylum")) continue;
                            if (isNative) continue;
                            if (userDocuments.containsKey("diplomatic authorization"))
                                if (isDiplomaticAuthorizationValid()) continue;
                                else return "Entry denied: invalid diplomatic authorization.";
                            if (getFieldIfExists("PURPOSE").equals("WORK") && !userDocuments.containsKey("work pass"))
                                return "Entry denied: missing required work pass.";
                            break;
                        case "work pass":
                            if (!getFieldIfExists("PURPOSE").equals("WORK")) continue;
                            break;
                    }
                    return "Entry denied: missing required " + docName + ".";
                }
            }
        }
        return null;
    }

    private boolean doesEntryApplyToUser(Map.Entry<String, ArrayList<String>> entry) {
        return (!isNative && entry.getValue().contains("Foreigners")) ||
                entry.getValue().stream().anyMatch(f -> f.equals(nation)) ||
                entry.getValue().contains("Entrants");
    }

    private String anyDocumentExpired() {
        for (Map.Entry<String, Document> entry : userDocuments.entrySet()) {
            if (entry.getValue().hasDocumentExpired())
                return "Entry denied: " + entry.getKey() + " expired.";
        }
        return null;
    }

    private String checkVaccine() {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            if (entry.getKey().contains("vaccination")) {
                String vaccine = entry.getKey().replace(" vaccination", "");
                if ((!isNative && entry.getValue().contains("Foreigners")) ||
                        entry.getValue().stream().anyMatch(f -> f.equals(nation)) ||
                        entry.getValue().contains("Entrants")) {
                    if (!Arrays.asList(getFieldIfExists("VACCINES").split(", ")).contains(vaccine)) {
                        return "Entry denied: missing required vaccination.";
                    }
                }
            }
        }
        return null;
    }

    private boolean isDiplomaticAuthorizationValid() {
        String[] countries = getFieldIfExists("ACCESS").split(", ");
        return Arrays.asList(countries).contains("Arstotzka");
    }

    private String getFieldIfExists(String fieldName) {
        for (Document document : userDocuments.values())
            if (document.getData().get(fieldName) != null)
                return document.getData().get(fieldName);
        return "Not found!";
    }

    static class Document {
        private final Map<String, String> data = new HashMap<>();

        public Document(String info) {
            String[] infos = info.split("\n");

            for (String i : infos) {
                String[] strings = i.split(": ");
                data.put(strings[0], strings[1]);
            }
        }

        public Map<String, String> getData() {
            return data;
        }

        public boolean hasDocumentExpired() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String date = getData().get("EXP");
            if (date == null)
                date = "1982.11.25";
            LocalDate time = LocalDate.parse(date, formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }
    }
}
