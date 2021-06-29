package lessons.TypyProste;

import lessons.PetleIInstrukcjeWarunkowe.Zadanie7;
import lessons.PetleIInstrukcjeWarunkowe.Zadanie9;

import java.util.Scanner;

public class Zadanie4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        test(scanner);

        System.out.println("Wpisz slowo");
        String odczyt = scanner.nextLine();
        System.out.println("Oto informacje na temat twojego slowa");
        System.out.println("Dlugosc: " + odczyt.length());
        System.out.println("Czy jest to palindrom?");
        if (Zadanie9.sprawdzCzyWyrazJestPalinondromem(odczyt) == true) System.out.println("tak");else System.out.println("nie");
        System.out.println("Otwrocona wartosc:");
        Zadanie7.odwrocSlowo(odczyt);
    }

    public static void test(Scanner scanner) {
        scanner.hasNext();
    }
}
