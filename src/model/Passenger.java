package model;

public class Passenger {
    private String name;
    private String id ;
    private  int  age;
    private Seat seat ;
    private PassengerClass type;

    public Passenger(String name, String id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.seat=null;
        this.type = PassengerClass.NORMAL_CLASS;
    }
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }
    public void setPassengerClass(PassengerClass pc){this.type = pc;}
    public PassengerClass getPassengerClass(){return this.type;}

    public Seat getSeat() {
        return this.seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
        System.out.println("\nAsignado asiento : "+seat.getNumber());
    }
    
    @Override
    public String toString() {
        return " Pasajero " +
                "nombre='" + this.name + '\'' +
                " Id=" + this.id +
                ", numeroAsiento=" + this.seat;
    }
}



