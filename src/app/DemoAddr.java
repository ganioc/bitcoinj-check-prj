package app;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;

import org.bitcoinj.core.ECKey;

// import System.out.println;

public class DemoAddr {

    public static void main() {
        System.out.println("DemoAddr.main()");

    }

    public static String createKey() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecGenParameterSpec);

            // generate public key, private key
            KeyPair kp = keyPairGenerator.generateKeyPair();
            PublicKey pub = kp.getPublic();
            PrivateKey pvt = kp.getPrivate();

            ECPrivateKey epvt = (ECPrivateKey) pvt;
            // String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
            System.out.println("s[" + "]:" + epvt.getS());
            System.out.println(epvt.getS().toString(16));

            return epvt.getS().toString(16);

        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
            return null;

        } catch (InvalidAlgorithmParameterException e) {
            System.err.println(e);
            return null;
        }
    }

    public static String addressFromSecretKey(String secret) {

        BigInteger priv = new BigInteger(secret, 16);
        byte[] bytesPub = ECKey.publicKeyFromPrivate(priv, true);

        return null;
    }
}