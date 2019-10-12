package katas.objectives;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;

public class Objectives1 {

    private static final int REQUESTED_WORD_LENGTH = 6;
    private static final int MIN_WORD_LENGTH = 2;
    private static List<String> allValidWords;

    public static void main(String[] args) throws IOException {
        StopWatch sw = new StopWatch();
        sw.start();

        List<String> words = readWords();
        sw.split();
        System.out.println("Reading words: " + sw.toSplitString());

        List<String> shortWords = words.stream()
                .filter(Objectives1::isValidShortWord)
                .collect(Collectors.toList());

        allValidWords = words.stream()
                .filter(s1 -> s1.length() == REQUESTED_WORD_LENGTH)
                .collect(Collectors.toList());

        for (String prefix : shortWords) {
            for (String suffix : shortWords) {
                if (canFormValidWord(prefix, suffix)) {
                    String testWord = prefix + suffix;
                    if (doesWordExist(testWord))
                        System.out.println(prefix + " + " + suffix + " => " + testWord);
                }
            }
        }

        sw.split();
        System.out.println("Processing: " + sw.toSplitString());
    }

    private static List<String> readWords() throws IOException {
        byte[] data = readAllBytes(Paths.get("/usr/local/src/jk/Cwiczenia/src/main/java/katas/wordlist.txt"));
        String s = new String(data);
        return Arrays.asList(s.split("\n")).stream()
                .map(String::toLowerCase)
                .distinct()
                //.limit(10000)
                .collect(Collectors.toList());
    }

    private static boolean canFormValidWord(String prefix, String suffix) {
        return prefix.length() + suffix.length() == REQUESTED_WORD_LENGTH;
    }

    private static boolean isValidShortWord(String word) {
        return (word.length() >= MIN_WORD_LENGTH)
                && (word.length() <= REQUESTED_WORD_LENGTH - MIN_WORD_LENGTH);
    }

    private static boolean doesWordExist(String word) {
        return allValidWords.contains(word);
    }

}
