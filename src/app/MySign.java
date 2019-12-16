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

import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.DumpedPrivateKey;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

public class MySign {

    public static void run() {
        System.out.println("\nMySign.run()");
        try {
            // Signature signer = Signature.getInstance("EC");

            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            final ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);

        } catch (final NoSuchAlgorithmException e) {

        } catch (final InvalidAlgorithmParameterException e) {

        }

    }

    public static void genKey() {
        try {
            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            final ECGenParameterSpec ecGenParameterSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecGenParameterSpec);

            // generate public key, private key
            final KeyPair kp = keyPairGenerator.generateKeyPair();
            final PublicKey pub = kp.getPublic();
            final PrivateKey pvt = kp.getPrivate();

            final ECPrivateKey epvt = (ECPrivateKey) pvt;
            // String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
            System.out.println("s[" + "]:" + epvt.getS());
            System.out.println(epvt.getS().toString(16));

        } catch (final NoSuchAlgorithmException e) {

        } catch (final InvalidAlgorithmParameterException e) {

        }

    }

    public static void testBitcoinj() {

        System.out.println("test Bitcoinj ");
        String k = "1E99423A4ED27608A15A2616A2B0E9E52CED330AC530EDCC32C8FFC6A526AEDD";
        System.out.println("\npriv :" + k);

        // Converting our string encoding as an actual number
        BigInteger priv = new BigInteger(k, 16);
        // final byte[] bytes1 = ECKey.publicKeyFromPrivate(priv, true);

        // // ECKey k1 = new ECKey();

        // final byte[] bytePub = ECKey.publicKeyFromPrivate(priv, true);

        NetworkParameters params = new MainNetParams();
        try {
            String publicAddress = new DumpedPrivateKey(params, k).getKey().toAddress(params).toString();
            System.out.println("Address: " + publicAddress);
        } catch (AddressFormatException e) {
            System.err.println("Exception e:" + e);
        }

    }

    public static void testBitcoinj2() {
        System.out.println("test bitcoinj 2");
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            final ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec);

            final KeyPair kp = keyGen.generateKeyPair();
            final PublicKey pub = kp.getPublic();
            final PrivateKey pvt = kp.getPrivate();

            // ECPrivateKey epvt = (ECPrivateKey) pvt;
            // String sepvt = adjustTo64(epvt.getS().toString(16)).toUpperCase();
            // System.out.println("secret [" + sepvt.length() + "]: " + sepvt);

            // ECPublicKey epub = (ECPublicKey) pub;
            // ECPoint pt = epub.getW();
            // String sx = adjustTo64(pt.getAffineX().toString(16)).toUpperCase();
            // String sy = adjustTo64(pt.getAffineY().toString(16)).toUpperCase();
            // String bcPub = "04" + sx + sy;
            // System.out.println("bcPub: " + bcPub);

        } catch (final NoSuchAlgorithmException e) {

        } catch (final InvalidAlgorithmParameterException e) {

        }

    }

    static private String adjustTo64(final String s) {
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