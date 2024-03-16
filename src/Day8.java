import lib.InputUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day8 {
    public static void main(String[] args) throws IOException {
        List<String> input = InputUtil.readAsLines("input8.txt");
        boolean[][] display = calculateDisplay(input);
        first(display);
        second(display);
    }

    private static boolean[][] calculateDisplay(List<String> input) {
        boolean[][] display = new boolean[6][50];
        for (String s : input) {
            List<Integer> parameters = InputUtil.extractPositiveIntegers(s);
            if (s.startsWith("rect")) {
                int w = parameters.get(0);
                int h = parameters.get(1);
                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        display[y][x] = true;
                    }
                }
            } else if (s.startsWith("rotate row")) {
                int y = parameters.get(0);
                int d = parameters.get(1);
                boolean[] org = Arrays.copyOf(display[y], display[y].length);
                for (int x = 0; x < display[y].length; x++) {
                    int nx = (x + d) % display[y].length;
                    display[y][nx] = org[x];
                }
            } else if (s.startsWith("rotate column")) {
                int x = parameters.get(0);
                int d = parameters.get(1);
                boolean[] org = new boolean[display.length];
                for (int y = 0; y < display.length; y++) {
                    org[y] = display[y][x];
                }
                for (int y = 0; y < display.length; y++) {
                    int ny = (y + d) % display.length;
                    display[ny][x] = org[y];
                }
            }
        }
        return display;
    }

    private static void first(boolean[][] display) {
        int count = 0;
        for (int y = 0; y < display.length; y++) {
            for (int x = 0; x < display[0].length; x++) {
                if (display[y][x]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void second(boolean[][] display) {
        for (int y = 0; y < display.length; y++) {
            for (int x = 0; x < display[0].length; x++) {
                System.out.print(display[y][x] ? '#' : '.');
            }
            System.out.println();
        }
    }
}
