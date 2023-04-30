package model;

public class Seat {
    private int number;
    private boolean isOcupied;
    public Seat(int number,boolean isOcupied){
        this.number = number;
        this.isOcupied = isOcupied;
    }
    public int getNumber() {
        return number;
    }
    public boolean isOcupied() {
        return isOcupied;
    }
    public void setOcupied(boolean ocupied) {
        isOcupied = ocupied;
    }
}
