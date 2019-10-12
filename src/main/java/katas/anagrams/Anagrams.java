package katas.anagrams;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;

public class Anagrams {
    private static Map<Representation, List<String>> anagrams;

    public static void main(String[] args) throws IOException {
        StopWatch sw = new StopWatch();

        sw.start();
        byte[] data = readAllBytes(Paths.get("/usr/local/src/jk/Cwiczenia/src/main/java/katas/wordlist.txt"));
        String s = new String(data);
        List<String> words = Arrays.asList(s.split("\n")).stream()
                .map(String::toLowerCase)
                //.limit(10000)
                .sorted()
                .collect(Collectors.toList());
        sw.split();
        System.out.println("Reading: " + sw.toSplitString() + " / " + words.size());

        anagrams = new HashMap<>();

        int cnt = 0;

        for (String word : words) {
            if (cnt++ % 1000 == 0) {
                sw.split();
                System.out.println(cnt + ": " + sw.toSplitString());
            }

            Representation w = new Representation(represent(word));
            anagrams.compute(w, (k, v) -> {
                if (v == null) {
                    v = new ArrayList<>();
                    v.add(word);
                } else {
                    v.add(word);
                }
                return v;
            });
        }

        sw.split();
        System.out.println("Processing: " + sw.toSplitString());

//        Arrays.stream(anagrams)
//                .flatMap(m -> m.values().stream())
//                .map(e -> e.stream().collect(Collectors.joining(", ", "[", "]")))
//                .forEach(System.out::println);
    }


    private static char[] represent(String word) {
        char[] wordArray = word.toCharArray();
        Arrays.sort(wordArray);
        return wordArray;
    }


    private static class Representation {
        char[] data;

        public Representation(char[] data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Representation that = (Representation) o;
            return Arrays.equals(data, that.data);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }
    }
}
