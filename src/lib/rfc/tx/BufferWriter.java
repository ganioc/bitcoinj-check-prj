package lib.rfc.tx;

import java.util.Vector;

class WriteOp {
    public static byte SEEK = 0;
    public static byte UI8 = 1;
    public static byte UI16 = 2;
    public static byte UI16BE = 3;
    public static byte UI32 = 4;
    public static byte UI32BE = 5;
    public static byte UI64 = 6;
    public static byte UI64BE = 7;
    public static byte I8 = 10;
    public static byte I16 = 11;
    public static byte I16BE = 12;
    public static byte I32 = 13;
    public static byte I32BE = 14;
    public static byte I64 = 15;
    public static byte I64BE = 16;
    public static byte FL = 19;
    public static byte FLBE = 20;
    public static byte DBL = 21;
    public static byte DBLBE = 22;
    public static byte VARINT = 23;
    public static byte VARINT2 = 25;
    public static byte BYTES = 27;
    public static byte STR = 28;
    public static byte CHECKSUM = 29;
    public static byte FILL = 30;

    public byte type;
    // public int value;
    public int enc;
    public int size;

    public WriteOp(byte type, int enc, int size) {
        this.type = type;
        // this.value = value;
        this.enc = enc;
        this.size = size;
    }
}

class WriteOpInt extends WriteOp {
    public int value;

    public WriteOpInt(byte type, int val, int enc, int size) {
        super(type, enc, size);
        value = val;
    }
}

class WriteOpFloat extends WriteOp {
    public Float value;

    public WriteOpFloat(byte type, Float val, int enc, int size) {
        super(type, enc, size);
        value = val;
    }
}

class WriteOpDouble extends WriteOp {
    public Double value;

    public WriteOpDouble(byte type, Double val, int enc, int size) {
        super(type, enc, size);
        value = val;
    }
}

class WriteOpBytes extends WriteOp {
    public byte[] value;

    public WriteOpBytes(byte type, byte[] val, int enc, int size) {
        super(type, enc, size);
        value = new byte[val.length];
        for (int i = 0; i < val.length; i++) {
            value[i] = val[i];
        }
    }
}

public class BufferWriter {

    private Vector<WriteOp> ops;
    private int offset;

    public BufferWriter() {
        ops = new Vector<WriteOp>();
        offset = 0;
    }

    public int getSize() {
        return offset;
    }

    public void seek(int mOffset) {
        offset += mOffset;
        ops.add(new WriteOpInt(WriteOp.SEEK, mOffset, 0, 0));
    }

    public void destroy() {
        ops.clear();
        offset = 0;
    }

    public byte[] render() {
        byte[] data = new byte[offset];
        int off = 0;

        return data;
    }

    private void writeU8(int value) {
        offset += 1;
        ops.add(new WriteOpInt(WriteOp.UI8, value, 0, 0));
    }

    private void writeU16(int value) {
        offset += 2;
        ops.add(new WriteOpInt(WriteOp.UI16, value, 0, 0));
    }

    private void writeU16BE(int value) {
        offset += 2;
        ops.add(new WriteOpInt(WriteOp.UI16BE, value, 0, 0));

    }

    private void writeU32(int value) {
        offset += 4;
        ops.add(new WriteOpInt(WriteOp.UI32, value, 0, 0));

    }

    private void writeU32BE(int value) {
        offset += 4;
        ops.add(new WriteOpInt(WriteOp.UI32BE, value, 0, 0));

    }

    private void writeU64(int value) {
        offset += 8;
        ops.add(new WriteOpInt(WriteOp.UI64, value, 0, 0));

    }

    private void writeU64BE(int value) {
        offset += 8;
        ops.add(new WriteOpInt(WriteOp.UI64BE, value, 0, 0));

    }

    private void writeI8(int value) {
        this.offset += 1;
        ops.add(new WriteOpInt(WriteOp.I8, value, 0, 0));
    }

    private void writeI16(int value) {
        this.offset += 2;
        ops.add(new WriteOpInt(WriteOp.I16, value, 0, 0));
    }

    private void writeI16BE(int value) {
        this.offset += 2;
        ops.add(new WriteOpInt(WriteOp.I16BE, value, 0, 0));
    }

    private void writeI32(int value) {
        this.offset += 4;
        ops.add(new WriteOpInt(WriteOp.I32, value, 0, 0));
    }

    private void writeI32BE(int value) {
        this.offset += 4;
        ops.add(new WriteOpInt(WriteOp.I32BE, value, 0, 0));
    }

    private void writeI64(int value) {
        this.offset += 8;
        ops.add(new WriteOpInt(WriteOp.I64, value, 0, 0));
    }

    private void writeI64BE(int value) {
        this.offset += 8;
        ops.add(new WriteOpInt(WriteOp.I64BE, value, 0, 0));
    }

    private void writeFloat(Float value) {
        this.offset += 4;
        ops.add(new WriteOpFloat(WriteOp.FL, value, 0, 0));
    }

    private void writeFloatBE(Float value) {
        this.offset += 4;
        ops.add(new WriteOpFloat(WriteOp.FLBE, value, 0, 0));
    }

    private void writeOpDouble(Double value) {
        this.offset += 4;
        ops.add(new WriteOpDouble(WriteOp.DBL, value, 0, 0));
    }

    private void writeOpDoubleBE(Double value) {
        this.offset += 4;
        ops.add(new WriteOpDouble(WriteOp.DBLBE, value, 0, 0));
    }

    // private void WriteVariant(int value) {
    // this.offset += 4;
    // ops.add(new WriteOpDouble(WriteOp.DBL, value, 0, 0));
    // }
    private void writeBytes(byte[] value) {
        if (value.length == 0) {
            return;
        }
        offset += value.length;
        ops.add(new WriteOpBytes(WriteOp.BYTES, value, 0, 0));
    }

    private void writeBigNumber(byte[] value) {
        writeBytes(value);
    }

    private void copy(byte[] value, int start, int end) {
        assert (end >= start);
        byte[] bytes = new byte[end - start];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = value[start + i];
        }
        writeBytes(bytes);
    }

    private void writeString(String value) {
        byte[] bytes = value.getBytes();
        writeBytes(bytes);
    }

    private void writeNullString(String value) {
        writeString(value);
        writeU8(0);
    }

    private void writeChecksum() {
        offset += 4;
        ops.add(new WriteOpInt(WriteOp.CHECKSUM, 0, 0, 0));
    }

    private void fill(int value, int size) {
        assert (size >= 0);

        if (size == 0) {
            return;
        }
        offset += size;
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = (byte) (value & 0xff);
        }
        writeBytes(bytes);
    }

}