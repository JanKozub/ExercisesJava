package lessons.PetleIInstrukcjeWarunkowe;

public class Zadanie12 {

    public static int[]posortujTablice(int[] tablica){
        for (int l=0; l<=tablica.length;l++) {
            int i = 0;
            while (i < tablica.length - 1) {
                int cyfra1 = tablica[i];
                int cyfra2 = tablica[i + 1];

                if (tablica[i] > tablica[i + 1]) {
                    tablica[i] = cyfra2;
                    tablica[i + 1] = cyfra1;
                }
                i++;
            }
        }
        return tablica;
    }

    public static void main(String[] args) {
        int[]tablica = {5,2,20,8,9,7};

        for (int i = 0; i < tablica.length; i++) {
            System.out.println(posortujTablice(tablica)[i]);
        }
    }
}
