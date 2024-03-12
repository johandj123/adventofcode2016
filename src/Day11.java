import lib.GraphUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 {
    public static void main(String[] args) {
        first();
        second();
    }

    private static void first() {
        Node startNode = new Node(1, List.of(
                new Pair(1, 2), // polonium
                new Pair(1, 1), // thulium
                new Pair(1, 2), // promethium
                new Pair(1, 1), // ruthenium
                new Pair(1, 1)  // cobalt
        ));
        int distance = GraphUtil.breadthFirstSearch(startNode, Node::getNeighbours, Node::isEnd);
        System.out.println(distance);
    }

    private static void second() {
        Node startNode = new Node(1, List.of(
                new Pair(1, 2), // polonium
                new Pair(1, 1), // thulium
                new Pair(1, 2), // promethium
                new Pair(1, 1), // ruthenium
                new Pair(1, 1), // cobalt
                new Pair(1, 1), // elerium
                new Pair(1, 1)  // dilithium
        ));
        int distance = GraphUtil.breadthFirstSearch(startNode, Node::getNeighbours, Node::isEnd);
        System.out.println(distance);
    }

    static class Node {
        final int elevatorFloor;
        final List<Pair> pairs;

        public Node(int elevatorFloor, List<Pair> pairs) {
            this.elevatorFloor = elevatorFloor;
            this.pairs = new ArrayList<>(pairs);
            this.pairs.sort(null);
        }

        public List<Node> getNeighbours() {
            List<Node> result = new ArrayList<>();
            List<Integer> pairsFlat = pairs.stream()
                    .flatMap(pair -> Stream.of(pair.generatorFloor, pair.microchipFloor))
                    .collect(Collectors.toList());
            List<Integer> movableIndexes = new ArrayList<>();
            for (int i = 0; i < pairsFlat.size(); i++) {
                if (pairsFlat.get(i) == elevatorFloor) {
                    movableIndexes.add(i);
                }
            }
            if (elevatorFloor > 1) {
                generateNodes(movableIndexes, pairsFlat, result, elevatorFloor - 1);
            }
            if (elevatorFloor < 4) {
                generateNodes(movableIndexes, pairsFlat, result, elevatorFloor + 1);
            }
            return result;
        }

        private void generateNodes(List<Integer> movableIndexes, List<Integer> pairsFlat, List<Node> result, int newElevatorFloor) {
            for (int i : movableIndexes) {
                for (int j : movableIndexes) {
                    List<Integer> pairsFlatCopy = new ArrayList<>(pairsFlat);
                    pairsFlatCopy.set(i, newElevatorFloor);
                    pairsFlatCopy.set(j, newElevatorFloor);
                    List<Pair> newPairs = new ArrayList<>();
                    for (int k = 0; k < pairsFlatCopy.size(); k += 2) {
                        newPairs.add(new Pair(pairsFlatCopy.get(k), pairsFlatCopy.get(k + 1)));
                    }
                    Node newNode = new Node(newElevatorFloor, newPairs);
                    if (newNode.isValid()) {
                        result.add(newNode);
                    }
                }
            }
        }

        public boolean isEnd() {
            return pairs.stream().allMatch(pair -> pair.generatorFloor == 4 && pair.microchipFloor == 4);
        }

        private boolean isValid() {
            Set<Integer> floorsWithGenerators = pairs.stream().map(pair -> pair.generatorFloor).collect(Collectors.toSet());
            for (Pair pair : pairs) {
                if (pair.generatorFloor != pair.microchipFloor && floorsWithGenerators.contains(pair.microchipFloor)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return elevatorFloor == node.elevatorFloor && Objects.equals(pairs, node.pairs);
        }

        @Override
        public int hashCode() {
            return Objects.hash(elevatorFloor, pairs);
        }
    }

    static class Pair implements Comparable<Pair> {
        final int generatorFloor;
        final int microchipFloor;

        public Pair(int generatorFloor, int microchipFloor) {
            this.generatorFloor = generatorFloor;
            this.microchipFloor = microchipFloor;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return generatorFloor == pair.generatorFloor && microchipFloor == pair.microchipFloor;
        }

        @Override
        public int hashCode() {
            return Objects.hash(generatorFloor, microchipFloor);
        }

        @Override
        public int compareTo(Pair o) {
            if (generatorFloor < o.generatorFloor) return -1;
            if (generatorFloor > o.generatorFloor) return 1;
            return Integer.compare(microchipFloor, o.microchipFloor);
        }
    }
}
