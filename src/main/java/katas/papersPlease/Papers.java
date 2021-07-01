package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Entrants require passport\n" +
                "Allow citizens of Arstotzka\n" +
                "Wanted by the State: Cassandra Karnov");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: Arstotzka\n" +
                "DOB: 1933.07.24\n" +
                "SEX: F\n" +
                "ISS: Orvech Vonor\n" +
                "ID#: BOGXY-FVJAU\n" +
                "EXP: 1985.05.12\n" +
                "NAME: Babayev, Daniela");

        System.out.println(inspector.inspect(josef));
    }
}
