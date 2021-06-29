package katas.papersPlease;

import java.util.Arrays;
import java.util.Map;

public class Inspector {
    private String[] allowedCitizens;
    private String[] deniedCitizens;

    private String[] wantedCriminals;

    private boolean entrantsRequirePassport = false;

    public void receiveBulletin(String bulletin) {
        String[] infos = bulletin.split("\n");
        System.out.println(Arrays.toString(infos));

        for (String info : infos) {
            if (info.contains("require passport")) {
                if (info.contains("Entrants"))
                    entrantsRequirePassport = true;
            } else {
                if (info.contains("Allow citizens")) {
                    allowedCitizens = info.split("of ")[1].split(", ");
                } else {
                    if (info.contains("Deny citizens")) {
                        deniedCitizens = info.split("of ")[1].split(", ");
                    } else {
                        wantedCriminals = info.split(": ")[1].split(", ");
                    }
                }
            }
        }
        System.out.println(Arrays.toString(wantedCriminals));
    }

    public String inspect(Map<String, String> person) {
        // Your code here
        return "";
    }


}
