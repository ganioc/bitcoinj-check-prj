package lib.rfc.tx;

import org.json.JSONException;
import org.json.JSONObject;

import lib.rfc.DemoAddr;

public class BaseTransaction extends SerializableWithHash {
    private String m_publicKey;
    private String m_secret;
    private byte[] m_signature;
    private String m_method;
    private int m_nonce;
    private JSONObject m_input;

    public BaseTransaction() {
        super();
        m_publicKey = "";
        m_signature = Digest.textToBytes(Encoding.ZERO_SIG64);
        m_method = "";
        m_nonce = -1;
        m_input = null;
    }

    public String address() {
        return DemoAddr.addressFromSecretKey(m_secret);
    }

    public String method() {
        return m_method;
    }

    public void method(String s) {
        m_method = s;
    }

    public long nonce() {
        return m_nonce;
    }

    public void nonce(int n) {
        m_nonce = n;
    }

    public JSONObject input() {
        return m_input;
    }

    public void input(JSONObject i) {
        m_input = i;
    }

    public boolean verifySignature() {
        if (m_publicKey.length() == 0) {
            return false;
        }
        return Digest.verify();
    }

    public byte[] sign(String privateKey) {
        if (privateKey.length() > 0) {
            m_publicKey = DemoAddr.publicKeyFromSecretKey(privateKey);
            this.updateHash();
            m_signature = Digest.sign(this.m_hash, privateKey);
        }
        return null;

    }

    public int encode(BufferWriter writer) {
        int err = super.encode(writer);
        if (err != 0) {
            return err;
        }
        writer.writeBytes(this.m_signature);
        return 0;
    }

    protected int _encodeHashContent(BufferWriter writer) {
        writer.writeVarString(this.m_method);
        writer.writeU32(this.m_nonce);
        writer.writeBytes(this.m_publicKey.getBytes());
        this._encodeInput(writer);
        return 0;
    }

    // protected _decodeHashContent(Buffer)

    // Format conversion
    // JSON object is encoded,
    protected BufferWriter _encodeInput(BufferWriter writer) {
        String input;

        if (this.m_input.length() > 0) {
            input = Encoding.toStringifiable(this.m_input, true);
        } else {
            input = "{}";
        }
        writer.writeVarString(input);
        return writer;
    }

    public JSONObject stringify() {
        JSONObject obj = super.stringify();
        try {
            obj.put("method", this.m_method);
            obj.put("input", this.m_input);
            obj.put("once", this.m_nonce);
            obj.put("caller", this.address());
        } catch (JSONException e) {
            System.err.println("Stringify error");
            System.err.println(e);
        }

        return obj;
    }
}
