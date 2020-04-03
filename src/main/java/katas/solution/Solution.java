package katas.solution;

public class Solution {
    public static void main(String[] args) {
        System.out.println(solution("world"));
    }

    public static String solution(String str) {
        StringBuilder output = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            output.append(str.charAt(i));
        }
        return output.toString();
    }
}
