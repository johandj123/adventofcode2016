import lib.InputUtil;

import java.io.IOException;

public class Day19 {
    public static void main(String[] args) throws IOException {
        int input = Integer.parseInt(InputUtil.readAsString("input19.txt"));
        Elf firstElf = buildElfCircle(input);
        first(firstElf);
    }

    private static void first(Elf firstElf) {
        Elf elf = firstElf;
        while (elf.nextElf != elf) {
            elf.nextElf = elf.nextElf.nextElf;
            elf = elf.nextElf;
        }
        System.out.println(elf.number);
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
