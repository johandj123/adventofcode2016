import lib.MathUtil;

import java.util.ArrayList;
import java.util.List;

public class Day15 {
    public static void main(String[] args) {
        run(false);
        run(true);
    }

    private static void run(boolean extraDisc) {
        List<MathUtil.ChineseRemainderTheoremEquation> equations = new ArrayList<>();
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(11 + 1), 13));
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(0 + 2), 5));
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(11 + 3), 17));
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(0 + 4), 3));
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(2 + 5), 7));
        equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(17 + 6), 19));
        if (extraDisc) {
            equations.add(new MathUtil.ChineseRemainderTheoremEquation(-(0 + 7), 11));
        }
        long result = MathUtil.solveUsingChineseRemainderTheorem(equations);
        System.out.println(result);
    }
}
