import lib.InputUtil;
import lib.Pos2;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input1.txt");
        String[] sp = input.split(", ");
        int x = 0;
        int y = 0;
        int dx = 0;
        int dy = 1;
        Set<Pos2> set = new HashSet<>(List.of(new Pos2(x, y)));
        Integer mh2 = null;
        for (String s : sp) {
            int d = Integer.parseInt(s.substring(1));
            int ndx;
            int ndy;
            if (s.startsWith("L")) {
                ndx = -dy;
                ndy = dx;
            } else {
                ndx = dy;
                ndy = -dx;
            }
            dx = ndx;
            dy = ndy;
            for (int i = 0; i < d; i++) {
                x += dx;
                y += dy;
                if (!set.add(new Pos2(x, y)) && mh2 == null) {
                    mh2 = Math.abs(x) + Math.abs(y);
                }
            }
        }
        int mh1 = Math.abs(x) + Math.abs(y);
        System.out.println(mh1);
        System.out.println(mh2);
    }
}
