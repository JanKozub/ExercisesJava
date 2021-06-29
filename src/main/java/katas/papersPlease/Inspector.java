package katas.papersPlease;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Inspector {
    private String[] allowedCitizens;
    private String[] deniedCitizens;
    private final List<String> wantedCriminals = new ArrayList<>();
    private final Map<String, String> requiredDocuments = new HashMap<>();

    public void receiveBulletin(String bulletin) {
        String[] infos = bulletin.split("\n");

        for (String info : infos) {
            System.out.println(info);
            updateRequiredDocuments(info);
            separateNations(info);
            separateNations(info);
            separateNations(info);
            getWantedCriminals(info);
        }
    }

    public String inspect(Map<String, String> person) {
        System.out.println(person);
        Document passport = null;
        Document grantOfAsylum;
        Document accessPermit = null;

        if (person.containsKey("access_permit")) {
            accessPermit = new Document(person.get("access_permit"));

            if (accessPermit.isDocumentExpired())
                return "Entry denied: access permit expired.";

        } else if (requiredDocuments.containsValue("access_permit"))
            return "Entry denied: missing required access permit.";

        if (person.containsKey("passport")) {
            passport = new Document(person.get("passport"));
            if (checkIsWantedCriminal(passport.getName()))
                return "Detainment: Entrant is a wanted criminal.";

            if (passport.isDocumentExpired())
                return "Entry denied: passport expired.";

            if (accessPermit != null) {
                if (!accessPermit.doesFieldMatch(passport, "ID#"))
                    return "Detainment: ID number mismatch.";
                if (!accessPermit.doesFieldMatch(passport, "NATION"))
                    return "Detainment: nationality mismatch.";
            }

            if (!passport.getNation().equals("Arstotzka"))
                if (checkForBannedNation(passport.getNation()) || !checkForAllowedNation(passport.getNation()))
                    return "Entry denied: citizen of banned nation.";

        } else if (requiredDocuments.containsValue("passport"))
            return "Entry denied: missing required passport.";

        if (person.containsKey("grant_of_asylum")) {
            grantOfAsylum = new Document(person.get("grant_of_asylum"));
            if (passport != null) {
                if (checkIsWantedCriminal(passport.getName()))
                    return "Detainment: Entrant is a wanted criminal.";

                if (!grantOfAsylum.doesFieldMatch(passport, "ID#"))
                    return "Detainment: ID number mismatch.";

                if (grantOfAsylum.isDocumentExpired())
                    return "Entry denied: grant of asylum expired.";

                if (checkForBannedNation(passport.getNation()) || !checkForAllowedNation(passport.getNation()))
                    return "Entry denied: citizen of banned nation.";
            }
        } else if (requiredDocuments.containsValue("grant_of_asylum"))
            return "Entry denied: missing required grant of asylum.";

        if (passport != null && !passport.getNation().equals("Arstotzka"))
            return "Cause no trouble.";
        return "Glory to Arstotzka.";
    }

    private boolean checkForBannedNation(String nation) {
        if (deniedCitizens != null) {
            return Arrays.asList(deniedCitizens).contains(nation);
        } else return false;
    }

    private boolean checkForAllowedNation(String nation) {
        if (allowedCitizens != null) {
            return Arrays.asList(allowedCitizens).contains(nation);
        } else return false;
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
        private final Map<String, String> data = new HashMap<>();

        public Document(String info) {
            String[] infos = info.split("\n");

            for (String i : infos) {
                String[] strings = i.split(": ");
                data.put(strings[0], strings[1]);
            }
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

        public boolean isDocumentExpired() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate time = LocalDate.parse(getExpDate(), formatter);
            return time.isBefore(LocalDate.parse("1982-11-22"));
        }

        public boolean doesFieldMatch(Document document, String field) {
            return getData().get(field).equals(document.getData().get(field));
        }
    }
}
