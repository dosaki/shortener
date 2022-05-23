package net.dosaki.l.utils.strings;

import java.util.Base64;

public class Encoder {
    public static String toBase64(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
}
