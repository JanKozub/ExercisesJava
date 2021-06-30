package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Foreigners require access permit\n" +
                "Wanted by the State: Anton Quinn");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: United Federation\n" +
                "DOB: 1956.03.10\n" +
                "SEX: F\n" +
                "ISS: Shingleton\n" +
                "ID#: EDXX5-J4CVH\n" +
                "EXP: 1983.07.25\n" +
                "NAME: Anderson, Ekaterina");

        josef.put("diplomatic_authorization", "NATION: United Federation\n" +
                "NAME: Anderson, Ekaterina\n" +
                "ID#: EDXX5-J4CVH\n" +
                "ACCESS: Arstotzka, Antegria, Impor");
        System.out.println(inspector.inspect(josef));
    }
}
