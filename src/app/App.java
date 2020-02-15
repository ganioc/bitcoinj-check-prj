package app;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

import lib.rfc.DemoAddr;
import lib.rfc.DemoStatus;
import lib.rfc.DemoTransfer;
import lib.rfc.client.IfFeedback;
import lib.rfc.client.IfSysinfo;
import lib.rfc.client.RPCClient;
import lib.rfc.tx.RTransaction;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");

        IfSysinfo info = new IfSysinfo();
        // info.secret =
        // "6f1df947d7942faf4110595f3aad1f2670e11b81ac9c1d8ee98806d81ec5f591";
        // info.address = "154bdF5WH3FXGo4v24F4dYwXnR8br8rc2r";
        // info.host = "161.189.65.155";

        info.secret = "560b11af06fe73674d80ebb6bcefd593711e28fbdf4cd087fefb1e17140021a0";
        info.address = "1DHozuZPpSYqsWDJtiAqdUJ6LjDe37eGx1";

        // info.host = "45.33.32.138";
        // info.host = "173.255.255.84";
        // mainnet 45.33.32.138
        info.host = "45.33.32.138";
        // info.host = "127.0.0.1";
        info.port = 18089;

        System.out.println("\nCreate client");
        RPCClient client = new RPCClient(info);

        // Test huobi sign
        // testSign();

        // Test address, key creation, address validation
        // testDemoAddr();

        // Test
        // testDemoStatus(client);

        // Test a transferTo transaction, de-serialize provided
        testDemoTransfer(client);

        System.out.println("\n-- End --");
    }

    private static void testSign() {
        System.out.println("\nTest sign");
        IfSysinfo info = new IfSysinfo();
        info.secret = "560b11af06fe73674d80ebb6bcefd593711e28fbdf4cd087fefb1e17140021a0";
        info.address = "1DHozuZPpSYqsWDJtiAqdUJ6LjDe37eGx1";
        info.host = "45.33.32.138";
        // info.host = "127.0.0.1";
        info.port = 18089;

        // System.out.println("\nCreate client");
        // RPCClient client = new RPCClient(info);
        BigDecimal amount = new BigDecimal("0.8");
        BigDecimal fee = new BigDecimal("0.1");
        int nonce = 1;
        String toAddr = new String("1HUQz27ZrW9vvi4FkNZPGHrs96PxwAojZp");
        if (!DemoAddr.isValidAddress(toAddr)) {
            System.out.println("Wrong addr:" + toAddr);
            return;
        }
        RTransaction tx = new RTransaction();
        tx.setMethod("transferTo");
        tx.setNonce(nonce);
        tx.setPublicKey(info.secret);

        JSONObject input = new JSONObject();
        try {
            input.put("to", toAddr);
        } catch (JSONException e) {
            System.out.println("JSON error input");
            throw new Error("JSON error");
        }

        tx.setInput(input);
        tx.setValue(amount);
        tx.setFee(fee);

        tx.print();

        if (!tx.sign(info.secret)) {
            System.out.println("Sign error");
            throw new Error("Sign error");

        }

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
        String addr = new String("1DHozuZPpSYqsWDJtiAqdUJ6LjDe37eGx1");
        String tx = new String("4f933ff0cd0ffd0dcab6430b9a2be64de62851406f85613b7ed8b8122035b1d4");
        IfFeedback fb2 = DemoStatus.getBalance(client, addr);
        System.out.println(fb2);

        System.out.println("\nGet latest block:");
        fb2 = DemoStatus.getBlock(client);
        System.out.println(fb2.resp);

        System.out.println("\nfind the height from block.number");

        System.out.println("\nGet block 1498:");
        fb2 = DemoStatus.getBlock(client, 1498);

        System.out.println(fb2.resp);

        System.out.println("\nGet transaction info:");
        fb2 = DemoStatus.getTransaction(client, tx);
        // System.out.println(fb2);
        System.out.println("response:" + fb2.resp);

        System.out.println("\nGet transaction info:" + tx);
        fb2 = DemoStatus.getTransaction(client, tx);
        System.out.println(fb2);

        System.out.println("\nGet nonce:");
        fb2 = DemoStatus.getNonce(client, addr);

        System.out.println(fb2);
        System.out.println(fb2.resp);

    }

    private static void testDemoTransfer(RPCClient client) {
        System.out.println("\n==================================");
        System.out.println("transferTo ");
        System.out.println("===================================");

        BigDecimal amount = new BigDecimal("0.1");
        BigDecimal fee = new BigDecimal("0.1");
        String strTo = "1HUQz27ZrW9vvi4FkNZPGHrs96PxwAojZp";

        IfFeedback fb = DemoTransfer.transferTo(client, strTo, amount, fee);

        System.out.println(fb);
        if (fb.ret != 0) {
            System.exit(-1);
        }
    }
}