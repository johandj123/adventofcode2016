import lib.GraphUtil;
import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Day13 {
    public static void main(String[] args) throws IOException {
        int number = Integer.parseInt(InputUtil.readAsString("input13.txt"));
        first(number);
        second(number);
    }

    private static void first(int number) {
        Position startPosition = new Position(1, 1);
        Position endPosition = new Position(31, 39);
        int distance = GraphUtil.breadthFirstSearch(startPosition, position -> getNeighbours(position, number), endPosition::equals);
        System.out.println(distance);
    }

    private static void second(int number) {
        Position startPosition = new Position(1, 1);
        Set<Position> positions = GraphUtil.breadthFirstSearch(startPosition, position -> getNeighbours(position, number), 50);
        System.out.println(positions.size());
    }

    private static List<Position> getNeighbours(Position position, int number) {
        List<Position> result = new ArrayList<>();
        for (Position next : List.of(
                new Position(position.x - 1, position.y),
                new Position(position.x + 1, position.y),
                new Position(position.x, position.y - 1),
                new Position(position.x, position.y + 1)
        )) {
            if (next.x >= 0 && next.y >= 0 && !isWall(next.x, next.y, number)) {
                result.add(next);
            }
        }
        return result;
    }

    private static boolean isWall(int x, int y, int number) {
        int value = x * x + 3 * x + 2 * x * y + y + y * y + number;
        int bits = Integer.bitCount(value);
        return (bits % 2) != 0;
    }

    private static class Position {
        final int x;
        final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
