package src.vehicleData;

public class Coordinates {

    private double x;
    private long y; //Максимальное значение поля: 746

    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Coordinates(double x, long y) {
        this.x = x;
        this.y = y;
    }

}
