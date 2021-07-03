package katas.papersPlease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector {
    private ArrayList<String> allowedCitizens;
    private String[] deniedCitizens;
    private List<String> wantedCriminals;
    private Map<String, String> requiredDocuments;
    private Map<String, Document> documents;
    private boolean isNative;
    private ArrayList<String> requiredVaccines;
    private ArrayList<String> vaccineNations;
    private int counter = 0;

    public void receiveBulletin(String bulletin) {
        this.allowedCitizens = new ArrayList<>();
        this.wantedCriminals = new ArrayList<>();
        this.requiredDocuments = new HashMap<>();
        this.requiredVaccines = new ArrayList<>();
        this.vaccineNations = new ArrayList<>();
        counter++;
        String[] infos = bulletin.split("\n");

        System.out.println("\nNew day nr." + counter + ": \n");
        for (String info : infos) {
            System.out.println(info);
            updateRequiredDocuments(info);
            separateNations(info);
            getWantedCriminals(info);
        }
    }

    public String inspect(Map<String, String> person) {
        this.documents = new HashMap<>();
        System.out.println("\nPerson:\n" + person);

        for (Map.Entry<String, String> entry : person.entrySet()) {
            Document document = new Document(entry.getValue());
            documents.put(entry.getKey().replaceAll("_", " "), document);
        }

        if (requiredDocuments.containsValue("ID card"))
            requiredDocuments.put("Foreigners", "access permit");

        this.isNative = getFieldIfExists("NATION").equals("Arstotzka");

        String y = checkIsWantedCriminal();
        if (y != null) return y;

        String x = checkForMismatch();
        if (x != null) return x;

        if (!isNative && documents.get("passport") == null) return "Entry denied: missing required passport.";

        if (checkForBannedNation(getFieldIfExists("NATION")))
            return "Entry denied: citizen of banned nation.";

        if (!isAllowedNation(getFieldIfExists("NATION")))
            return "Entry denied: citizen of banned nation.";

        String documentMissing = isDocumentMissing();
        if (documentMissing != null) return documentMissing;

        String document = anyDocumentExpired();
        if (document != null) return document;

        if (!isNative)
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private String checkForMismatch() {
        if (!documents.values().stream().allMatch(d -> {
            if (d.getData().get("ID#") != null)
                return d.getData().get("ID#").equals(getFieldIfExists("ID#"));
            else return true;
        })) return "Detainment: ID number mismatch.";

        if (!documents.values().stream().allMatch(d -> d.getData().get("NAME").equals(getFieldIfExists("NAME"))))
            return "Detainment: name mismatch.";

        if (!documents.values().stream().allMatch(d -> {
            if (d.getData().get("NATION") != null)
                return d.getData().get("NATION").equals(getFieldIfExists("NATION"));
            else return true;
        })) return "Detainment: nationality mismatch.";

        return null;
    }

    private String isDocumentMissing() {
        for (Map.Entry<String, String> reqDoc : requiredDocuments.entrySet()) {
            if (!documents.containsKey(reqDoc.getValue())) {
                if (!(isNative && reqDoc.getKey().equals("Entrants"))) {
                    switch (reqDoc.getValue()) {
                        case "access permit":
                            if (documents.containsKey("grant of asylum")) continue;
                            if (isNative) continue;
                            if (documents.containsKey("diplomatic authorization"))
                                if (isDiplomaticAuthorizationValid()) continue;
                                else return "Entry denied: invalid diplomatic authorization.";
                            break;
                        case "ID card":
                            if (!isNative) continue;
                            break;
                    }

                    if (reqDoc.getValue().contains("vaccination")) {
                        if (documents.containsKey("certificate of vaccination") &&
                                isVaccinationInvalid() &&
                                isVaccineNation())
                            return "Entry denied: missing required vaccination.";
                    } else
                        return "Entry denied: missing required " + reqDoc.getValue() + ".";
                }
            }
        }
        return null;
    }

    private boolean isVaccineNation() {
        if (vaccineNations.isEmpty())
            return true;
        else return vaccineNations.contains(getFieldIfExists("NATION"));
    }

    private boolean isVaccinationInvalid() {
        String[] vaccines = documents.get("certificate of vaccination").getData().get("VACCINES").split(", ");
        return !Arrays.asList(vaccines).containsAll(requiredVaccines);
    }

    private boolean isDiplomaticAuthorizationValid() {
        String[] countries = documents.get("diplomatic authorization").getData().get("ACCESS").split(", ");
        return Arrays.asList(countries).contains("Arstotzka");
    }

    private String getFieldIfExists(String fieldName) {
        for (Document document : documents.values())
            if (document.getData().get(fieldName) != null)
                return document.getData().get(fieldName);
        return "Not found!";
    }

    private String anyDocumentExpired() {
        for (Map.Entry<String, Document> entry : documents.entrySet()) {
            if (entry.getValue().hasDocumentExpired())
                return "Entry denied: " + entry.getKey() + " expired.";
        }
        return null;
    }

    private boolean checkForBannedNation(String nation) {
        if (deniedCitizens != null) {
            return Arrays.asList(deniedCitizens).contains(nation);
        } else return false;
    }

    private boolean isAllowedNation(String nation) {
        if (!allowedCitizens.isEmpty())
            return allowedCitizens.contains(nation);
        return true;
    }

    private String checkIsWantedCriminal() {
        if (documents.get("passport") != null)
            if (wantedCriminals.contains(documents.get("passport").getData().get("NAME")))
                return "Detainment: Entrant is a wanted criminal.";
        return null;
    }

    private void updateRequiredDocuments(String info) {
        if (info.contains("require")) {
            String[] strings = info.split(" require ");
            requiredDocuments.put(strings[0], strings[1]);
            if (strings[1].contains("vaccination")) {
                requiredVaccines.add(strings[1].split(" ")[0]);
                if (strings[0].contains("Citizens"))
                    vaccineNations.addAll(Arrays.asList(strings[0].split("of ")[1].split(", ")));
            }
        }
    }

    private void separateNations(String info) {
        if (info.contains("Allow citizens")) {
            allowedCitizens.addAll(List.of(info.split("of ")[1].split(", ")));
            allowedCitizens.add("Arstotzka");
        } else {
            if (info.contains("Deny citizens")) {
                deniedCitizens = info.split("of ")[1].split(", ");
            }
        }
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
