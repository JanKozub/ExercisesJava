package katas.objectives;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.readAllBytes;

public class Objectives2 {

    private static final int REQUESTED_WORD_LENGTH = 6;
    private static final int MIN_WORD_LENGTH = 2;
    private static Collection<String> allValidWords;

    public static void main(String[] args) throws IOException {
        StopWatch sw = new StopWatch();
        sw.start();

        Map<Integer, List<String>> shortWordsByLength = readWords()
                .filter(p -> p.length() > 1)
                .filter(p -> p.length() <= REQUESTED_WORD_LENGTH)
                .collect(Collectors.groupingBy(s -> s.length()));
        sw.split();
        System.out.println("Reading words: " + sw.toSplitString());

        allValidWords = new HashSet<>(shortWordsByLength.remove(REQUESTED_WORD_LENGTH));

        shortWordsByLength.values().stream().flatMap(List::stream).forEach(prefix -> {
            List<String> possibleSuffixes = shortWordsByLength.getOrDefault(REQUESTED_WORD_LENGTH - prefix.length(), Collections.emptyList());
            for (String suffix : possibleSuffixes) {
                String testWord = prefix + suffix;
                if (doesWordExist(testWord)) {
                    System.out.println(prefix + " + " + suffix + " => " + testWord);
                }
            }
        });

        sw.split();
        System.out.println("Processing: " + sw.toSplitString());
    }

    private static Stream<String> readWords() throws IOException {
        byte[] data = readAllBytes(Paths.get("/usr/local/src/jk/Cwiczenia/src/main/java/katas/wordlist.txt"));
        String s = new String(data);
        return Arrays.asList(s.split("\n")).stream()
                .map(String::toLowerCase)
                .distinct();
        //.limit(10000)
    }


    private static boolean isValidShortWord(String word) {
        return (word.length() >= MIN_WORD_LENGTH)
                && (word.length() <= REQUESTED_WORD_LENGTH - MIN_WORD_LENGTH);
    }

    private static boolean doesWordExist(String word) {
        return allValidWords.contains(word);
    }

}
