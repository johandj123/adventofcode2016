import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    public static void main(String[] args) throws IOException {
        List<int[]> lines = readInput();
        first(lines);
        second(lines);
    }

    private static List<int[]> readInput() throws IOException {
        List<String> input = InputUtil.readAsLines("input3.txt");
        List<int[]> lines = new ArrayList<>();
        for (String s : input) {
            String[] sp = s.split("\\D");
            int[] ii = Arrays.stream(sp)
                    .filter(ss -> !ss.isBlank())
                    .mapToInt(Integer::parseInt)
                    .toArray();
            lines.add(ii);
        }
        return lines;
    }

    private static void first(List<int[]> lines) {
        long possible = lines.stream().filter(Day3::possible).count();
        System.out.println(possible);
    }

    private static void second(List<int[]> lines) {
        List<int[]> rotated = new ArrayList<>();
        for (int i = 0; i < lines.size(); i += 3) {
            for (int j = 0; j < 3; j++) {
                rotated.add(new int[]{ lines.get(i)[j], lines.get(i + 1)[j], lines.get(i + 2)[j] });
            }
        }
        long possible = rotated.stream().filter(Day3::possible).count();
        System.out.println(possible);
    }

    private static boolean possible(int[] ii) {
        return ii[0] + ii[1] > ii[2] &&
                ii[0] + ii[2] > ii[1] &&
                ii[1] + ii[2] > ii[0];
    }
}
