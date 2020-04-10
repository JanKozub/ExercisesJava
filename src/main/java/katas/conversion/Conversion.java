package katas.conversion;

/*
Create a function taking a positive integer as its parameter and returning a string containing the Roman Numeral representation of that integer.

Modern Roman numerals are written by expressing each digit separately starting with the left most digit and skipping any digit with a value of zero.
In Roman numerals 1990 is rendered: 1000=M, 900=CM, 90=XC; resulting in MCMXC.
2008 is written as 2000=MM, 8=VIII; or MMVIII. 1666 uses each Roman symbol in descending order: MDCLXVI.

Example:

conversion.solution(1000); //should return "M"
Help:

Symbol    Value
I          1
V          5
X          10
L          50
C          100
D          500
M          1,000
Remember that there can't be more than 3 identical symbols in a row.

More about roman numerals - http://en.wikipedia.org/wiki/Roman_numerals
 */

public class Conversion {

    private static String[] tens = {"I", "X", "C", "M"};
    private static String[] halfs = {"V", "L", "D"};

    public static void main(String[] args) {
        System.out.println(solution(1337));
    }

    public static String solution(int n) {
        StringBuilder output = new StringBuilder();

        int i = 0;
        do {
            int num = n % 10;
            output.insert(0, convertDigit(num, i));
            n /= 10;
            i++;
        } while (n > 0);

        return output.toString();
    }

    private static String convertDigit(int n, int idx) {
        StringBuilder string = new StringBuilder();

        if (idx >= 4)
            string.append(tens[idx]);
        else {
            if (n == 4 || n == 9)
                string.append(tens[idx]);

            if (n >= 9)
                string.append(tens[idx + 1]);

            if (n <= 3) {
                for (int i = 0; i < n; i++) {
                    string.append(tens[idx]);
                }
            }

            if (n >= 4 && n <= 8) {
                string.append(halfs[idx]);
                for (int i = 0; i < n - 5; i++) {
                    string.append(tens[idx]);
                }
            }
        }

        return string.toString();
    }
}
