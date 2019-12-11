package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import lib.Boy;

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

        Boy aBoy = new Boy("John");
        aBoy.shout();
        String argsext[] = { new String("ain"), new String("bin") };
        aBoy.getvalue(10, 11);
        Boy.main(argsext);
        aBoy.shout();

        aBoy.use();

        System.out.println("");

        MyThread th = new MyThread();
        th.start();

        // App.fetchFromBaidu();

        MySign.run();

    }
}