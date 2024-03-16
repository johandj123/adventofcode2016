import lib.InputUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class Day14 {
    public static void main(String[] args) throws IOException {
        String salt = InputUtil.readAsString("input14.txt");
        run(salt, Day14::md5);
        run(salt, Day14::stretchedHash);
    }

    private static void run(String salt, Function<String, String> hashFunction) {
        List<String> keys = new ArrayList<>();
        int index = 0;
        Deque<String> hashes = new ArrayDeque<>();
        while (keys.size() < 64) {
            if ((index % 1000) == 0) {
                System.out.println("..." + index + " (" + keys.size() + " keys)");
            }
            while (hashes.size() < 1001) {
                hashes.offerLast(hashFunction.apply(salt + (index + hashes.size())));
            }
            String potentialKey = hashes.removeFirst();
            index++;
            if (isKey(potentialKey, hashes)) {
                keys.add(potentialKey);
            }
        }
        System.out.println(index - 1);
    }

    private static boolean isKey(String potentialKey, Deque<String> hashes) {
        char triplet = '\0';
        for (int i = 0; i < potentialKey.length() - 2; i++) {
            if (potentialKey.charAt(i) == potentialKey.charAt(i + 1) &&
                    potentialKey.charAt(i + 1) == potentialKey.charAt(i + 2)) {
                triplet = potentialKey.charAt(i);
                break;
            }
        }
        if (triplet == '\0') {
            return false;
        }
        String five = "" + triplet + triplet + triplet + triplet + triplet;
        return hashes.stream().anyMatch(s -> s.contains(five));
    }

    private static String stretchedHash(String input) {
        String s = input;
        for (int i = 0; i < 2017; i++) {
            s = md5(s);
        }
        return s;
    }

    private static String md5(String input) {
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
}
