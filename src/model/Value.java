package service;

public class Value {
    private long byteValue;
    private int userId;

    public Value(long byteValue, int userId) {
        this.byteValue = byteValue;
        this.userId = userId;
    }

    public long getByteValue() {
        return byteValue;
    }

    public int getUserId() {
        return userId;
    }
}
