package katas.cipher;

public class Start {
    public static void main(String[] args) {
        AlphabetCipher alphabetCipher = new AlphabetCipher(AlphabetCipher.ASCII_FULL, "scones");

        System.out.println(alphabetCipher.encode("meetmebythethree"));
    }
}
