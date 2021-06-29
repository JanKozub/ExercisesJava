package lessons.Metody;

/**
 * Created by Cyprian on 2018-07-18.
 */
public class Zadanie8 {
//http://www.samouczekprogramisty.pl/zestaw-cwiczen-dla-poczatkujacych-programistow/
    public static boolean sprawdzCzyTrojkatJestProstokatny(double a,double b,double c){
        if (a > b && a > c){
            if (Math.pow(a,2) == Math.pow(b,2) + Math.pow(c,2)){
                return true;
            }
        }
        if (b > a && b > c){
            if (Math.pow(b,2) == Math.pow(a,2) + Math.pow(c,2)){
                return true;
            }
        }
        if (c > a && c > b){
            if (Math.pow(c,2) == Math.pow(b,2) + Math.pow(a,2)){
                return true;

            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(sprawdzCzyTrojkatJestProstokatny(3,4,5));
    }
}
