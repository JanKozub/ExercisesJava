package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of United Federation\n" +
                "Foreigners require polio vaccination\n" +
                "Wanted by the State: Pablo Schumer");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: Obristan\n" +
                "DOB: 1961.06.15\n" +
                "SEX: F\n" +
                "ISS: Mergerous\n" +
                "ID#: WCPSA-RHTY9\n" +
                "EXP: 1984.12.01\n" +
                "NAME: Muller, Gabrielle");

        josef.put("access_permit", "NAME: Muller, Gabrielle\n" +
                "NATION: Obristan\n" +
                "ID#: WCPSA-RHTY9\n" +
                "PURPOSE: VISIT\n" +
                "DURATION: 3 MONTHS\n" +
                "HEIGHT: 185.0cm\n" +
                "WEIGHT: 97.0kg\n" +
                "EXP: 1983.08.05");

//        josef.put("certificate_of_vaccination", "NAME: Mikkelson, Eduardo\n" +
//                "ID#: H94DC-XHR5W\n" +
//                "VACCINES: hepatitis B, cowpox, tetanus");

//        josef.put("diplomatic_authorization", "NATION: United Federation\n" +
//                "NAME: Nityev, Gregor\n" +
//                "ID#: NWCFT-OUE8L\n" +
//                "ACCESS: Arstotzka, Antegria");

        System.out.println(inspector.inspect(josef));
    }
}
