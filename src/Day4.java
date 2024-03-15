import lib.InputUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args) throws IOException {
        List<Room> rooms = InputUtil.readAsLines("input4.txt").stream()
                .map(Room::new)
                .collect(Collectors.toList());
        first(rooms);
        second(rooms);
    }

    private static void first(List<Room> rooms) {
        int sum = rooms.stream()
                .filter(Room::isReal)
                .mapToInt(room -> room.sectorId)
                .sum();
        System.out.println(sum);
    }

    private static void second(List<Room> rooms) {
        int sectorId = rooms.stream()
                .filter(Room::isReal)
                .filter(room -> "northpole object storage".equals(room.decrypt()))
                .findFirst()
                .map(room -> room.sectorId)
                .orElseThrow();
        System.out.println(sectorId);
    }

    static class Room {
        String letters;
        int sectorId;
        String checksum;

        public Room(String s) {
            int index = s.lastIndexOf('-');
            letters = s.substring(0, index);
            String right = s.substring(index + 1);
            index = right.indexOf('[');
            sectorId = Integer.parseInt(right.substring(0, index));
            checksum = right.substring(index + 1, right.length() - 1);
        }

        public boolean isReal() {
            Map<Character, Integer> map = new HashMap<>();
            char[] charArray = letters.replace("-", "").toCharArray();
            for (char c : charArray) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
            list.sort(Map.Entry.<Character, Integer>comparingByValue().reversed()
                    .thenComparing(Map.Entry::getKey));
            String calculatedChecksum = list.stream()
                    .map(Map.Entry::getKey)
                    .limit(5)
                    .map(Object::toString)
                    .collect(Collectors.joining());
            return calculatedChecksum.equals(checksum);
        }

        public String decrypt() {
            char[] chars = letters.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                if (c == '-') {
                    sb.append(' ');
                } else {
                    int n = c - 'a';
                    n = (n + sectorId) % 26;
                    n = n + 'a';
                    sb.append((char) n);
                }
            }
            return sb.toString();
        }
    }
}
