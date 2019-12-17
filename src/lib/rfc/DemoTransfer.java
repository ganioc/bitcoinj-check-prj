package lib.rfc;

import lib.rfc.client.IfFeedback;
import lib.rfc.client.RPCClient;

public class DemoTransfer {
    public static IfFeedback transferTo(RPCClient client, String toAddr, String amount, String fee) {
        IfFeedback fb = new IfFeedback();

        if (!DemoAddr.isValidAddress(toAddr)) {
            fb.ret = IfFeedback.WRONG_ADDRESS;
            fb.resp = "Wrong address";
            return fb;
        }

        if (!DemoAddr.isValidAmount(amount)) {
            fb.ret = IfFeedback.WRONG_AMOUNT;
            fb.resp = "Wrong amount";
            return fb;
        }

        if (!DemoAddr.isValidFee(fee)) {
            fb.ret = IfFeedback.WRONG_FEE;
            fb.resp = "Wrong fee";
            return fb;
        }

        return fb;
    }
}