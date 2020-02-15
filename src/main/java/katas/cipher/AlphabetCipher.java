package katas.cipher;

import java.util.Arrays;

public class AlphabetCipher implements Cipher {
    public static final String ASCII_UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ASCII_FULL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private final char[] alphabet;
    private final String password;

    public AlphabetCipher(String alphabet, String password) {
        this.alphabet = alphabet.toCharArray();
        this.password = password;
        Arrays.sort(this.alphabet);
    }


    public AlphabetCipher(String password) {
        this(ASCII_FULL, password);
    }

    @Override
    public String decode(String message) {
        return "";
    }

    @Override
    public String encode(String message) {
        StringBuilder result = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            int x = getIndexOf(password.charAt(i % password.length()));
            int y = getIndexOf(message.charAt(i));
            int encoded = (y + x) % alphabet.length;
            result.append(alphabet[encoded]);
        }
        return result.toString();
    }

    private int getIndexOf(char letter) {
        return Arrays.binarySearch(alphabet, letter);
    }
}
