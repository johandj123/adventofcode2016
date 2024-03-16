import lib.InputUtil;

import java.io.IOException;

public class Data16 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input16.txt");
        run(input, 272);
        run(input, 35651584);
    }

    private static void run(String input, int diskSize) {
        String s = input;
        while (s.length() < diskSize) {
            s = generateStep(s);
        }
        s = s.substring(0, diskSize);
        while ((s.length() % 2) == 0) {
            s = checksumStep(s);
        }
        System.out.println(s);
    }

    private static String generateStep(String input) {
        StringBuilder sb = new StringBuilder(input).append('0');
        for (int i = input.length() - 1; i >= 0; i--) {
            char c = input.charAt(i);
            if (c == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    private static String checksumStep(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }
}
