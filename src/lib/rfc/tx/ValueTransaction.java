package lib.rfc.tx;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

import lib.rfc.DemoAddr;

public class ValueTransaction extends BaseTransaction {

    private BigDecimal m_value; // String to prevent difference between JS BigNumber
    private BigDecimal m_fee;

    public ValueTransaction() {
        super();
        this.m_value = new BigDecimal(0);
        this.m_fee = new BigDecimal(0);

    }

    public BigDecimal value() {
        return this.m_value;
    }

    public void value(BigDecimal val) {
        this.m_value = val;
    }

    public void value(String val) {
        this.m_value = new BigDecimal(val);
    }

    public BigDecimal fee() {
        return this.m_fee;
    }

    public void fee(BigDecimal val) {
        this.m_fee = val;
    }

    public void fee(String val) {
        this.m_fee = new BigDecimal(val);
    }

    public byte[] sign(String privateKey) {
        if (privateKey.length() > 0) {

            String publicKey = DemoAddr.publicKeyFromSecretKey(privateKey);
            this.setPublicKey(publicKey);
            this.updateData(privateKey);
            // update signature
            // this.m_signature = Digest.sign(this.m_hash, privateKey);
            this.updateSignature(privateKey);
        }
        return null;
    }

    protected int _encodeHashContent(BufferWriter writer) {
        int err = super._encodeHashContent(writer);
        if (err != 0) {
            return err;
        }
        writer.writeBigNumber(this.m_value);
        writer.writeBigNumber(this.m_fee);
        return 0;
    }

    public String toString() {
        JSONObject obj = super.getObject();
        try {
            obj.put("m_nonce", this.m_value);
            obj.put("m_value", this.m_value);
        } catch (JSONException e) {
            return "";
        }
        return obj.toString();
    }

    private void updateData(String privateKey) {
        BufferWriter contentWriter = new BufferWriter();

        this._encodeHashContent(contentWriter);

        byte[] bytes = contentWriter.render();
        byte[] byteHash = Digest.hash256(bytes, bytes.length);

        this.updateHash(Digest.bytesToText(byteHash));
    }
}