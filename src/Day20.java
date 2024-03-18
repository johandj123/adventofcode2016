import lib.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day20 {
    public static void main(String[] args) throws IOException {
        List<Interval> intervals = InputUtil.readAsLines("input20.txt").stream()
                .map(Interval::new)
                .collect(Collectors.toList());
        first(intervals);
        second(intervals);
    }

    private static void first(List<Interval> intervals) {
        long result = firstUnblocked(intervals, 0L);
        System.out.println(result);
    }

    private static void second(List<Interval> intervals) {
        long result = 0L;
        long current = 0L;
        while (current < 4294967296L) {
            long start = firstUnblocked(intervals, current);
            long end = firstBlocked(intervals, start);
            result += (end - start);
            current = end;
        }
        System.out.println(result);
    }

    private static long firstUnblocked(List<Interval> intervals, long input) {
        long x = input;
        boolean change;
        do {
            change = false;
            for (Interval interval : intervals) {
                if (interval.inInterval(x)) {
                    x = interval.end + 1;
                    change = true;
                }
            }
        } while (change);
        return x;
    }

    private static long firstBlocked(List<Interval> intervals, long input) {
        long result = 4294967296L;
        for (Interval interval : intervals) {
            if (interval.start > input) {
                result = Math.min(result, interval.start);
            }
        }
        return result;
    }

    static class Interval {
        final long start;
        final long end;

        Interval(String s) {
            String[] sp = s.split("-");
            start = Long.parseLong(sp[0]);
            end = Long.parseLong(sp[1]);
        }

        boolean inInterval(long x) {
            return start <= x && x <= end;
        }
    }
}
