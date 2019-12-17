package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mysql.jdbc.Buffer;

import org.json.JSONException;
import org.json.JSONObject;

import lib.rfc.client.IfArgs;
import lib.rfc.client.IfFeedback;
import lib.rfc.client.IfSysinfo;
import lib.rfc.client.RPCClient;
import lib.rfc.tx.Encoding;
import lib.rfc.DemoAddr;
import lib.rfc.DemoStatus;

public class App {
    public static void fetchFromBaidu() {
        // get url
        URL url;
        try {
            url = new URL("http://www.baidu.com/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.connect();

            int code = connection.getResponseCode();

            if (code == 404) {
                throw new Exception("404");
            } else if (code == 500) {
                throw new Exception("500");
            }
            String resp = connection.getResponseMessage();
            System.out.println("resp:");
            System.out.println(resp);

            String result = null;
            InputStream in = connection.getInputStream();
            byte[] data = new byte[1024 * 1024];
            in.read(data);

            result = new String(data, "UTF-8");
            in.close();

            System.out.println(result);
        } catch (MalformedURLException e) {
            System.out.println("url exception:" + e);
        } catch (IOException e) {

        } catch (Exception e) {

        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        System.out.print("go");
        System.out.println("End");

        // float p, b;
        // double area = 0, hyp = 0;
        // InputStreamReader read = new InputStreamReader(System.in);
        // BufferedReader in = new BufferedReader(read);

        // System.out.println("Enter perpendicular and base:");
        // p = Float.parseFloat(in.readLine());
        // b = Float.parseFloat(in.readLine());

        // hyp = Math.sqrt(p * p + b * b);
        // area = (float) 1 / 2 * p * b;
        // System.out.println("Hypotenuse=" + hyp);
        // System.out.println("Area=" + area);

        int a[] = { 33, 3, 4, 5 };
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        // Boy aBoy = new Boy("John");
        // aBoy.shout();
        // String argsext[] = { new String("ain"), new String("bin") };
        // aBoy.getvalue(10, 11);
        // Boy.main(argsext);
        // aBoy.shout();

        // aBoy.use();

        // System.out.println("");

        // MyThread th = new MyThread();
        // th.start();

        // App.fetchFromBaidu();

        MySign.run();
        MySign.genKey();
        MySign.testBitcoinj();
        MySign.testBitcoinj2();

        System.out.println("Create client");

        IfSysinfo info = new IfSysinfo();
        info.secret = "6f1df947d7942faf4110595f3aad1f2670e11b81ac9c1d8ee98806d81ec5f591";
        info.address = "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r";
        info.host = "161.189.65.155";
        info.port = 18089;

        RPCClient client = new RPCClient("161.189.65.155", 18089, info);

        String funcN = "view";
        IfArgs funcArg = new IfArgs();

        funcArg.method = "getBalance";

        funcArg.params = new JSONObject();
        funcArg.params.put("address", "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r");

        System.out.println(funcN);
        System.out.println(funcArg);

        IfFeedback fb = client.callAsync(funcN, funcArg.toJSON());

        System.out.println("\nFeedback :");
        System.out.println(fb);
        System.out.println(fb.ret);
        System.out.println(fb.resp);

        DemoAddr.main();

        String secret = DemoAddr.createKey();
        if (secret == null) {
            System.err.println("create secret failed");
            System.exit(-1);
        }
        System.out.println("=========================");
        System.out.println("Secret:");
        System.out.println(secret);

        System.out.println("public key:");
        System.out.println(DemoAddr.publicKeyFromSecretKey(secret));

        System.out.println("address:");
        System.out.println(DemoAddr.addressFromSecretKey(secret));

        System.out.println("\nDo some test");
        String priv = "1E99423A4ED27608A15A2616A2B0E9E52CED330AC530EDCC32C8FFC6A526AEDD";

        System.out.println(DemoAddr.addressFromSecretKey(priv));

        System.out.println(DemoAddr.publicKeyFromSecretKey(priv));

        System.out.println("\ncreateKey()");
        String newSecret;
        System.out.println(newSecret = DemoAddr.createKey());
        System.out.println(DemoAddr.addressFromSecretKey(newSecret));

        System.out.println(DemoAddr.publicKeyFromSecretKey(newSecret));

        String[] addresses = { "1FbwA2egJnzUqifZWFggrHcDHE1mv1XEwx", "18bea6HE35DfsLy3JrGDdhgt2Lgpj6cnVk",
                "15nnW962zo4BSdaoc95humWZ1efvMGjPiH", "1FbwA2egJnzUqifZWFggrHcDHE1mv1XEwx" };

        for (int i = 0; i < addresses.length; i++) {
            System.out.print(i);
            System.out.print('-');
            System.out.println(DemoAddr.isValidAddress(addresses[i]));
        }

        for (int i = 0; i < 5; i++) {

            System.out.println(i);
            String sec = DemoAddr.createKey();
            System.out.println(sec);

            System.out.println("public key:");
            System.out.println(DemoAddr.publicKeyFromSecretKey(sec));
            System.out.println(DemoAddr.addressFromSecretKey(sec));
        }

        System.out.println(DemoAddr.isValidAddress("15nnW962zo4BSdaoc95humWZ1efvMGjPiH"));

        System.out.println("\nGet balance is:");
        IfFeedback fb2 = DemoStatus.getBalance(client, "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r");
        System.out.println(fb2);
        // System.out.println("balance:" + fb2.resp); // in string format

        System.out.println("\nGet latest block:");
        fb2 = DemoStatus.getBlock(client);
        // System.out.println(fb2);
        System.out.println("current height:" + fb2.resp);

        System.out.println("\nGet block 1498:");
        fb2 = DemoStatus.getBlock(client, 1498);
        // System.out.println(fb2);
        System.out.println("current height:" + fb2.resp);

        System.out.println("\nGet transaction info:");
        fb2 = DemoStatus.getTransaction(client, "76f18f540323e7cf78619cfb41bb38687d06a12dec0b034fa0742ced93217e5f");
        // System.out.println(fb2);
        System.out.println("response:" + fb2.resp);
        // block

        System.out.println(
                "\nGet transaction info:" + "4ed9a2c9b464da82eaa1afcb4bde8f4a07af27a9f31c8ac70d195075344022dc");
        fb2 = DemoStatus.getTransaction(client, "4ed9a2c9b464da82eaa1afcb4bde8f4a07af27a9f31c8ac70d195075344022dc");
        System.out.println(fb2);
        // System.out.println("response:" + fb2.resp);
        // block/receipt.returnCode = 0 transaction success

        System.out.println("\nGet nonce:");
        fb2 = DemoStatus.getNonce(client, "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r");

        System.out.println(fb2);

        byte[] ONE_HASH = "0100000000000000000000000000000000000000000000000000000000000000".getBytes();

        int val1 = 0x15;
        System.out.println(Integer.toHexString(val1));

        //
        System.out.println("\nbyte array:");
        byte[] mByte = Encoding.textToBytes("0100000000000000000000000000000000000000000000000000000000000000");
        for (int i = 0; i < mByte.length; i++) {
            System.out.println(mByte[i]);
        }

        System.out.println(Encoding.bytesToText(mByte));

        // Encoding.test();
    }
}