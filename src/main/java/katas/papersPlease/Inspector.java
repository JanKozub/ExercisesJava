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

        Document passport = getDocumentIfExists("passport");
//        Document certificateOfVaccine = getDocumentIfExists("certificate_of_vaccination");
//        Document idCard = getDocumentIfExists("ID_card");
        Document accessPermit = getDocumentIfExists("access_permit");
//        Document workPass = getDocumentIfExists("work_pass");
        Document grantOfAsylum = getDocumentIfExists("grant_of_asylum");
//        Document diplomaticAuthorization = getDocumentIfExists("diplomatic_authorization");

        for (Map.Entry<String, String> e : requiredDocuments.entrySet()) {
            if (documents.stream().noneMatch(document -> document.getDocumentName().equals(e.getValue()))) {
                assert passport != null;
                String nation = passport.getNation().equals("Arstotzka") ? "Entrants" : "Foreigners";
                if (e.getKey().equals(nation))
                    return "Entry denied: missing required " + e.getValue() + ".";
            }
        }

        if (passport != null) {
            if (checkIsWantedCriminal(passport.getName()))
                return "Detainment: Entrant is a wanted criminal.";

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
            if (checkIsWantedCriminal(passport.getName()))
                return "Detainment: Entrant is a wanted criminal.";

            if (grantOfAsylum.fieldsDontMatch(passport, "ID#"))
                return "Detainment: ID number mismatch.";

            if (checkForBannedNation(passport.getNation()) || isNotAllowedNation(passport.getNation()))
                return "Entry denied: citizen of banned nation.";
        }

        for (Document document : documents) {
            if (document.hasDocumentExpired())
                return "Entry denied: " + document.getDocumentName() + " expired.";
        }

        if (passport != null && !passport.getNation().equals("Arstotzka"))
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private Document getDocumentIfExists(String documentName) {
        if (person.containsKey(documentName)) {
            Document document = new Document(documentName.replaceAll("_", " "), person.get(documentName));
            documents.add(document);
            return document;
        } else
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
            LocalDate time = LocalDate.parse(getExpDate(), formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }

        public boolean fieldsDontMatch(Document document, String field) {
            return !getData().get(field).equals(document.getData().get(field));
        }
    }
}
