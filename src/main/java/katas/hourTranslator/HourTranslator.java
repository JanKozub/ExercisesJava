package katas.hourTranslator;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HourTranslator {
    public static void main(String[] args) {
        System.out.println(formatDuration2(3806));
    }

    public static String formatDuration(int seconds) {
        StringBuilder value = new StringBuilder();
        int[] timetable = {0, 0, 0, seconds / 60, seconds % 60};
        int[] valTable = {365, 24, 60};
        String[] values = {"year", "day", "hour", "minute", "second"};

        if (seconds == 0)
            return "now";

        for (int i = 2; i >= 0; i--) {
            timetable[i] = Math.floorDiv(timetable[i + 1], valTable[i]);
            if (timetable[i] > 0) {
                timetable[i + 1] = timetable[i + 1] % valTable[i];
            }
        }

        for (int i = 0; i < 5; i++) {
            if (timetable[i] > 0) {
                value.append(timetable[i]).append(" ").append(values[i]);
                if (timetable[i] > 1)
                    value.append("s");
                String separator = "";
                for (int k = i + 1; k < 5; k++) {
                    if (timetable[k] > 0)
                        if (!separator.equals(", "))
                            if (separator.equals(" and ")) separator = ", ";
                            else separator = " and ";
                }
                value.append(separator);
            }
        }

        return value.toString();
    }

    public static String formatDuration2(int seconds) {

        int[] divValues = {
                365 * 24 * 60 * 60,
                24 * 60 * 60,
                60 * 60,
                60,
                1
        };
        int[] results = new int[divValues.length];
        String[] values = {"year", "day", "hour", "minute", "second"};

        if (seconds == 0) return "now";

        for (int i = 0; i < divValues.length; i++) {
            results[i] = seconds / divValues[i];
            seconds = seconds % divValues[i];
        }

        String output = IntStream.range(0, divValues.length)
                .filter(idx -> results[idx] > 0)
                .mapToObj(idx -> results[idx] + " " + values[idx] + (results[idx] > 1 ? "s" : ""))
                .collect(Collectors.joining(", "));

        int idx = output.lastIndexOf(",");
        if (idx >= 0)
            output = output.substring(0, idx) + " and" + output.substring(idx + 1);

        return output;
    }
}
