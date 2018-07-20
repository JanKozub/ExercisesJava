package ObiektyiPakiety.Zadanie2;

import java.util.Scanner;

public class Obliczenia {

    public static double obliczPole(double wysokosc, double szerokosc){
            double wynik = wysokosc*szerokosc;

            return wynik;
    }
    public static double obliczObwod(double wysokosc, double szerokosc){
            double wynik = (wysokosc*2) + (szerokosc*2);

            return wynik;
    }
    public static double obliczPrzekatna(double wysokosc, double szerokosc){
        double wynik = Math.sqrt((Math.pow(wysokosc,2)+Math.pow(szerokosc,2)));

        return wynik;
    }
    public static void main(String[] args) {
        System.out.println("Wybierz co chcesz obliczyc:");
        System.out.println("1.Pole");
        System.out.println("2.Obwod");
        System.out.println("3.Przekatna");
        Scanner scanner = new Scanner(System.in);
        String odczyt = scanner.nextLine();
        if (odczyt.equals("1")) {
            Prostokat prostokat = new Prostokat(4, 5);
            System.out.println(obliczPole(prostokat.getWysokosc(), prostokat.getSzerokosc()));
        }else{
            if (odczyt.equals("2")) {
                Prostokat prostokat = new Prostokat(4, 5);
                System.out.println(obliczObwod(prostokat.getWysokosc(), prostokat.getSzerokosc()));
            }else{
                if (odczyt.equals("3")) {
                    Prostokat prostokat = new Prostokat(4, 5);
                    System.out.println(obliczPrzekatna(prostokat.getWysokosc(), prostokat.getSzerokosc()));
                }
            }
        }
    }
}
