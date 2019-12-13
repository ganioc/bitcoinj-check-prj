package lib.rfc.client;

import org.json.JSONException;
import org.json.JSONObject;

public class IfFeedback {
    public int ret = 0;
    public String resp;

    public String toString() {
        JSONObject obj = new JSONObject();

        try {
            obj.put("ret", this.ret);
            obj.put("resp", this.resp);
        } catch (JSONException e) {
            return "";
        }

        return obj.toString();

    }
}