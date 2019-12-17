package lib.rfc.client;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * ret 0 succeed other num, fail resp json in string
 * 
 */
public class IfFeedback {
    public int ret = 0;
    public String resp;

    public static int OK = 0;
    public static int WRONG_ADDRESS = -1;
    public static int WRONG_JSON = -2;
    public static int WRONG_AMOUNT = -3;
    public static int WRONG_FEE = -4;
    public static int WRONG_POST = -100;
    public static int WRONG_RPC_RETURN = -200;

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