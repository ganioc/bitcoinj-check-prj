package lib.rfc.tx;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;

public class ValueTransaction extends BaseTransaction {

    private BigDecimal m_value; // String to prevent difference between JS BigNumber
    private BigDecimal m_fee;

    public ValueTransaction() {
        super();
        this.m_value = new BigDecimal(0);
        this.m_fee = new BigDecimal(0);

    }

    public BigDecimal value() {
        return this.m_value;
    }

    public void value(BigDecimal val) {
        this.m_value = val;
    }

    public void value(String val) {
        this.m_value = new BigDecimal(val);
    }

    public BigDecimal fee() {
        return this.m_fee;
    }

    public void fee(BigDecimal val) {
        this.m_fee = val;
    }

    public void fee(String val) {
        this.m_fee = new BigDecimal(val);
    }

    protected int _encodeHashContent(BufferWriter writer) {
        int err = super._encodeHashContent(writer);
        if (err != 0) {
            return err;
        }
        writer.writeBigNumber(this.m_value);
        writer.writeBigNumber(this.m_fee);
        return 0;
    }

}