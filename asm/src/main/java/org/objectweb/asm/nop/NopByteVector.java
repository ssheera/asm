package org.objectweb.asm.nop;

import org.objectweb.asm.ByteVector;

public class NopByteVector extends ByteVector {

    public NopByteVector() {
        this.data = new byte[128];
        this.length = 0;
    }

    @Override
    public NopByteVector putByte(int byteValue) {
//        byteValue = 0;
//        super.putByte(byteValue);
        return this;
    }

    public NopByteVector realByte(int byteValue) {
        super.putByte(byteValue);
        return this;
    }

    @Override
    public NopByteVector putByteArray(byte[] byteArrayValue, int byteOffset, int byteLength) {
        for (int i = 0; i < byteLength; i++) {
            this.putByte(0);
        }
        return this;
//        return super.putByteArray(byteArrayValue, byteOffset, byteLength);
    }



    @Override
    public NopByteVector put11(int byteValue1, int byteValue2) {
        putByte(byteValue1);
        putByte(byteValue2);
        return this;
    }

    @Override
    public NopByteVector put12(int byteValue, int shortValue) {
        putByte(byteValue);
        putShort(shortValue);
        return this;
    }

    @Override
    public NopByteVector put112(int byteValue1, int byteValue2, int shortValue) {
        putByte(byteValue1);
        putByte(byteValue2);
        putShort(shortValue);
        return this;
    }

    @Override
    public NopByteVector put122(int byteValue, int shortValue1, int shortValue2) {
        putByte(byteValue);
        putShort(shortValue1);
        putShort(shortValue2);
        return this;
    }

    @Override
    public NopByteVector putInt(int intValue) {
        for (int i = 0; i < 4; i++) {
            this.putByte(0);
        }
        return this;
    }

    @Override
    public NopByteVector putLong(long longValue) {
        for (int i = 0; i < 8; i++) {
            this.putByte(0);
        }
        return this;
    }

    @Override
    public NopByteVector putShort(int shortValue) {
        for (int i = 0; i < 2; i++) {
            this.putByte(0);
        }
        return this;
    }

    public void discard() {
        this.data[length] = 0;
        this.length--;
    }

    public NopByteVector real12(int byteValue, int shortValue) {
        super.put12(byteValue, shortValue);
        return this;
    }

    public NopByteVector real11(int byteValue, int byteValue1) {
        super.put11(byteValue, byteValue1);
        return this;
    }
}
