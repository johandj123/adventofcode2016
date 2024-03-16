import lib.InputUtil;

import java.io.IOException;
import java.util.*;

public class Day10 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input10.txt");
        Map<Integer, Deque<Integer>> botValues = new HashMap<>();
        Map<Integer, List<Integer>> outputValues = new HashMap<>();
        for (String s : input) {
            if (s.startsWith("value")) {
                List<Integer> numbers = InputUtil.extractPositiveIntegers(s);
                botValues.computeIfAbsent(numbers.get(1), key -> new ArrayDeque<>()).offerLast(numbers.get(0));
            }
        }
        boolean change;
        do {
            change = false;
            for (String s : input) {
                if (s.startsWith("bot")) {
                    String[] sp = s.split(" ");
                    Deque<Integer> deque = botValues.computeIfAbsent(Integer.parseInt(sp[1]), key -> new ArrayDeque<>());
                    if (deque.size() >= 2) {
                        int value1 = deque.removeFirst();
                        int value2 = deque.removeFirst();
                        int lowValue = Math.min(value1, value2);
                        int highValue = Math.max(value1, value2);
                        giveValue(sp[5], Integer.parseInt(sp[6]), lowValue, botValues, outputValues);
                        giveValue(sp[10], Integer.parseInt(sp[11]), highValue, botValues, outputValues);
                        if (lowValue == 17 && highValue == 61) {
                            System.out.println(sp[1]);
                        }
                        change = true;
                    }
                }
            }
        } while (change);
        int result = outputValues.get(0).get(0) * outputValues.get(1).get(0) * outputValues.get(2).get(0);
        System.out.println(result);
    }

    private static void giveValue(String type,int number,int value,Map<Integer, Deque<Integer>> botValues, Map<Integer, List<Integer>> outputValues) {
        if ("bot".equals(type)) {
            botValues.computeIfAbsent(number, key -> new ArrayDeque<>()).offerLast(value);
        } else if ("output".equals(type)) {
            outputValues.computeIfAbsent(number, key -> new ArrayList<>()).add(value);
        } else {
            throw new IllegalArgumentException("Unknown destination type " + type);
        }
    }
}
