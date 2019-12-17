package lib.rfc.tx;

import org.json.JSONException;
import org.json.JSONObject;

interface JSONable {
    String stringify();
}

interface Serializable {
    boolean encode();

    boolean decode();
}

class SerializableWithHash {
    private String m_hash;

    public SerializableWithHash() {
        m_hash = Encoding.NULL_HASH;
    }

    public String stringify() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("hash", m_hash);
        } catch (JSONException e) {
            System.err.println("JSON");
            return "";
        }
        return obj.toString();
    }

    public boolean encode() {
        // return _encodeHashContent();
        return false;
    }

    public boolean decode() {

        return false;
    }

}

public class ValueTransaction {

}