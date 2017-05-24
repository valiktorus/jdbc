package by.gsu.epamlab.beans;

public class NumLen {
    private final int len;
    private final int num;

    public NumLen(int len, int num) {
        this.len = len;
        this.num = num;
    }

    public int getLen() {
        return len;
    }

    public int getNum() {
        return num;
    }

    @Override
    public String toString() {
        return String.format("%d;%d%n", len, num);
    }
}
