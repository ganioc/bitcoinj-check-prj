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

import org.bitcoinj.core.Address;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.params.MainNetParams;

import java.security.SecureRandom;
// import System.out.println;

public class DemoAddr {

    public static void main() {
        System.out.println("DemoAddr.main()");

    }

    /**
     * address from secret key ,
     * 
     * @param secret 32 bytes hex format
     */
    public static String addressFromSecretKey(String secret) {

        BigInteger priv = new BigInteger(secret, 16);

        ECKey key = ECKey.fromPrivate(priv);
        NetworkParameters params = new MainNetParams();
        Address addr = key.toAddress(params);

        // System.out.println("addr:");
        // System.out.println(addr);

        return addr + "";
    }

    /**
     * get public address from secret key
     * 
     * @param secret hex string format of secret
     * 
     * @return hex string format of public key
     */
    public static String publicKeyFromSecretKey(String secret) {

        BigInteger priv = new BigInteger(secret, 16);

        ECKey key = ECKey.fromPrivate(priv);

        // System.out.println(key);

        String pub = key.getPublicKeyAsHex();

        // System.out.println("pub key:" + "");
        // System.out.println(pub);

        return pub;
    }

    public static String createKey() {

        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            ECKey key = new ECKey(secureRandom);

            BigInteger pvt = key.getPrivKey();

            String strKey = adjustTo64(pvt.toString(16));

            return strKey;

        } catch (NoSuchAlgorithmException e) {
            System.err.println("createKey():" + e);
            return null;
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

    public static boolean isValidAddress(final String str) {
        NetworkParameters params = new MainNetParams();

        try {
            Address addr = Address.fromBase58(params, str);
            System.out.println("addr:");
            System.out.println(addr);
        } catch (AddressFormatException e) {
            System.err.println("Wrong addr");
            return false;
        }

        return true;
    }
}