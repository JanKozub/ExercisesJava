package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of Republia\n" +
                "Citizens of Antegria, Impor, Kolechia require polio vaccination\n" +
                "Wanted by the State: Otto Sousa");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: United Federation\n" +
                "DOB: 1931.07.18\n" +
                "SEX: M\n" +
                "ISS: Korista City\n" +
                "ID#: VS4LO-XGLS6\n" +
                "EXP: 1985.09.19\n" +
                "NAME: Rosenfeld, Damian");

        josef.put("access_permit","NAME: Rosenfeld, Damian\n" +
                "NATION: United Federation\n" +
                "ID#: VS4LO-XGLS6\n" +
                "PURPOSE: IMMIGRATE\n" +
                "DURATION: FOREVER\n" +
                "HEIGHT: 161.0cm\n" +
                "WEIGHT: 64.0kg\n" +
                "EXP: 1984.04.09");
//
//        josef.put("certificate_of_vaccination", "NAME: DeGraff, Christoph\n" +
//                "ID#: FQT1J-AZ5NS\n" +
//                "VACCINES: polio, hepatitis B, tuberculosis");

//        josef.put("diplomatic_authorization", "NATION: Republia\n" +
//                "NAME: Kierkgaard, Viktor\n" +
//                "ID#: HX5XN-KSBGE\n" +
//                "ACCESS: Antegria, Impor, Kolechia");

        System.out.println(inspector.inspect(josef));
    }
}
