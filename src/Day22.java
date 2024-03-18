import lib.InputUtil;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Day22 {
    public static void main(String[] args) throws IOException {
        List<Node> nodes = InputUtil.readAsLines("input22.txt").stream()
                .filter(s -> s.startsWith("/"))
                .map(Node::new)
                .collect(Collectors.toList());
        first(nodes);
        second(nodes);
    }

    private static void first(List<Node> nodes) {
        int count = 0;
        for (int i = 0; i < nodes.size(); i++) {
            Node a = nodes.get(i);
            if (a.used <= 0) {
                continue;
            }
            for (int j = 0; j < nodes.size(); j++) {
                if (i == j) {
                    continue;
                }
                Node b = nodes.get(j);
                if (a.used <= b.avail) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void second(List<Node> nodes) {
        int width = nodes.stream().mapToInt(node -> node.x).max().orElseThrow() + 1;
        int height = nodes.stream().mapToInt(node -> node.y).max().orElseThrow() + 1;
        Node[][] layout = new Node[height][width];
        for (Node node : nodes) {
            layout[node.y][node.x] = node;
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int size = layout[y][x].used + layout[y][x].avail;
                int used = layout[y][x].used;
                if (used == 0) {
                    System.out.printf("__/%02d ", size);
                } else if (size >= 100) {
                    System.out.print("##### ");
                } else {
                    System.out.printf("%02d/%02d ", used, size);
                }
            }
            System.out.println();
        }
        // Answer determined manually from looking at the data
        // 61 moves to get the empty node to the goal data
        // moving the target data left takes 5 moves per node, move 30 nodes
        System.out.println(61+30*5);
    }

    static class Node {
        final int x;
        final int y;
        final int used;
        final int avail;

        public Node(String s) {
            List<Integer> integers = InputUtil.extractPositiveIntegers(s);
            x = integers.get(0);
            y = integers.get(1);
            used = Integer.parseInt(s.substring(30, 33).trim());
            avail = Integer.parseInt(s.substring(37, 40).trim());
        }
    }
}
