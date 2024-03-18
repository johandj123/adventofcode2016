import lib.InputUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day25 {
    public static void main(String[] args) throws IOException {
        List<String> instructions = InputUtil.readAsLines("input25.txt");
        Computer computer = new Computer(instructions.stream().map(s -> s.split(" ")).collect(Collectors.toList()));
        computer.registers.put('a', 192);   // Guessed by analyzing the program
        computer.run();
    }

    static class Computer {
        final List<String[]> instructions;
        final Map<Character, Integer> registers = new HashMap<>();
        int pc = 0;

        public Computer(List<String[]> instructions) {
            this.instructions = instructions;
        }

        public void run() {
            while (0 <= pc && pc < instructions.size()) {
                String[] sp = instructions.get(pc);
                String opc = sp[0];
                if ("cpy".equals(opc)) {
                    if (!isValidInteger(sp[2])) {
                        registers.put(sp[2].charAt(0), getValue(sp[1]));
                    }
                    pc++;
                } else if ("inc".equals(opc)) {
                    registers.put(sp[1].charAt(0), getValue(sp[1]) + 1);
                    pc++;
                } else if ("dec".equals(opc)) {
                    registers.put(sp[1].charAt(0), getValue(sp[1]) - 1);
                    pc++;
                } else if ("jnz".equals(opc)) {
                    if (getValue(sp[1]) != 0) {
                        pc += getValue(sp[2]);
                    } else {
                        pc++;
                    }
                } else if ("out".equals(opc)) {
                    System.out.print(getValue(sp[1]) + " ");
                    pc++;
                } else {
                    throw new IllegalArgumentException("Unknown opcode " + opc);
                }
            }
        }

        private int getValue(String s) {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return registers.getOrDefault(s.charAt(0), 0);
            }
        }

        private boolean isValidInteger(String s) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
