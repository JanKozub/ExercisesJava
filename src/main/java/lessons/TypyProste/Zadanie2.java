package lessons.TypyProste;

import lessons.PetleIInstrukcjeWarunkowe.Zadanie8;

import java.util.Scanner;

public class Zadanie2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbe ktora chcesz zamienic");
        String odczyt = scanner.nextLine();
        System.out.println("Twoja cyfra: " + odczyt);
        System.out.println("Jej binarna reprezentacja: " + Zadanie8.zamienLiczbeNaBinarna(Integer.parseInt(odczyt)));
    }
}
