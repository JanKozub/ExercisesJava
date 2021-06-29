package lessons.TypyProste;

import lessons.PetleIInstrukcjeWarunkowe.Zadanie11;
import lessons.PetleIInstrukcjeWarunkowe.Zadanie12;

import java.util.Scanner;

public class Zadanie3 {

    public static int[]pobierzLiczbyOdUzytkownika(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj dlugosc tablicy");
        String dlugoscTablicy = scanner.nextLine();
        int[]tablica = new int[Integer.parseInt(dlugoscTablicy)];
        int licznik=0;
        for (int i = 0; i <= Integer.parseInt(dlugoscTablicy)-1;i++) {
            System.out.println("Podaj " + (i+1) + " cyfre");
            String odczyt = scanner.nextLine();
            tablica[licznik] = Integer.parseInt(odczyt);
            licznik++;
        }
        System.out.println("Oto twoja tablica przed sortowaniem: ");
        wyswietlTablice(tablica);
        System.out.println("");
        System.out.println("A to twoja tablica po sortowaniu: ");
        return tablica;
    }

    public static void wyswietlTablice(int[] tablica){
        for (int i = 0; i < tablica.length; i++) {
            System.out.print(tablica[i]);
        }
    }

    public static void main(String[] args) {
        System.out.println(Zadanie11.zamienTabliceNaString(Zadanie12.posortujTablice(pobierzLiczbyOdUzytkownika())));
    }
}
