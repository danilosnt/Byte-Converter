package service;

public class Value {
    private int id; // ID do banco de dados
    private long byteValue;
    private int userId;

    public Value(int id, long byteValue, int userId) {
        this.id = id;
        this.byteValue = byteValue;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public long getByteValue() {
        return byteValue;
    }

    public int getUserId() {
        return userId;
    }
}
