package katas.papersPlease;

import java.util.HashMap;
import java.util.Map;

public class Papers {
    public static void main(String[] args) {
        Inspector inspector = new Inspector();
        inspector.receiveBulletin("Citizens of Arstotzka require ID card\n" +
                "Deny citizens of United Federation\n" +
                "Entrants require polio vaccination\n" +
                "Wanted by the State: Abdullah Medici");

        Map<String, String> josef = new HashMap<>();

        josef.put("passport", "NATION: Kolechia\n" +
                "DOB: 1927.04.22\n" +
                "SEX: M\n" +
                "ISS: Yurko City\n" +
                "ID#: VIIMP-TYYXC\n" +
                "EXP: 1984.08.04\n" +
                "NAME: Reichenbach, Tomasz");

        josef.put("access_permit", "NAME: Reichenbach, Tomasz\n" +
                "NATION: Kolechia\n" +
                "ID#: VIIMP-TYYXC\n" +
                "PURPOSE: VISIT\n" +
                "DURATION: 1 MONTH\n" +
                "HEIGHT: 168.0cm\n" +
                "WEIGHT: 73.0kg\n" +
                "EXP: 1983.01.31");

        josef.put("certificate_of_vaccination", "NAME: Reichenbach, Tomasz\n" +
                "ID#: VIIMP-TYYXC\n" +
                "VACCINES: HPV, typhus, cholera");

        System.out.println(inspector.inspect(josef));
    }
}
