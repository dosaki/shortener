package net.dosaki.l.utils.mocked;

import net.dosaki.l.utils.strings.Encoder;

public class PersistentAuthHandler {
    /* These would probably be held in a persistence layer elsewhere */
    private static final String mockCredentials = "admin:password";
    private static final String mockToken = "fN8k2jQ3bYdk6Hs0uclW";

    public static Boolean verifyCredentials(String credentials){
        return credentials.equals(mockCredentials) || credentials.equals(Encoder.toBase64(mockCredentials));
    }

    public static Boolean verifyToken(String token) {
        return token.equals(mockToken);
    }

    public static String fetchToken() {
        return mockToken;
    }
}
