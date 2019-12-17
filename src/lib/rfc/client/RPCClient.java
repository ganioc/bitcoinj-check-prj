package lib.rfc.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class RPCClient {
    private String m_url;
    private IfSysinfo m_sysInfo;

    public RPCClient(String serveraddr, int port, IfSysinfo sysinfo) {
        this.m_url = new String("http://" + serveraddr + ":" + String.valueOf(port) + "/rpc");
        this.m_sysInfo = sysinfo;
    }

    public IfFeedback postTo(String content) {
        IfFeedback fb = new IfFeedback();

        try {
            URL url = new URL(this.m_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Content-Length", content.length() + "");

            conn.setUseCaches(false);
            conn.setDoOutput(true); // out
            conn.setDoInput(true);

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

            wr.writeBytes(content);
            wr.close();
            // wr.flush();

            //
            int code = conn.getResponseCode();
            if (code == 404) {
                throw new Exception("404");
            } else if (code == 500) {
                throw new Exception("500");
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String output;
            String out = "";

            while ((output = in.readLine()) != null) {
                out += output;
                System.out.println(output);
            }
            conn.disconnect();
            fb.ret = 0;

            fb.resp = out;
            return fb;

        } catch (IllegalStateException e) {
            fb.ret = IfFeedback.WRONG_POST;
            fb.resp = "illegal state";

        } catch (ProtocolException e) {
            fb.ret = IfFeedback.WRONG_POST;
            fb.resp = "protocol";
        } catch (MalformedURLException e) {
            fb.ret = IfFeedback.WRONG_POST;
            fb.resp = "malformed url";
        } catch (IOException e) {
            fb.ret = IfFeedback.WRONG_POST;
            fb.resp = "io";
        } catch (Exception e) {
            fb.ret = IfFeedback.WRONG_POST;
            fb.resp = "send json";
        }

        return fb;
    }

    public IfFeedback callAsync(String funcName, JSONObject funcArgs) {
        IfFeedback fb = new IfFeedback();

        JSONObject sendobj = new JSONObject();
        try {
            sendobj.put("funName", funcName);
            sendobj.put("args", funcArgs);
        } catch (JSONException e) {
            fb.ret = IfFeedback.WRONG_JSON;
            fb.resp = "NOK";
            return fb;
        }

        return postTo(sendobj.toString());

    }
}