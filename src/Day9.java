import lib.InputUtil;

import java.io.IOException;

public class Day9 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input9.txt");
        System.out.println(decompress(input).length());
    }

    private static String decompress(String s) {
        StringBuilder sb = new StringBuilder();
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
                for (int k = 0; k < patternRepeats; k++) {
                    for (int l = 0; l < patternLength; l++) {
                        sb.append(s.charAt(i + l));
                    }
                }
                i += patternLength;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }
}
