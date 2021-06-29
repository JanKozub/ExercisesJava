package lessons.Tablice;

public class Zadanie1 {
    public static void zwrocZnaki(String[] tablica){

        for(int i=0; i<5;i++){
            System.out.println(tablica[i]);
        }
    }
    public static void main(String[] args) {
        String[] alfabet = {"a","b","c","d","e"};
        zwrocZnaki(alfabet);
    }
}
