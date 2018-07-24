package PetleIInstrukcjeWarunkowe;

import java.util.ArrayList;
import java.util.List;

public class Zadanie7 {
    public static void odwrocSlowo(String slowo){
        List<String> wynik = new ArrayList();
        for (int i=0; i<=slowo.length()-1;i++){
            wynik.add(i,Character.toString(slowo.charAt(i)));
        }
        for(int l=wynik.size()-1; l>=0;l--){
            System.out.print(wynik.get(l));
        }
    }

    public static void main(String[] args) {
        odwrocSlowo("test");
    }
}
