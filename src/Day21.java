import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input21.txt");
        first(input);
        second(input);
    }

    private static void first(List<String> input) {
        String s = scramble(input, "abcdefgh");
        System.out.println(s);
    }

    private static String scramble(List<String> input, String s) {
        for (String op : input) {
            List<Integer> param = InputUtil.extractPositiveIntegers(op);
            if (op.startsWith("swap position")) {
                s = swap(s, param.get(0), param.get(1));
            } else if (op.startsWith("swap letter")) {
                char letter1 = op.charAt(12);
                char letter2 = op.charAt(26);
                int index1 = s.indexOf(letter1);
                int index2 = s.indexOf(letter2);
                s = swap(s, index1, index2);
            } else if (op.startsWith("rotate left")) {
                Deque<Integer> deque = s.codePoints().boxed().collect(Collectors.toCollection(ArrayDeque::new));
                for (int i = 0; i < param.get(0); i++) {
                    deque.addLast(deque.removeFirst());
                }
                s = deque.stream().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            } else if (op.startsWith("rotate right")) {
                Deque<Integer> deque = s.codePoints().boxed().collect(Collectors.toCollection(ArrayDeque::new));
                for (int i = 0; i < param.get(0); i++) {
                    deque.addFirst(deque.removeLast());
                }
                s = deque.stream().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            } else if (op.startsWith("rotate based")) {
                char letter = op.charAt(op.length() - 1);
                int index = s.indexOf(letter);
                if (index >= 4) {
                    index++;
                }
                index++;
                Deque<Integer> deque = s.codePoints().boxed().collect(Collectors.toCollection(ArrayDeque::new));
                for (int i = 0; i < index; i++) {
                    deque.addFirst(deque.removeLast());
                }
                s = deque.stream().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            } else if (op.startsWith("reverse")) {
                String left = s.substring(0, param.get(0));
                String middle = s.substring(param.get(0), param.get(1) + 1);
                String right = s.substring(param.get(1) + 1);
                s = left + new StringBuilder(middle).reverse().toString() + right;
            } else if (op.startsWith("move")) {
                String letter = s.substring(param.get(0), param.get(0) + 1);
                s = s.substring(0, param.get(0)) + s.substring(param.get(0) + 1);
                s = s.substring(0, param.get(1)) + letter + s.substring(param.get(1));
            }
        }
        return s;
    }

    private static String swap(String s, int index1, int index2) {
        char[] chars = s.toCharArray();
        char h = chars[index1];
        chars[index1] = chars[index2];
        chars[index2] = h;
        return charsToString(chars);
    }

    private static String charsToString(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static void second(List<String> input) {
        permute(input, "abcdefgh", "");
    }

    private static void permute(List<String> input, String str, String ans) {
        if (str.isEmpty()) {
            String s = scramble(input, ans);
            if ("fbgdceah".equals(s)) {
                System.out.println(ans);
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            permute(input, ros, ans + ch);
        }
    }
}
