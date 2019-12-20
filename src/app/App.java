package app;

import java.math.BigDecimal;

import lib.rfc.DemoAddr;
import lib.rfc.DemoStatus;
import lib.rfc.DemoTransfer;
import lib.rfc.client.IfFeedback;
import lib.rfc.client.IfSysinfo;
import lib.rfc.client.RPCClient;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        IfSysinfo info = new IfSysinfo();
        info.secret = "6f1df947d7942faf4110595f3aad1f2670e11b81ac9c1d8ee98806d81ec5f591";
        info.address = "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r";
        info.host = "161.189.65.155";
        info.port = 18089;

        System.out.println("\nCreate client");
        RPCClient client = new RPCClient(info);

        // testDemoAddr();

        // testDemoStatus(client);

        testDemoTransfer(client);

        System.out.println("\n-- End --");
    }

    private static void testDemoAddr() {
        System.out.println("\n==================================");
        System.out.println("Address creation, key generation");
        System.out.println("===================================");

        String secret = DemoAddr.createKey();
        if (secret == null) {
            System.err.println("create secret failed");
            System.exit(-1);
        }

        System.out.print("Secret:  ");
        System.out.println(secret);

        System.out.print("public key:  ");

        String strPubKey = DemoAddr.publicKeyFromSecretKey(secret);
        System.out.println(strPubKey);

        System.out.print("address:  ");
        System.out.println(DemoAddr.addressFromSecretKey(secret));

    }

    private static void testDemoStatus(RPCClient client) {
        System.out.println("\n==================================");
        System.out.println("Get status from chain");
        System.out.println("===================================");
        System.out.println("\nGet balance is: ");
        IfFeedback fb2 = DemoStatus.getBalance(client, "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r");
        System.out.println(fb2);

        System.out.println("\nGet latest block:");
        fb2 = DemoStatus.getBlock(client);
        System.out.println(fb2.resp);

        System.out.println("\nfind the height from block.number");

        System.out.println("\nGet block 1498:");
        fb2 = DemoStatus.getBlock(client, 1498);

        System.out.println(fb2.resp);

        System.out.println("\nGet transaction info:");
        fb2 = DemoStatus.getTransaction(client, "76f18f540323e7cf78619cfb41bb38687d06a12dec0b034fa0742ced93217e5f");
        // System.out.println(fb2);
        System.out.println("response:" + fb2.resp);

        System.out.println(
                "\nGet transaction info:" + "4ed9a2c9b464da82eaa1afcb4bde8f4a07af27a9f31c8ac70d195075344022dc");
        fb2 = DemoStatus.getTransaction(client, "4ed9a2c9b464da82eaa1afcb4bde8f4a07af27a9f31c8ac70d195075344022dc");
        System.out.println(fb2);

        System.out.println("\nGet nonce:");
        fb2 = DemoStatus.getNonce(client, "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r");

        System.out.println(fb2);
        System.out.println(fb2.resp);

    }

    private static void testDemoTransfer(RPCClient client) {
        System.out.println("\n==================================");
        System.out.println("transferTo ");
        System.out.println("===================================");

        BigDecimal amount = new BigDecimal("100");
        BigDecimal fee = new BigDecimal("0.1");
        String strTo = "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r";

        IfFeedback fb = DemoTransfer.transferTo(client, strTo, amount, fee);

        System.out.println(fb);
        if (fb.ret != 0) {
            System.exit(-1);
        }

        // check
    }
}