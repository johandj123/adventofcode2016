import lib.InputUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day5 {
    public static void main(String[] args) throws IOException {
        String input = InputUtil.readAsString("input5.txt");
        first(input);
        second(input);
    }

    private static void first(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; sb.length() < 8; i++) {
            String hash = md5(input + i);
            if (hash.startsWith("00000")) {
                sb.append(hash.charAt(5));
            }
        }
        String password = sb.toString();
        System.out.println(password);
    }

    private static void second(String input) {
        char[] digits = new char[8];
        int filled = 0;
        for (int i = 0; filled < 8; i++) {
            String hash = md5(input + i);
            if (hash.startsWith("00000")) {
                int index = hash.charAt(5) - '0';
                if (index >= 0 && index < digits.length && digits[index] == '\0') {
                    digits[index] = hash.charAt(6);
                    filled++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : digits) {
            sb.append(c);
        }
        String password = sb.toString();
        System.out.println(password);
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
