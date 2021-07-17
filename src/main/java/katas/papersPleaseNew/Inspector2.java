package katas.papersPleaseNew;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector2 {
    public static final String FIELD_ID = "ID#";
    public static final String FIELD_NAME = "NAME";
    public static final String FIELD_NATION = "NATION";
    public static final String FIELD_PURPOSE = "PURPOSE";
    public static final String FIELD_VACCINES = "VACCINES";
    public static final String FIELD_ACCESS = "ACCESS";
    public static final String FIELD_EXPIRED = "EXP";

    public static final String VALUE_WORK = "WORK";
    public static final String VALUE_ENTRANTS = "Entrants";
    public static final String VALUE_FOREIGNERS = "Foreigners";

    public static final String DOC_PASSPORT = "passport";
    public static final String DOC_CERTIFICATE_OF_VACCINATION = "certificate of vaccination";
    public static final String DOC_ACCESS_PERMIT = "access permit";
    public static final String DOC_GRANT_OF_ASYLUM = "grant of asylum";
    public static final String DOC_DIPLOMATIC_AUTHORIZATION = "diplomatic authorization";
    public static final String DOC_WORK_PASS = "work pass";

    public static final String HOME_COUNTRY = "Arstotzka";

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

        String reason = checkForWantedCriminal(userData);
        if (reason != null) return reason;

        reason = checkForMismatch(userData);
        if (reason != null) return reason;

        reason = checkForPassport(userData);
        if (reason != null) return reason;

        reason = checkForNation(userData);
        if (reason != null) return reason;

        reason = checkForMissingDocument(userData);
        if (reason != null) return reason;

        reason = checkForExpiredDocument(userData);
        if (reason != null) return reason;

        reason = checkForVaccine(userData);
        if (reason != null) return reason;

        reason = checkForWorkpass(userData);
        if (reason != null) return reason;

        if (!userData.isNative())
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private String checkForWantedCriminal(UserData userData) {
        if (wantedCriminals.contains(userData.getName()))
            return "Detainment: Entrant is a wanted criminal.";
        return null;
    }

    private String checkForMismatch(UserData userData) {
        if (userData.allFieldsMatch(FIELD_ID))
            return "Detainment: ID number mismatch.";

        if (userData.allFieldsMatch(FIELD_NAME))
            return "Detainment: name mismatch.";

        if (userData.allFieldsMatch(FIELD_NATION))
            return "Detainment: nationality mismatch.";

        return null;
    }

    private String checkForPassport(UserData userData) {
        if (!userData.isNative() && userData.hasDocument(DOC_PASSPORT))
            return "Entry denied: missing required passport.";
        return null;
    }

    private String checkForNation(UserData userData) {
        if (!allowedNations.contains(userData.getNation()))
            return "Entry denied: citizen of banned nation.";
        return null;
    }

    private String checkForMissingDocument(UserData userData) {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            String docName = entry.getKey();
            if (docName.contains("vaccination")) docName = DOC_CERTIFICATE_OF_VACCINATION;

            if (!userData.hasDocument(docName) && userData.doesEntryApplyToUser(entry.getValue())) {
                switch (docName) {
                    case DOC_ACCESS_PERMIT:
                        if (userData.isNative()) continue;
                        if (userData.hasDocument(DOC_GRANT_OF_ASYLUM)) continue;
                        if (userData.hasDocument(DOC_DIPLOMATIC_AUTHORIZATION))
                            if (isDiplomaticAuthorizationValid(userData)) continue;
                            else return "Entry denied: invalid diplomatic authorization.";
                        if (userData.containsFieldWithValue(FIELD_PURPOSE, VALUE_WORK)
                                && !userData.hasDocument(DOC_WORK_PASS))
                            return "Entry denied: missing required work pass.";
                        break;
                    case DOC_WORK_PASS:
                        if (!userData.containsFieldWithValue(FIELD_PURPOSE, VALUE_WORK)) continue;
                        break;
                }
                return "Entry denied: missing required " + docName + ".";
            }
        }
        return null;
    }

    private String checkForExpiredDocument(UserData userData) {
        return userData.getDocuments().stream()
                .filter(Document::hasDocumentExpired)
                .findAny()
                .map(d -> "Entry denied: " + d.getName() + " expired.")
                .orElse(null);
    }

    private String checkForVaccine(UserData userData) {
        for (Map.Entry<String, ArrayList<String>> entry : requiredDocuments.entrySet()) {
            String key = entry.getKey();
            if (key.contains("vaccination")) {
                String vaccine = key.replace(" vaccination", "");
                if (userData.doesEntryApplyToUser(entry.getValue())) {
                    if (!userData.getFieldFromAnyDocumentAsList(FIELD_VACCINES).contains(vaccine)) {
                        return "Entry denied: missing required vaccination.";
                    }
                }
            }
        }
        return null;
    }

    private String checkForWorkpass(UserData userData) {
        if (requiredDocuments.values().stream().anyMatch(v -> v.contains("Workers")) &&
                userData.containsFieldWithValue(FIELD_PURPOSE, VALUE_WORK) &&
                userData.hasDocument(DOC_WORK_PASS))
            return "Entry denied: missing required work pass.";
        return null;
    }

    private void updateRequiredDocuments(String info) {
        info = info.replace("Citizens of ", "");

        if (info.contains("no longer")) {
            String[] infos = info.split(" no longer require ");
            requiredDocuments.get(infos[1]).removeAll(List.of((infos[0].split(", "))));
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

    private boolean isDiplomaticAuthorizationValid(UserData userData) {
        Collection<String> countries = userData.getFieldFromAnyDocumentAsList(FIELD_ACCESS);
        return countries.contains(HOME_COUNTRY);
    }

    static class UserData {
        private final Map<String, Document> documents = new HashMap<>();
        private final boolean isNative;
        private final String nation;
        private final String name;

        public UserData(Map<String, String> person) {
            for (Map.Entry<String, String> entry : person.entrySet()) {
                String name = entry.getKey().replaceAll("_", " ");
                documents.put(name, new Document(name, entry.getValue()));
            }

            this.nation = getFieldFromAnyDocument(FIELD_NATION).orElse("");
            this.isNative = nation.equals(HOME_COUNTRY);
            this.name = getFieldFromAnyDocument(FIELD_NAME).orElse("");
        }

        public Collection<Document> getDocuments() {
            return documents.values();
        }

        public String getName() {
            return name;
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

        private Optional<String> getFieldFromAnyDocument(String fieldName) {
            return documents.values().stream()
                    .map(d -> d.getField(fieldName))
                    .filter(Objects::nonNull)
                    .findAny();
        }

        @SuppressWarnings("SameParameterValue")
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
            return (!isNative && entry.contains(VALUE_FOREIGNERS)) ||
                    entry.contains(nation) ||
                    entry.contains(VALUE_ENTRANTS);
        }

        private Collection<String> getFieldFromAnyDocumentAsList(String fieldName) {
            return getFieldFromAnyDocument(fieldName)
                    .map(s -> s.split(", "))
                    .map(Set::of)
                    .orElse(Collections.emptySet());
        }
    }

    static class Document {
        private final String name;
        private final Map<String, String> data = new HashMap<>();

        public Document(String name, String info) {
            this.name = name;
            String[] infos = info.split("\n");

            for (String i : infos) {
                String[] strings = i.split(": ");
                data.put(strings[0], strings[1]);
            }
        }

        public String getName() {
            return name;
        }

        public String getField(String field) {
            return data.get(field);
        }

        public boolean hasDocumentExpired() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String date = data.getOrDefault(FIELD_EXPIRED, "1982.11.25");
            LocalDate time = LocalDate.parse(date, formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }
    }
}
