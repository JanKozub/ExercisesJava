package Metody;

/**
 * Created by Cyprian on 2018-07-18.
 */
public class Zadanie5 {



    public static boolean sprawdzPodzielnosc(int liczba){
        int reszta1 = liczba % 3;
        int reszta2 = liczba % 5;
        if(reszta1 == 0 && reszta2 == 0){
            return true;
        }else{
            return false;
        }
    }


    public static void main(String[] args) {
        System.out.println(sprawdzPodzielnosc(30));
    }
}
