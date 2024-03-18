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

    static class Node {
        final int used;
        final int avail;

        public Node(String s) {
            used = Integer.parseInt(s.substring(30, 33).trim());
            avail = Integer.parseInt(s.substring(37, 40).trim());
        }
    }
}
