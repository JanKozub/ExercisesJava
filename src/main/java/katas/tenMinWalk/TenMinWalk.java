package katas.tenMinWalk;

public class TenMinWalk {

    public static void main(String[] args) {
        System.out.println(isValid(new char[]{'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e', 'w', 'e'}));
    }

    public static boolean isValid(char[] walk) {
        if (walk.length != 10)
            return false;
        int verVal = 0; //vertical value
        int horVal = 0; //horizontal value

        for (char c : walk) {
            if (c == 'n')
                verVal++;
            else if (c == 's')
                verVal--;
            else if (c == 'w')
                horVal++;
            else if (c == 'e')
                horVal--;
        }
        return verVal == 0 && horVal == 0;
    }
}
