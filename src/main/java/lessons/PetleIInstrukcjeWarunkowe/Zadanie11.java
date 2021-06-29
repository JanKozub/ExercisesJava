package lessons.PetleIInstrukcjeWarunkowe;

public class Zadanie11 {

    public static String zamienTabliceNaString(int[] tablica){
        String wynik = "[";
        for (int i=0; i<= tablica.length-1;i++){
            if (i>0){
                wynik = wynik + ",";
                wynik = wynik + tablica[i];
            }else{
                wynik = wynik + tablica[i];
            }
        }
        wynik = wynik + "]";
        return wynik;
    }

    public static void main(String[] args) {
        int[] tablica = {1,2,3,6,45,3,345,6};
        System.out.println(zamienTabliceNaString(tablica));
    }
}
