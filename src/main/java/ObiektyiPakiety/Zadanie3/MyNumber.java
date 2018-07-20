package ObiektyiPakiety.Zadanie3;

public class MyNumber {
    private double numer;
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getNumer() {
        return numer;
    }

    public void setNumer(double numer) {
        this.numer = numer;
    }

    public MyNumber(double numer, double x) {

        this.numer = numer;
        this.x = x;
    }


    public static boolean isOdd(double numer){

        if (numer % 2 == 0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean isEven(double numer){

        if (numer % 2 == 0){
            return true;
        }else{
            return false;
        }
    }
    public static double add(double numer, double numer2){
        double wynik = numer + numer2;

        return wynik;
    }
    public static double substract(double numer, double numer2){
        double wynik = numer - numer2;

        return wynik;
    }
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber(5,2);

        System.out.println(isOdd(myNumber.numer));
        System.out.println(isEven(myNumber.numer));
        System.out.println(Math.sqrt(myNumber.numer));
        System.out.println(Math.pow(myNumber.numer,myNumber.x));
        System.out.println(add(myNumber.numer,myNumber.x));
        System.out.println(substract(myNumber.numer,myNumber.x));
    }
}
