import lib.CharMatrix;
import lib.InputUtil;

import java.io.IOException;

public class Day18 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input18.txt");
        countSafe(input, 40);
        countSafe(input, 400000);
    }

    private static void countSafe(String input, int height) {
        CharMatrix charMatrix = new CharMatrix(height, input.length(), '.');
        for (int x = 0; x < input.length(); x++) {
            charMatrix.set(x, 0, input.charAt(x));
        }
        for (int y = 1; y < charMatrix.getHeight(); y++) {
            for (int x = 0; x < charMatrix.getWidth(); x++) {
                boolean trapLeft = (charMatrix.getUnbounded(x - 1, y - 1) == '^');
                boolean trapRight = (charMatrix.getUnbounded(x + 1, y - 1) == '^');
                if ((trapLeft && !trapRight) || (!trapLeft && trapRight)) {
                    charMatrix.set(x, y, '^');
                }
            }
        }
        int count = 0;
        for (int y = 0; y < charMatrix.getHeight(); y++) {
            for (int x = 0; x < charMatrix.getWidth(); x++) {
                if (charMatrix.get(x, y) == '.') {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
