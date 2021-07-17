package katas.papersPleaseNew;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector2 {
    private final ArrayList<String> allowedNations = new ArrayList<>();
    private final List<String> wantedCriminals = new ArrayList<>();
    private final Map<String, ArrayList<String>> requiredDocuments = new HashMap<>();
    private int dayNumber = 0;

    public void receiveBulletin(String bulletin) {
        wantedCriminals.clear();
        dayNumber++;

        System.out.println("\nNew day nr." + dayNumber + ": \n");
        for (String info : bulletin.split("\n")) {
            System.out.println(info);
            updateRequiredDocuments(info);
            getNations(info);
            getWantedCriminals(info);
        }
        System.out.println(requiredDocuments);
    }

    public String inspect(Map<String, String> person) {
        System.out.println("\nPerson:\n" + person);

        UserData userData = new UserData(person);

        String reason = checkIsWantedCriminal(userData);
        if (reason != null) return reason;

        reason = checkForMismatch(userData);
        if (reason != null) return reason;

        if (!userData.isNative() && userData.hasDocument("passport"))
            return "Entry denied: missing required passport.";

        if (!allowedNations.contains(userData.getNation()))
            return "Entry denied: citizen of banned nation.";

        reason = isDocumentMissing(userData);
        if (reason != null) return reason;

        reason = anyDocumentExpired(userData);
        if (reason != null) return reason;

        reason = checkVaccine(userData);
        if (reason != null) return reason;

        if (requiredDocuments.values().stream().anyMatch(v -> v.contains("Workers")) &&
                userData.containsFieldWithValue("PURPOSE", "WORK") &&
                userData.hasDocument("work pass"))
            return "Entry denied: missing required work pass.";

        if (!userData.isNative())
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

    private String checkIsWantedCriminal(UserData userData) {
        if (wantedCriminals.contains(userData.getName()))
            return "Detainment: Entrant is a wanted criminal.";
        return null;
    }

    private String checkForMismatch(UserData userData) {
        if (userData.allFieldsMatch("ID#"))
            return "Detainment: ID number mismatch.";

        if (userData.allFieldsMatch("NAME"))
            return "Detainment: name mismatch.";

        if (userData.allFieldsMatch("NATION"))
            return "Detainment: nationality mismatch.";

        return null;
    }

    private String isDocumentMissing(UserData userData) {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            String docName = entry.getKey();
            if (docName.contains("vaccination")) docName = "certificate of vaccination";

            if (!userData.hasDocument(docName) && userData.doesEntryApplyToUser(entry.getValue())) {
                switch (docName) {
                    case "access permit":
                        if (userData.hasDocument("grant of asylum")) continue;
                        if (userData.isNative()) continue;
                        if (userData.hasDocument("diplomatic authorization"))
                            if (isDiplomaticAuthorizationValid(userData)) continue;
                            else return "Entry denied: invalid diplomatic authorization.";
                        if (userData.containsFieldWithValue("PURPOSE", "WORK")
                                && !userData.hasDocument("work pass"))
                            return "Entry denied: missing required work pass.";
                        break;
                    case "work pass":
                        if (!userData.containsFieldWithValue("PURPOSE", "WORK")) continue;
                        break;
                }
                return "Entry denied: missing required " + docName + ".";
            }
        }
        return null;
    }

    private String anyDocumentExpired(UserData userData) {
        return userData.documents.entrySet().stream()
                .filter(d -> d.getValue().hasDocumentExpired())
                .findAny()
                .map(d -> "Entry denied: " + d.getKey() + " expired.")
                .orElse(null);
    }

    private String checkVaccine(UserData userData) {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            String key = entry.getKey();
            if (key.contains("vaccination")) {
                String vaccine = key.replace(" vaccination", "");
                if (userData.doesEntryApplyToUser(entry.getValue())) {
                    if (!Arrays.asList(userData.getFieldFromAnyDocument("VACCINES")
                            .map(s -> s.split(", "))
                            .orElse(new String[0]))
                            .contains(vaccine)) {
                        return "Entry denied: missing required vaccination.";
                    }
                }
            }
        }
        return null;
    }

    private boolean isDiplomaticAuthorizationValid(UserData userData) {
        String[] countries = userData.getFieldFromAnyDocument("ACCESS")
                .map(s -> s.split(", "))
                .orElse(new String[0]);
        return Arrays.asList(countries).contains("Arstotzka");
    }

    static class UserData {
        private final Map<String, Document> documents = new HashMap<>();
        private final boolean isNative;
        private final String nation;

        public UserData(Map<String, String> person) {
            for (Map.Entry<String, String> entry : person.entrySet())
                documents.put(entry.getKey().replaceAll("_", " "), new Document(entry.getValue()));

            this.nation = getFieldFromAnyDocument("NATION").orElse("");
            this.isNative = nation.equals("Arstotzka");
        }

        public Document getDocument(String documentName) {
            return documents.get(documentName);
        }

        public boolean hasDocument(String documentName) {
            return documents.containsKey(documentName);
        }

        public boolean isNative() {
            return isNative;
        }

        public String getNation() {
            return nation;
        }

        public String getName() {
            return getFieldFromAnyDocument("NAME").orElse("");
        }

        private Optional<String> getFieldFromAnyDocument(String fieldName) {
            return documents.values().stream()
                    .map(d -> d.getField(fieldName))
                    .filter(Objects::nonNull)
                    .findAny();
        }

        private boolean containsFieldWithValue(String fieldName, String value) {
            return getFieldFromAnyDocument(fieldName).orElse("").equals(value);
        }

        private boolean allFieldsMatch(String field) {
            return documents.values().stream()
                    .map(d -> d.getField(field))
                    .filter(Objects::nonNull)
                    .distinct().count() != 1;
        }

        private boolean doesEntryApplyToUser(List<String> entry) {
            return (!isNative && entry.contains("Foreigners")) ||
                    entry.contains(nation) ||
                    entry.contains("Entrants");
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

        public String getField(String field) {
            return data.get(field);
        }

        public boolean hasDocumentExpired() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String date = getField("EXP");
            if (date == null)
                date = "1982.11.25";
            LocalDate time = LocalDate.parse(date, formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }
    }
}
