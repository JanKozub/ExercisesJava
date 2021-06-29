package lessons.PetleIInstrukcjeWarunkowe;

import java.util.ArrayList;
import java.util.List;

public class Zadanie9 {
    public static boolean sprawdzCzyWyrazJestPalinondromem(String wyraz){

        List<String> wynik = new ArrayList();
        for (int i=0; i<=wyraz.length()-1;i++){
            wynik.add(i,Character.toString(wyraz.charAt(i)));
        }

        do {

            wynik.remove(" ");

        }while(wynik.contains(" "));
        do {

            wynik.remove(",");

        }while(wynik.contains(","));
        
        int licznikDobrychliter=0;
        int licznik=wynik.size();
        for (Object l:wynik) {
            if (l.equals(wynik.get(licznik-1))){
                licznikDobrychliter++;
            }
            licznik--;
        }
        if (licznikDobrychliter == wynik.size()) {
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(sprawdzCzyWyrazJestPalinondromem("tu armata, a tam raut"));
    }
}
