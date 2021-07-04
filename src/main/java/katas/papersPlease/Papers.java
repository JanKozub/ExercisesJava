package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of Antegria\n" +
                "Entrants require HPV vaccination\n" +
                "Wanted by the State: Emily Tjell");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: Obristan\n" +
                "DOB: 1945.08.07\n" +
                "SEX: M\n" +
                "ISS: Lorndaz\n" +
                "ID#: DLNJS-MA7CN\n" +
                "EXP: 1985.06.18\n" +
                "NAME: Schumer, Karl");

        josef.put("access_permit", "NAME: Schumer, Karl\n" +
                "NATION: Obristan\n" +
                "ID#: DLNJS-MA7CN\n" +
                "PURPOSE: TRANSIT\n" +
                "DURATION: 2 DAYS\n" +
                "HEIGHT: 148.0cm\n" +
                "WEIGHT: 45.0kg\n" +
                "EXP: 1984.05.20");

        josef.put("certificate_of_vaccination", "NAME: Schumer, Karl\n" +
                "ID#: DLNJS-MA7CN\n" +
                "VACCINES: HPV, polio, cowpox");

//        josef.put("diplomatic_authorization", "NATION: United Federation\n" +
//                "NAME: Nityev, Gregor\n" +
//                "ID#: NWCFT-OUE8L\n" +
//                "ACCESS: Arstotzka, Antegria");

        System.out.println(inspector.inspect(josef));
    }
}
