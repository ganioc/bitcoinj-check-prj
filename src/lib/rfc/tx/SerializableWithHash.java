package lib.rfc.tx;

import org.json.JSONException;
import org.json.JSONObject;

class SerializableWithHash {

    private String m_hash;

    public SerializableWithHash() {
        m_hash = Encoding.NULL_HASH;
    }

    public JSONObject getObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("m_hash", this.m_hash);
        } catch (JSONException e) {
            System.err.println("SerializableWithHash error");
            return obj;
        }
        return obj;
    }

    public String hash() {
        return m_hash;
    }

    public void hash(String s) {
        m_hash = s;
    }

    // public int encode(BufferWriter writer) {
    // return _encodeHashContent(writer);
    // }

    // public int decode() {
    // this.updateHash();
    // return _decodeHashContent();
    // }

    // public void updateHash() {
    // this.m_hash = this._genHash();
    // }

    // protected int _encodeHashContent(BufferWriter writer) {
    // return 0;
    // }

    // protected int _decodeHashContent() {
    // return 0;
    // }

    // protected String _genHash() {
    // BufferWriter contentWriter = new BufferWriter();

    // this._encodeHashContent(contentWriter);

    // byte[] bytes = contentWriter.render();
    // byte[] byteHash = Digest.hash256(bytes, bytes.length);

    // return Digest.bytesToText(byteHash);
    // }

    // protected boolean _verifyHash() {
    // return this.hash() == this._genHash();
    // }

    public JSONObject stringify() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("hash", m_hash);
        } catch (JSONException e) {
            System.err.println("JSON");
            return obj;
        }
        return obj;
    }
}