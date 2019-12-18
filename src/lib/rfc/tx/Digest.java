package lib.rfc.tx;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Digest {
    public static byte[] hash256(byte[] inBytes, int offset) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = Arrays.copyOf(inBytes, offset);
            byte[] encodedhash = digest.digest(bytes);
            return encodedhash;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }
}