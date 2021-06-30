package katas.papersPlease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector {
    private String[] allowedCitizens;
    private String[] deniedCitizens;
    private List<String> wantedCriminals;
    private Map<String, String> requiredDocuments;
    private Map<String, String> person;
    private ArrayList<Document> documents;
    private Document diplomaticAuthorization;
    private Document passport;
    private Document grantOfAsylum;

    public void receiveBulletin(String bulletin) {
        this.wantedCriminals = new ArrayList<>();
        this.requiredDocuments = new HashMap<>();
        String[] infos = bulletin.split("\n");

        for (String info : infos) {
            System.out.println(info);
            updateRequiredDocuments(info);
            separateNations(info);
            getWantedCriminals(info);
        }
    }

    public String inspect(Map<String, String> person) {
        this.person = person;
        this.documents = new ArrayList<>();
        System.out.println("Person:");
        System.out.println(person);

        this.passport = getDocumentIfExists("passport");
//        Document certificateOfVaccine = getDocumentIfExists("certificate_of_vaccination");
//        Document idCard = getDocumentIfExists("ID_card");
        Document accessPermit = getDocumentIfExists("access_permit");
//        Document workPass = getDocumentIfExists("work_pass");
        this.grantOfAsylum = getDocumentIfExists("grant_of_asylum");
        this.diplomaticAuthorization = getDocumentIfExists("diplomatic_authorization");

        if (passport != null)
            if (checkIsWantedCriminal(passport.getName()))
                return "Detainment: Entrant is a wanted criminal.";

        String document = anyDocumentExpired();
        if (document != null) return document;

        String documentMissing = isDocumentMissing(passport);
        if (documentMissing != null) return documentMissing;

        if (doesNamesMismatch()) return "Detainment: name mismatch";

        if (passport != null) {
            if (accessPermit != null) {
                if (accessPermit.fieldsDontMatch(passport, "ID#"))
                    return "Detainment: ID number mismatch.";
                if (accessPermit.fieldsDontMatch(passport, "NATION"))
                    return "Detainment: nationality mismatch.";
            }

            if (!passport.getNation().equals("Arstotzka"))
                if (checkForBannedNation(passport.getNation()) || isNotAllowedNation(passport.getNation()))
                    return "Entry denied: citizen of banned nation.";
        }

        if (grantOfAsylum != null && passport != null) {
            if (grantOfAsylum.fieldsDontMatch(passport, "ID#"))
                return "Detainment: ID number mismatch.";

            if (checkForBannedNation(passport.getNation()) || isNotAllowedNation(passport.getNation()))
                return "Entry denied: citizen of banned nation.";
        }

        if (passport != null && !passport.getNation().equals("Arstotzka"))
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private boolean doesNamesMismatch() {
        String name = documents.get(0).getName();
        System.out.println(name);
        return !documents.stream().findAny().get().getName().equals(name);
    }

    private Document getDocumentIfExists(String documentName) {
        if (person.containsKey(documentName)) {
            Document document = new Document(documentName.replaceAll("_", " "), person.get(documentName));
            documents.add(document);
            return document;
        } else
            return null;
    }

    private String isDocumentMissing(Document doc) {
        for (Map.Entry<String, String> e : requiredDocuments.entrySet()) {
            if (documents.stream().noneMatch(document -> document.getDocumentName().equals(e.getValue()))) {
                if (doc != null) {
                    String nation = doc.getNation().equals("Arstotzka") ? "Entrants" : "Foreigners";
                    if (e.getKey().equals("Foreigners") && passport == null)
                        return "Entry denied: missing required passport.";
                    if (e.getKey().equals(nation)) {
                        if (e.getValue().equals("access permit") && diplomaticAuthorization != null) {
                            if (!isDiplomaticAuthorizationValid(diplomaticAuthorization)) {
                                return "Entry denied: invalid diplomatic authorization.";
                            }
                        } else if (e.getValue().equals("access permit") && grantOfAsylum == null)
                            return "Entry denied: missing required " + e.getValue() + ".";
                    }
                }
            }
        }
        return null;
    }

    private boolean isDiplomaticAuthorizationValid(Document document) {
        return Arrays.asList(document.getData().get("ACCESS").split(", ")).contains("Arstotzka");
    }

    private String anyDocumentExpired() {
        for (Document document : documents) {
            if (document.hasDocumentExpired())
                return "Entry denied: " + document.getDocumentName() + " expired.";
        }
        return null;
    }

    private boolean checkForBannedNation(String nation) {
        if (deniedCitizens != null) {
            return Arrays.asList(deniedCitizens).contains(nation);
        } else return false;
    }

    private boolean isNotAllowedNation(String nation) {
        if (allowedCitizens != null) {
            return !Arrays.asList(allowedCitizens).contains(nation);
        } else return true;
    }

    private boolean checkIsWantedCriminal(String name) {
        return wantedCriminals.contains(name);
    }

    private void updateRequiredDocuments(String info) {
        if (info.contains("require")) {
            String[] strings = info.split(" require ");
            requiredDocuments.put(strings[0], strings[1]);
        }
    }

    private void separateNations(String info) {
        if (info.contains("Allow citizens")) {
            allowedCitizens = info.split("of ")[1].split(", ");
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
        private final String documentName;
        private final Map<String, String> data = new HashMap<>();

        public Document(String documentName, String info) {
            this.documentName = documentName;
            String[] infos = info.split("\n");

            for (String i : infos) {
                String[] strings = i.split(": ");
                data.put(strings[0], strings[1]);
            }
        }

        public String getDocumentName() {
            return this.documentName;
        }

        public String getName() {
            return getData().get("NAME");
        }

        public String getNation() {
            return getData().get("NATION");
        }

        public String getExpDate() {
            return getData().get("EXP");
        }

        public Map<String, String> getData() {
            return data;
        }

        public boolean hasDocumentExpired() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            String date = getExpDate();
            if (date == null)
                date = "1982.11.25";
            LocalDate time = LocalDate.parse(date, formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }

        public boolean fieldsDontMatch(Document document, String field) {
            return !getData().get(field).equals(document.getData().get(field));
        }
    }
}
