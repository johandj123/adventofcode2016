import lib.InputUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) throws IOException {
        List<String> instructions = InputUtil.readAsLines("input12.txt");
        first(instructions);
        second(instructions);
    }

    private static void first(List<String> instructions) {
        Computer computer = new Computer(instructions);
        computer.run();
        System.out.println(computer.getValue("a"));
    }

    private static void second(List<String> instructions) {
        Computer computer = new Computer(instructions);
        computer.registers.put("c", 1);
        computer.run();
        System.out.println(computer.getValue("a"));
    }

    static class Computer {
        final List<String> instructions;
        final Map<String, Integer> registers = new HashMap<>();
        int pc = 0;

        public Computer(List<String> instructions) {
            this.instructions = instructions;
        }

        public void run() {
            while (0 <= pc && pc < instructions.size()) {
                String[] sp = instructions.get(pc).split(" ");
                String opc = sp[0];
                if ("cpy".equals(opc)) {
                    registers.put(sp[2], getValue(sp[1]));
                    pc++;
                } else if ("inc".equals(opc)) {
                    registers.put(sp[1], getValue(sp[1]) + 1);
                    pc++;
                } else if ("dec".equals(opc)) {
                    registers.put(sp[1], getValue(sp[1]) - 1);
                    pc++;
                } else if ("jnz".equals(opc)) {
                    if (getValue(sp[1]) != 0) {
                        pc += Integer.parseInt(sp[2]);
                    } else {
                        pc++;
                    }
                } else {
                    throw new IllegalArgumentException("Unknown opcode " + opc);
                }
            }
        }

        private int getValue(String s) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return registers.getOrDefault(s, 0);
            }
        }
    }
}
