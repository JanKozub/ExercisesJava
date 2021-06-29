package lessons.exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleException {
    public static class MyException extends Exception {
        public MyException() {
            super();
        }

        public MyException(String message) {
            super(message);
        }
    }

    public static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException() {
            super();
        }

        public MyUncheckedException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws MyException {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        try {
            String line = readLine();
            process(array, line);
        } catch (IOException ex) {
            System.out.println("IO");
        }

        System.out.println("We're done!!!");
    }

    public static void main2(String[] args) throws IOException,MyException{
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        String line = readLine();
        process(array, line);

        System.out.println("We're done!!!");
    }

    public static void main3(String[] args) throws Exception{
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        String line = readLine();
        process(array, line);

        System.out.println("We're done!!!");
    }

    public static void main4(String[] args) throws Exception{
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        try {
            String line = readLine();
            process(array, line);
        } catch(Exception ex) {
            System.out.println("something wrong happened");
            throw ex;
        }

        System.out.println("We're done!!!");
    }

    private static final String readLine() throws IOException {
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader reader = new BufferedReader(isr)) {
            return reader.readLine();
        }
    }

    private static final void process(int[] array, String input) throws MyException {
        int max = Integer.parseInt(input);
        if (max > array.length) throw new MyException("glupia wartosc");
        if(max < 0) throw new MyUncheckedException("inne glupia wartosc");

        for (int i = 0; i < max; i++)
            System.out.println("" + array[i]);
    }
}
