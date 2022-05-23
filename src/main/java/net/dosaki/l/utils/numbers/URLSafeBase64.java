package net.dosaki.l.utils.numbers;

public class URLSafeBase64 {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
    private static final int MAX_LENGTH = 64;

    public static String fromBase10(long number) {
        final StringBuilder base64Number = new StringBuilder();
        long remaining_value = number;
        do {
            base64Number.insert(0, CHARACTERS.charAt((int) (remaining_value % MAX_LENGTH)));
            remaining_value = remaining_value / MAX_LENGTH;
        } while (remaining_value > 0);
        return base64Number.toString();
    }

    public static long toBase10(String number) {
        return toBase10(new StringBuilder(number).reverse().toString().toCharArray());
    }

    private static long toBase10(char[] chars) {
        long result = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            result += toBase10(CHARACTERS.indexOf(chars[i]), i);
        }
        return result;
    }

    private static long toBase10(long result, int pow) {
        return result * (int) Math.pow(MAX_LENGTH, pow);
    }
}