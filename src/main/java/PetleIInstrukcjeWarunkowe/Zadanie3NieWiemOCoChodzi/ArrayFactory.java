package PetleIInstrukcjeWarunkowe.Zadanie3NieWiemOCoChodzi;

public class ArrayFactory {
    private int liczba;

    public int getLiczba() {
        return liczba;
    }

    public void setLiczba(int liczba) {
        this.liczba = liczba;
    }

    public ArrayFactory(int liczba) {

        this.liczba = liczba;
    }

    public static int[]oneDimension(int x){

        return oneDimension(1);
    }


    public static void main(String[] args) {

    }
}
