import lib.CharMatrix;
import lib.GraphUtil;
import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Day24 {
    public static void main(String[] args) throws IOException {
        CharMatrix charMatrix = new CharMatrix(InputUtil.readAsLines("input24.txt"));
        CharMatrix.Position startPosition = charMatrix.find('0').orElseThrow();
        int endMask = determineEndMask(charMatrix);
        first(startPosition, endMask);
        second(startPosition, endMask);
    }

    private static void first(CharMatrix.Position startPosition, int endMask) {
        int distance = GraphUtil.breadthFirstSearch(new Node(startPosition, 1), Node::getNeighbours, node -> node.mask == endMask);
        System.out.println(distance);
    }

    private static void second(CharMatrix.Position startPosition, int endMask) {
        int distance = GraphUtil.breadthFirstSearch(new Node(startPosition, 1), Node::getNeighbours, node -> startPosition.equals(node.position) &&  node.mask == endMask);
        System.out.println(distance);
    }

    private static int determineEndMask(CharMatrix charMatrix) {
        int endMask = 0;
        for (int y = 0; y < charMatrix.getHeight(); y++) {
            for (int x = 0; x < charMatrix.getWidth(); x++) {
                char c = charMatrix.get(x, y);
                if (c >= '0' && c <= '9') {
                    endMask |= (1 << (c - '0'));
                }
            }
        }
        return endMask;
    }

    static class Node {
        final CharMatrix.Position position;
        final int mask;

        public Node(CharMatrix.Position position, int mask) {
            this.position = position;
            this.mask = mask;
        }

        public List<Node> getNeighbours() {
            List<Node> result = new ArrayList<>();
            for (var next : position.getNeighbours()) {
                char c = next.get();
                if (c != '#') {
                    int nextMask = mask;
                    if (c >= '0' && c <= '9') {
                        nextMask |= ((1 << (c - '0')));
                    }
                    result.add(new Node(next, nextMask));
                }
            }
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return mask == node.mask && Objects.equals(position, node.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, mask);
        }
    }
}
