package model;

public class Passenger {
    private String name;
    private String id ;
    private  int  age;
    private int seat ;

    public Passenger(String name, String id, int age,int seat) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.seat=seat;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSeat() {
        return this.seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
    
    @Override
    public String toString() {
        return " Pasajero " +
                "nombre='" + this.name + '\'' +
                " Id=" + this.id +
                ", numeroAsiento=" + this.seat;
    }
}



