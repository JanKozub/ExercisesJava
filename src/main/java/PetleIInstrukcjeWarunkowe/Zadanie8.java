package PetleIInstrukcjeWarunkowe;

import java.util.ArrayList;
import java.util.List;

public class Zadanie8 {

    public static int zamienLiczbeNaBinarna(int liczba){
        List<String> binarnaLista = new ArrayList<String>();
        while (liczba > 0){
            if (liczba % 2 == 1) {
                binarnaLista.add("1");
            } else {
                binarnaLista.add("0");
            }
            liczba = liczba / 2;
            Math.floor(liczba);
        }
        String wynik = "";
        for (int i=binarnaLista.size()-1; i>=0;i--){
            wynik = wynik + binarnaLista.get(i);
        }
        return Integer.parseInt(wynik);
    }
    public static void main(String[] args) {
        System.out.println(zamienLiczbeNaBinarna(5));
    }
}
