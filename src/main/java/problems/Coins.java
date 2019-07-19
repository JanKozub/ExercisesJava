package problems;

import java.util.Arrays;

public class Coins {
    public static void main(String[] args) {
        Coins coins = new Coins(new int[] {100, 30, 10});
        System.out.println(Arrays.toString(coins.getNominals()));
        System.out.println(Arrays.toString(coins.splitAmountByCounts(1250)));
    }

    public int[] splitAmount(int money){
        int[] nominals = {500, 200, 100, 50, 20, 10, 5, 2, 1};

        int[] values = {};

        int index = 0;
        while (money > 0){
            int divide = money / nominals[index];
            if (divide >= 1){
                int[] newValues = Arrays.copyOf(values, values.length + divide);
                for (int j = 0; j < divide; j++){
                    newValues[values.length + j] = nominals[index];
                }
                values = newValues;
                money = money % nominals[index];
            }
            index++;
        }
        return values;
    }


    private final int[] counts;

    public Coins() {
        this(new int[] {500, 200, 100, 50, 20, 10, 5, 2, 1});
    }

    public Coins(int[] amounts) {
        this.counts = amounts;
        // validatio nis needed;
    }



    public int[] getNominals() {
        return counts;
    }

    public int[] splitAmountByCounts(int money){
        int[] nominals = getNominals();
        int[] counts = new int[nominals.length];

        for(int i = 0; i < nominals.length; i++) {
            int divide = money / nominals[i];
            counts[i] = divide;
            money = money % nominals[i];
        }
        return counts;
    }
}
