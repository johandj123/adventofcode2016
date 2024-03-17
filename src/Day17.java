import lib.GraphUtil;
import lib.InputUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Day17 {
    private static final String[] DIRECTIONS = new String[] { "U", "D", "L", "R" };
    private static final int[] DIRECTIONX = new int[] { 0, 0, -1, 1 };
    private static final int[] DIRECTIONY = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input17.txt");
        Node startNode = new Node(0, 0, input);
        String[] endPath = new String[1];
        int distance = GraphUtil.breadthFirstSearch(startNode, Node::getNeighbours, node -> {
            boolean end = node.isEnd();
            if (end) {
                endPath[0] = node.path;
            }
            return end;
        });
        System.out.println(endPath[0].substring(endPath[0].length() - distance));
    }

    static class Node {
        final int x;
        final int y;
        final String path;

        public Node(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

        public List<Node> getNeighbours() {
            String hash = md5(path);
            List<Node> result = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                char access = hash.charAt(i);
                if (access >= 'b' && access <= 'f') {
                    int nx = x + DIRECTIONX[i];
                    int ny = y + DIRECTIONY[i];
                    if (nx >= 0 && ny >= 0 && nx <= 3 && ny <= 3) {
                        result.add(new Node(nx, ny, path + DIRECTIONS[i]));
                    }
                }
            }
            return result;
        }

        private String md5(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(input.getBytes(StandardCharsets.UTF_8));
                byte[] digest = md.digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        private boolean isEnd() {
            return x == 3 && y == 3;
        }
    }
}
