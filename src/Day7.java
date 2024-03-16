import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input7.txt");
        first(input);
        second(input);
    }

    private static void first(List<String> input) {
        long result = input.stream().filter(Day7::supportsTls).count();
        System.out.println(result);
    }

    private static void second(List<String> input) {
        long result = input.stream().filter(Day7::supportsSsl).count();
        System.out.println(result);
    }

    private static boolean supportsTls(String s) {
        String[] sp = s.split("[\\[\\]]");
        boolean result = false;
        for (int i = 0; i < sp.length; i++) {
            if (abba(sp[i])) {
                if ((i % 2) == 1) {
                    return false;
                }
                result = true;
            }
        }
        return result;
    }

    private static boolean supportsSsl(String s) {
        String[] sp = s.split("[\\[\\]]");
        List<String> babList = new ArrayList<>();
        for (int i = 0; i < sp.length; i += 2) {
            babList.addAll(abaAsBab(sp[i]));
        }
        for (int i = 1; i < sp.length; i += 2) {
            String sps = sp[i];
            if (babList.stream().anyMatch(sps::contains)) {
                return true;
            }
        }
        return false;
    }

    private static boolean abba(String s) {
        for (int i = 0; i < s.length() - 3; i++) {
            if (s.charAt(i) != s.charAt(i + 1) &&
                    s.charAt(i) == s.charAt(i + 3) &&
                    s.charAt(i + 1) == s.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> abaAsBab(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 2) &&
                    s.charAt(i) != s.charAt(i + 1)) {
                char a = s.charAt(i);
                char b = s.charAt(i + 1);
                result.add("" + b + a + b);
            }
        }
        return result;
    }
}
