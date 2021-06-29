package lessons.PetleIInstrukcjeWarunkowe;

import java.util.ArrayList;
import java.util.List;

public class Zadanie6 {
    public static void podzielLiczbe(int liczba){
        int licznik=0;
        List<Integer> wynik = new ArrayList();
        for (int i=0; Integer.toString(liczba).length() > wynik.size();i++){
         if (Integer.toString(liczba).contains(Integer.toString(i))){
             wynik.add(licznik,i);
             licznik++;
         }
     }
        for (int l=wynik.size()-1;l>=0;l--){
            System.out.println(wynik.get(l));
        }
    }

    public static void main(String[] args) {
        podzielLiczbe(567);
    }
}
