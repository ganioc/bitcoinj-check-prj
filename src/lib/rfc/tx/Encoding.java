package lib.rfc.tx;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Encoding {
    public static Charset encodingType = StandardCharsets.UTF_8;
    public static String ONE_HASH = "0100000000000000000000000000000000000000000000000000000000000000";
    public static String ZERO_HASH = "0000000000000000000000000000000000000000000000000000000000000000";
    public static String MAX_HASH = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";
    public static String NULL_HASH = "0000000000000000000000000000000000000000000000000000000000000000";
    public static String HIGH_HASH = "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff";

    public static String textToHex(String text) {
        byte[] buf = null;
        buf = text.getBytes(Encoding.encodingType);

        char[] HEX_CHARS = "0123456789abcdef".toCharArray();

        char[] chars = new char[2 * buf.length];

        for (int i = 0; i < buf.length; i++) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
    }

    public static String hexToText(String hex) {
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        String st = new String(data, Encoding.encodingType);
        return st;
    }

    public static byte[] textToBytes(String text) {

        // System.out.println("length:" + text.length());

        if (text.length() % 2 != 0) {
            text = "0" + text;
        }
        byte[] bufOut = new byte[text.length() / 2];
        for (int i = 0; i < bufOut.length; i++) {
            // System.out.println(":" + i);
            byte hi = (byte) (Character.digit(text.charAt(i * 2), 16) & 0xff);
            byte lo = (byte) (Character.digit(text.charAt(i * 2 + 1), 16) & 0xff);

            bufOut[i] = (byte) (hi << 4 | lo);

            // System.out.println("------------");
        }

        return bufOut;
    }

    public static String bytesToText(byte[] buf) {
        String str = "";

        for (int i = 0; i < buf.length; i++) {
            // System.out.println(i + ":");
            // System.out.println(buf[i]);
            String strByte = Integer.toHexString(buf[i] & 0xff);
            if (strByte.length() < 2) {
                strByte = "0" + strByte;
            }
            str += strByte;
        }
        // if (str.length() % 2 != 0) {
        // str = "0" + str;
        // }
        return str;
    }

    // public static void test() {
    // System.out.println("\ntest");
    // String text = "df";

    // byte[] byteArray = new byte[text.length() / 2];

    // for (int i = 0; i < byteArray.length; i++) {
    // System.out.println(":" + i);
    // byte hi = (byte) (Character.digit(text.charAt(i), 16) & 0xff);
    // byte lo = (byte) (Character.digit(text.charAt(i + 1), 16) & 0xff);
    // byteArray[i] = (byte) (hi << 4 | lo);
    // System.out.println(byteArray[i]);
    // }
    // for (int i = 0; i < byteArray.length; i++) {
    // System.out.println(Integer.toHexString(byteArray[i] & 0xff));
    // }

    // }

}