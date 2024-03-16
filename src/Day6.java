import lib.InputUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input6.txt");
        first(input);
        second(input);
    }

    private static void first(List<String> input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i++) {
            sb.append(mostCommon(input, i));
        }
        String message = sb.toString();
        System.out.println(message);
    }

    private static void second(List<String> input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.get(0).length(); i++) {
            sb.append(leastCommon(input, i));
        }
        String message = sb.toString();
        System.out.println(message);
    }

    private static char mostCommon(List<String> input,int position) {
        Map<Character, Integer> map = new HashMap<>();
        for (String s : input) {
            char c = s.charAt(position);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    private static char leastCommon(List<String> input,int position) {
        Map<Character, Integer> map = new HashMap<>();
        for (String s : input) {
            char c = s.charAt(position);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map.entrySet().stream()
                .min(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow();
    }
}
