package lessons.Tablice;



public class Zadanie2 {

    public static int[] odwrocliste(int[] tablica){
        int[]wynik = new int[3];
        int kolejnosc=0;
        for (int i=tablica.length; i>0;i--){
            wynik[kolejnosc]= i;
            kolejnosc++;
        }
        return wynik;
    }

    public static void main(String[] args) {
        int[] listaZNormalnaKolejnoscia = {1,2,3};
        System.out.println("Normalna tablica");
        for (int i=0; i<=listaZNormalnaKolejnoscia.length-1; i++){
            System.out.println(listaZNormalnaKolejnoscia[i]);
        }
        System.out.println("Odwtrocona tablica");
        for (int i=0; i<=listaZNormalnaKolejnoscia.length-1; i++){
            System.out.println(odwrocliste(listaZNormalnaKolejnoscia)[i]);
        }
    }
}
