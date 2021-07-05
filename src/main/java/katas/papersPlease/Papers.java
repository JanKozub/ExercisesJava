package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Entrants require passport\n" +
                "Allow citizens of Arstotzka\n" +
                "Wanted by the State: Katrina Wojcik");

        inspector.receiveBulletin("Allow citizens of Antegria, Impor, Kolechia, Obristan, Republia, United Federation\n" +
                "Wanted by the State: Omid Tjell");

        inspector.receiveBulletin("Foreigners require access permit\n" +
                "Wanted by the State: Ingrid Ramos");

        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of Republia\n" +
                "Wanted by the State: Jakob Fischer");

        inspector.receiveBulletin("Deny citizens of Republia\n" +
                "Entrants require cowpox vaccination\n" +
                "Wanted by the State: Elena Lang\n" +
                "Entrants require certificate of vaccination\n");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: United Federation\n" +
                "DOB: 1916.06.12\n" +
                "SEX: F\n" +
                "ISS: Korista City\n" +
                "ID#: NK4K7-S5B6N\n" +
                "EXP: 1984.08.08\n" +
                "NAME: Costa, Eleanor");

        josef.put("access_permit", "NAME: Costa, Eleanor\n" +
                "NATION: United Federation\n" +
                "ID#: NK4K7-S5B6N\n" +
                "PURPOSE: VISIT\n" +
                "DURATION: 3 MONTHS\n" +
                "HEIGHT: 176.0cm\n" +
                "WEIGHT: 85.0kg\n" +
                "EXP: 1984.07.21");

        josef.put("certificate_of_vaccination", "NAME: Costa, Eleanor\n" +
                "ID#: NK4K7-S5B6N\n" +
                "VACCINES: HPV, polio, cholera");

//        josef.put("diplomatic_authorization", "NATION: United Federation\n" +
//                "NAME: Nityev, Gregor\n" +
//                "ID#: NWCFT-OUE8L\n" +
//                "ACCESS: Arstotzka, Antegria");

        System.out.println(inspector.inspect(josef));
    }
}
