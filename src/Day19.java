import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
        Deque<Integer> left = new ArrayDeque<>();
        Deque<Integer> right = new ArrayDeque<>();
        for (int i = 1; i <= input / 2; i++) {
            left.addLast(i);
        }
        for (int i = (input / 2) + 1; i <= input; i++) {
            right.addFirst(i);
        }

        while (left.size() + right.size() > 1) {
            if (left.size() > right.size()) {
                left.removeLast();
            } else {
                right.removeLast();
            }
            right.addFirst(left.removeFirst());
            left.addLast(right.removeLast());
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
