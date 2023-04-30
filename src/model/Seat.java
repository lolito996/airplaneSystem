package model;

public class Seat {
    private int number;
    private boolean isOcupied;
    private Passenger passenger;
    public Seat(int number,boolean isOcupied){
        this.number = number;
        this.isOcupied = isOcupied;
        this.passenger = null;
    }

    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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
