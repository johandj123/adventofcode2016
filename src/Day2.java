import lib.CharMatrix;
import lib.InputUtil;

import java.io.IOException;
import java.util.List;

public class Day2 {
    private static final CharMatrix NUMBERPAD1 = new CharMatrix(List.of("123", "456", "789"));
    private static final CharMatrix NUMBERPAD2 = new CharMatrix(List.of("  1  ", " 234 ", "56789", " ABC ", "  D  "));

    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input2.txt");
        first(input);
        second(input);
    }

    private static void first(List<String> input) {
        int x = 1;
        int y = 1;
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            for (char c : s.toCharArray()) {
                switch (c) {
                    case 'L':
                        x = Math.max(x - 1, 0);
                        break;
                    case 'R':
                        x = Math.min(x + 1, 2);
                        break;
                    case 'U':
                        y = Math.max(y - 1, 0);
                        break;
                    case 'D':
                        y = Math.min(y + 1, 2);
                        break;
                }
            }
            sb.append(NUMBERPAD1.get(x, y));
        }
        String code = sb.toString();
        System.out.println(code);
    }

    private static void second(List<String> input) {
        int x = 0;
        int y = 2;
        StringBuilder sb = new StringBuilder();
        for (String s : input) {
            for (char c : s.toCharArray()) {
                int nx = x;
                int ny = y;
                switch (c) {
                    case 'L':
                        nx = x - 1;
                        break;
                    case 'R':
                        nx = x + 1;
                        break;
                    case 'U':
                        ny = y - 1;
                        break;
                    case 'D':
                        ny = y + 1;
                        break;
                }
                if (NUMBERPAD2.getUnbounded(nx, ny) != ' ') {
                    x = nx;
                    y = ny;
                }
            }
            sb.append(NUMBERPAD2.get(x, y));
        }
        String code = sb.toString();
        System.out.println(code);
    }
}
