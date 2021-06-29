package katas.papersPlease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Inspector {
    private ArrayList<String> allowedCitizens = new ArrayList<>();
    private ArrayList<String> deniedCitizens = new ArrayList<>();

    public void receiveBulletin(String bulletin) {
        String[] infos = bulletin.split("\n");
        System.out.println(Arrays.toString(infos));
    }

    public String inspect(Map<String, String> person) {
        // Your code here
        return "";
    }


}
