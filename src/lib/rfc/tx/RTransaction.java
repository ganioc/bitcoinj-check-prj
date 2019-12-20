package lib.rfc.tx;

import java.math.BigDecimal;
import java.util.Arrays;

import org.json.JSONObject;

import lib.rfc.DemoAddr;

/**
 * Simplified ValueTransaction
 */
public class RTransaction {
    public String m_hash;
    public String m_publicKey;
    public String m_secret;
    public byte[] m_signature;
    public String m_method;
    public int m_nonce;
    public JSONObject m_input;
    private BigDecimal m_value;
    private BigDecimal m_fee;

    public RTransaction() {
        this.m_hash = Encoding.NULL_HASH;
        this.m_publicKey = Encoding.ZERO_KEY;
        this.m_signature = Digest.textToBytes(Encoding.ZERO_SIG64);
        this.m_method = "";
        this.m_nonce = -1;
        this.m_input = null;
        this.m_value = new BigDecimal(0);
        this.m_fee = new BigDecimal(0);
    }

    public void setMethod(String method) {
        this.m_method = method;
    }

    public void setNonce(int nonce) {
        this.m_nonce = nonce;
    }

    public void setInput(JSONObject input) {
        this.m_input = input;
    }

    public void setValue(BigDecimal amount) {
        this.m_value = amount;
    }

    public void setFee(BigDecimal fee) {
        this.m_fee = fee;
    }

    public byte[] encode() {
        BufferWriter writer = new BufferWriter();
        writer.writeVarString(this.m_method);
        writer.writeU32(this.m_nonce);
        writer.writeBytes(Digest.textToBytes(this.m_publicKey));
        String input = Encoding.toStringifiable(this.m_input, true);
        writer.writeVarString(input);
        writer.writeBigNumber(this.m_value);
        writer.writeBigNumber(this.m_fee);
        writer.writeBytes(this.m_signature);

        byte[] dataBuf = writer.render();
        return dataBuf;
    }

    public boolean sign(String strPri) {
        if (strPri.length() > 0) {
            this.m_publicKey = DemoAddr.publicKeyFromSecretKey(strPri);

            this.m_signature = this.updateData(strPri);

            if (this.m_signature == null) {
                return false;
            } else {
                return true;
            }
        }

        return false;
    }

    private byte[] updateData(String strPri) {
        BufferWriter writer = new BufferWriter();
        writer.writeVarString(this.m_method);
        writer.writeU32(this.m_nonce);
        writer.writeBytes(Digest.textToBytes(this.m_publicKey));
        String input = Encoding.toStringifiable(this.m_input, true);
        writer.writeVarString(input);
        writer.writeBigNumber(this.m_value);
        writer.writeBigNumber(this.m_fee);

        byte[] content = writer.render();
        byte[] byteHash = Digest.hash256(content, content.length);
        String strHash = Digest.bytesToText(byteHash);
        System.out.println(strHash);
        System.out.println("\nSignature:");
        byte[] signature = Digest.sign(strHash, strPri);

        System.out.println(Arrays.toString(Digest.bytesToInts(signature)));

        return signature;
    }
}