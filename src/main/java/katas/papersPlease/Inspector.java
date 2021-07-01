package katas.papersPlease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector {
    private ArrayList<String> allowedCitizens;
    private String[] deniedCitizens;
    private List<String> wantedCriminals;
    private Map<String, String> requiredDocuments;
    private Map<String, String> person;
    private ArrayList<Document> documents;
    private Document passport;
    private Document grantOfAsylum;
    private Document diplomaticAuthorization;

    public void receiveBulletin(String bulletin) {
        this.wantedCriminals = new ArrayList<>();
        this.requiredDocuments = new HashMap<>();
        this.allowedCitizens = new ArrayList<>();
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
        System.out.println(" ");
        System.out.println("Person:");
        System.out.println(person);

        for (Map.Entry<String, String> entry : person.entrySet()) {
            Document document = new Document(entry.getKey().replaceAll("_", " "), entry.getValue());
            switch (document.getDocumentName()) {
                case "passport":
                    passport = document;
                    break;
                case "grant of asylum":
                    grantOfAsylum = document;
                    break;
                case "diplomatic authorization":
                    diplomaticAuthorization = document;
                    break;
            }
            documents.add(new Document(entry.getKey(), entry.getValue()));
        }

        String y = checkIsWantedCriminal();
        if (y != null) return y;

        String x = checkForMismatch();
        if (x != null) return x;

        String document = anyDocumentExpired();
        if (document != null) return document;

        String documentMissing = isDocumentMissing();
        if (documentMissing != null) return documentMissing;

        if (checkForBannedNation(getNation()))
            return "Entry denied: citizen of banned nation.";

        if (!isAllowedNation(getNation()))
            return "Entry denied: citizen of banned nation.";

        if (!getEntrant().equals("Entrants"))
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private String checkForMismatch() {
        if (!documents.stream().allMatch(d -> {
            if (d.getId() != null) return d.getId().equals(documents.get(0).getId());
            else return true;
        })) return "Detainment: ID number mismatch.";

        if (!documents.stream().allMatch(d -> d.getName().equals(documents.get(0).getName())))
            return "Detainment: name mismatch.";

        if (!documents.stream().allMatch(d -> d.getNation().equals(documents.get(0).getNation())))
            return "Detainment: nationality mismatch.";
        return null;
    }

    private String isDocumentMissing() {
        if (getEntrant().equals("Foreigners") && passport == null)
            return "Entry denied: missing required passport.";
        for (Map.Entry<String, String> entry : requiredDocuments.entrySet()) {
            System.out.println(entry.getValue());
            if (documents.stream().noneMatch(doc -> doc.getDocumentName().equals(entry.getValue())))
                if (getEntrant().equals(entry.getKey())) {
                    if (entry.getValue().equals("access permit")
                            && diplomaticAuthorization != null
                            && !isDiplomaticAuthorizationValid()) {
                        return "Entry denied: invalid diplomatic authorization.";
                    }
                    return "Entry denied: missing required " + entry.getValue() + ".";
                }
        }
        return null;
    }

    private String getNation() {
        return documents.get(0).getNation();
    }

    private String getEntrant() {
        return documents.get(0).getNation().equals("Arstotzka") ? "Entrants" : "Foreigners";
    }

    private boolean isDiplomaticAuthorizationValid() {
        return Arrays.asList(diplomaticAuthorization.getData().get("ACCESS").split(", ")).contains("Arstotzka");
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

    private boolean isAllowedNation(String nation) {
        return allowedCitizens.contains(nation);
    }

    private String checkIsWantedCriminal() {
        if (passport != null)
            if (wantedCriminals.contains(passport.getName()))
                return "Detainment: Entrant is a wanted criminal.";
        return null;
    }

    private void updateRequiredDocuments(String info) {
        if (info.contains("require")) {
            String[] strings = info.split(" require ");
            requiredDocuments.put(strings[0], strings[1]);
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

        public String getId() {
            return getData().get("ID#");
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
    }
}
