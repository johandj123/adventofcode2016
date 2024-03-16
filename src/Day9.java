import lib.InputUtil;

import java.io.IOException;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input9.txt");
        System.out.println(decompresssSize(input, false));
        System.out.println(decompresssSize(input, true));
    }

    private static long decompresssSize(String s, boolean recurse) {
        long result = 0L;
        for (int i = 0; i < s.length(); ) {
            char c = s.charAt(i);
            if (c == '(') {
                i++;
                int j = i;
                while (s.charAt(i) != 'x') {
                    i++;
                }
                int patternLength = Integer.parseInt(s.substring(j, i));
                i++;
                j = i;
                while (s.charAt(i) != ')') {
                    i++;
                }
                int patternRepeats = Integer.parseInt(s.substring(j, i));
                i++;
                long patternTotal;
                if (!recurse) {
                    patternTotal = ((long) patternRepeats) * ((long) patternLength);
                } else {
                    patternTotal = ((long) patternRepeats) * decompresssSize(s.substring(i, i + patternLength), true);
                }
                result += patternTotal;
                i += patternLength;
            } else {
                result++;
                i++;
            }
        }
        return result;
    }
}
