package app;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import sun.security.provider.SecureRandom;

public class MySign {

    public static void run() {
        System.out.println("\nMySign.run()");
        try {
            // Signature signer = Signature.getInstance("EC");

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");

        } catch (NoSuchAlgorithmException e) {

        }

    }

    public static void genKey() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecGenParameterSpec, new SecureRandom());
    }
}