package app;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;

public class MySign {

    public static void run() {
        System.out.println("\nMySign.run()");
        try {
            // Signature signer = Signature.getInstance("EC");

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);

        } catch (NoSuchAlgorithmException e) {

        } catch (InvalidAlgorithmParameterException e) {

        }

    }

    public static void genKey() {
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

        } catch (NoSuchAlgorithmException e) {

        } catch (InvalidAlgorithmParameterException e) {

        }

    }

    public static void testBitcoinj() {

        System.out.println("test Bitcoinj ");
        String k = "1E99423A4ED27608A15A2616A2B0E9E52CED330AC530EDCC32C8FFC6A526AEDD";
        System.out.println("\npriv :" + k);

        // Converting our string encoding as an actual number
        BigInteger priv = new BigInteger(k, 16);
        // byte[] bytes1 = ECKey.publicKeyFromPrivate(priv, true);

        // ECKey k1 = new ECKey();

        // byte[] bytePub = ECKey.publicKeyFromPrivate(priv, true);

        // NetworkParameters params = new MainNetParams();
        // try {
        // String publicAddress = new DumpedPrivateKey(params,
        // k).getKey().toAddress(params).toString();
        // System.out.println("Address: " + publicAddress);
        // } catch (AddressFormatException e) {

        // }

    }

    public static void testBitcoinj2() {
        System.out.println("test bitcoinj 2");
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);

            KeyPair kp = keyGen.generateKeyPair();
            PublicKey pub = kp.getPublic();
            PrivateKey pvt = kp.getPrivate();

            // ECPrivateKey epvt = (ECPrivateKey) pvt;
            // String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
            // System.out.println("secret [" + sepvt.length() + "]: " + sepvt);

            // ECPublicKey epub = (ECPublicKey) pub;
            // ECPoint pt = epub.getW();
            // String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
            // String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
            // String bcPub = "04" + sx + sy;
            // System.out.println("bcPub: " + bcPub);

        } catch (NoSuchAlgorithmException e) {

        } catch (InvalidAlgorithmParameterException e) {

        }

    }

    static private String adjustTo64(String s) {
        switch (s.length()) {
        case 62:
            return "00" + s;
        case 63:
            return "0" + s;
        case 64:
            return s;
        default:
            throw new IllegalArgumentException("not a valid key: " + s);
        }
    }
}