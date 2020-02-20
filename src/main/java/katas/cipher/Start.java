package katas.cipher;

public class Start {
    public static void main(String[] args) {
        AlphabetCipher alphabetCipher = new AlphabetCipher(AlphabetCipher.ASCII_FULL, "scones");

        String encodedMsg = alphabetCipher.encode("SAGstnbFSAghYDJDGF63gadvYUT");
        System.out.println(encodedMsg);
        System.out.println(alphabetCipher.decode(encodedMsg));
    }
}
