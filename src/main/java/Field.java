public class Field {

    private boolean isO;
    private boolean isX;

    private int number;

    private int value;

    public Field(int number, int value) {
        this.number = number;
        this.value = value;
    }

    public Field(Field field) {
        this.number = field.number;
        this.value = field.value;
        this.isO = field.isO;
        this.isX = field.isX;
    }

    public boolean isO() {
        return isO;
    }

    public void setO(boolean o) {
        isO = o;
    }

    public boolean isX() {
        return isX;
    }

    public void setX(boolean x) {
        isX = x;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field{" +
                "isO=" + isO +
                ", isX=" + isX +
                ", number=" + number +
                '}';
    }
}
