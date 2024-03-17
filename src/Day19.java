import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day19 {
    public static void main(String[] args) throws IOException {
        int input = Integer.parseInt(InputUtil.readAsString("input19.txt"));
        first(input);
        second(input);
    }

    private static void first(int input) {
        Elf elf = buildElfCircle(input);
        while (elf.nextElf != elf) {
            elf.nextElf = elf.nextElf.nextElf;
            elf = elf.nextElf;
        }
        System.out.println(elf.number);
    }

    private static void second(int input) {
        Deque<Integer> left = IntStream.rangeClosed(1, input / 2).boxed().collect(Collectors.toCollection(ArrayDeque::new));
        Deque<Integer> right = IntStream.rangeClosed(input / 2 + 1, input).boxed().collect(Collectors.toCollection(ArrayDeque::new));

        while (left.size() + right.size() > 1) {
            if (left.size() > right.size()) {
                left.removeLast();
            } else {
                right.removeFirst();
            }
            right.addLast(left.removeFirst());
            left.addLast(right.removeFirst());
        }

        List<Integer> result = new ArrayList<>();
        result.addAll(left);
        result.addAll(right);
        System.out.println(result.get(0));
    }

    private static Elf buildElfCircle(int input) {
        Elf firstElf = null;
        Elf lastElf = null;
        for (int i = 0; i < input; i++) {
            Elf elf = new Elf(i + 1);
            if (firstElf == null) {
                firstElf = elf;
                lastElf = elf;
            } else {
                lastElf.nextElf = elf;
                lastElf = elf;
            }
        }
        lastElf.nextElf = firstElf;
        return firstElf;
    }

    static class Elf {
        Elf nextElf;
        final int number;

        public Elf(int number) {
            this.number = number;
        }
    }
}
