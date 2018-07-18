package Metody;

/**
 * Created by Cyprian on 2018-07-18.
 */
public class Zadanie6 {

    public static double podniesDoPotegi(double iloscPoteg, double liczba){

        double wynik = Math.pow(liczba,iloscPoteg);

        return wynik;
    }

    public static void main(String[] args) {
        System.out.println(podniesDoPotegi(2,2));
    }
}
