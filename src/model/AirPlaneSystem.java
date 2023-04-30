package model;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class AirPlaneSystem {
    private File result;
    private PlaneState state;
    private HashTable<Passenger,String> hash;
    private ArrayList<Passenger> passengers;
    private ArrayList<Passenger> listTemp;
    private Queue<Passenger,String> normalQueue;
    private Queue<Passenger,String> firstClassQueue;
    private ArrayList<Seat> normalSeats;
    private ArrayList<Seat> firstClassSeats;

    public AirPlaneSystem(){
        passengers = new ArrayList<>();
        listTemp = new ArrayList<>();
        state = PlaneState.WAITING;
        normalQueue = new Queue<>();
        firstClassQueue = new Queue<>();
        normalSeats = new ArrayList<>();
        firstClassSeats = new ArrayList<>();
        createDataFile();
        uploadDataPassenger();
        System.out.println(passengers.get(10).getName());
        passengerHashLoad();
        setSeatList();

    }
    public ArrayList<Passenger> getPassengers(){
        return passengers;
    }
    public ArrayList<Passenger> getListTemp(){
        listTemp = passengers;
       return listTemp;
    }
    public void setPlaneState(){
        if(state.equals(PlaneState.WAITING)){
            state = PlaneState.LANDED;
        }else{
            state = PlaneState.WAITING;
        }
    }
    public PlaneState getPlaneState(){return state;}
    private void createDataFile(){
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir.getAbsolutePath() + "/data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        result = new File(dataDirectory, "result.txt");
    }
    private void uploadDataPassenger(){
        try{
            FileInputStream fis = new FileInputStream(result);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = "";
            StringBuilder msj = new StringBuilder();
            while((line = reader.readLine())!=null){
                msj.append(line);
            }
            fis.close();
            Gson gson = new Gson();
            Passenger[] array = gson.fromJson(msj.toString(),Passenger[].class);
            for(Passenger p:array){
                passengers.add(p);
                p.setPassengerClass(PassengerClass.NORMAL_CLASS);
            }
        }catch(Exception ex){
            ex.printStackTrace();
            //Ya que creamos el archivo antes, nunca va a pasar que no lo encuentra;
        }
    }
    public String printPassengers(){
        StringBuilder msj = new StringBuilder();
        msj.append("  NAME   |   ID   |   AGE");
        for(Passenger p:passengers){
            msj.append("\n "+p.getName()+" | "+p.getId()+" | "+p.getAge());
        }
        return msj.toString();
    }
    private void setSeatList(){
        //lista de asientos de clase económica
        for(int i=19;i<=54;i++){
            normalSeats.add(new Seat(i,false));
        }
        //lista de asientos de primera Clase
        for(int i=1;i<=18;i++){
            firstClassSeats.add(new Seat(i,false));
        }
    }
    private void passengerHashLoad(){
        hash = new HashTable<>();
        for(int i=0;i<passengers.size();i++){
            hash.add(passengers.get(i),passengers.get(i).getId());
        }
    }
    public int passengerComes(){
        if(passengers.size()==0){
            return -1;
        }
        int pos = (int)Math.floor(Math.random()*passengers.size()); //Selecciona un pasajero aleatorio
        return pos;
    }
    public boolean validatePassenger(Passenger passenger,int pos){
        if(hash.search(passenger.getId())!=null){
            passengers.remove(pos);
            orderPassenger(passenger);
            setPassengerSeat(passenger,pos);
            return true;
        }else{
            return false;
        }

    }
    private void setPassengerSeat(Passenger passenger,int pos){
        if(passenger.getClass().equals(PassengerClass.FIRST_CLASS)){
            int seatPos = (int)Math.floor(Math.random()*(firstClassSeats.size()));
            Seat seat = firstClassSeats.get(seatPos);
            firstClassSeats.remove(seatPos);
            passengers.get(pos).setSeat(seat);
        }else if(passenger.getPassengerClass().equals(PassengerClass.NORMAL_CLASS)){
            int seatPos = (int)Math.floor(Math.random()*(normalSeats.size()));
            Seat seat = normalSeats.get(seatPos);
            normalSeats.remove(seatPos);
            passengers.get(pos).setSeat(seat);
        }
    }
    private void orderPassenger(Passenger passenger){
        if(passenger.getClass().equals(PassengerClass.FIRST_CLASS)){
            firstClassQueue.enqueue(passenger,passenger.getId());
        }else if(passenger.getPassengerClass().equals(PassengerClass.NORMAL_CLASS)){
            normalQueue.enqueue(passenger,passenger.getId());
        }else{

        }
    }
    //Este metodo se eliminara para el prototipo final
    public void createPassengerList() throws IOException {
        passengers.add(new Passenger("Alejandro Muñoz", generateRandomId(), 18,PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Samuel Ibarra", generateRandomId(), 20, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Michael Brown", generateRandomId(), 43, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Emily Davis", generateRandomId(), 19, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("David Wilson", generateRandomId(), 57, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Sophia Garcia", generateRandomId(), 23, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("William Martinez", generateRandomId(), 35, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Elizabeth Hernandez", generateRandomId(), 41, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("James Lee", generateRandomId(), 50, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Mia Nguyen", generateRandomId(), 29, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Christopher Jones", generateRandomId(), 38, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Ava Martin", generateRandomId(), 27, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Daniel Davis", generateRandomId(), 31, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Emma Wilson", generateRandomId(), 22, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Sarah Martinez", generateRandomId(), 45, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Alexander Johnson", generateRandomId(), 49, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Olivia Garcia", generateRandomId(), 20, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Matthew Brown", generateRandomId(), 28, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Isabella Lee", generateRandomId(), 36, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Joseph Smith", generateRandomId(), 39, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Samantha Nguyen", generateRandomId(), 26, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Andrew Wilson", generateRandomId(), 33, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Madison Hernandez", generateRandomId(), 30, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Joshua Davis", generateRandomId(), 24, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Chloe Martin", generateRandomId(), 54, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Liam Baker", generateRandomId(), 25, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Avery Williams", generateRandomId(), 32, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Evelyn Cooper", generateRandomId(), 43, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Benjamin Davis", generateRandomId(), 19, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Lila Edwards", generateRandomId(), 57, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Logan Flores", generateRandomId(), 23, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Mia Garcia", generateRandomId(), 35, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Owen Hall", generateRandomId(), 41, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Aria Hill", generateRandomId(), 50, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Caleb Johnson", generateRandomId(), 29, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Aaliyah Jones", generateRandomId(), 38, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Carson Lewis", generateRandomId(), 27, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Madison Mitchell", generateRandomId(), 31, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Abigail Perez", generateRandomId(), 22, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Leo Reed", generateRandomId(), 45, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Nora Richardson", generateRandomId(), 49, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Wyatt Roberts", generateRandomId(), 15, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Hazel Scott", generateRandomId(), 28, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Grayson Taylor", generateRandomId(), 36, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Aurora Thomas", generateRandomId(), 39, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Ethan Turner", generateRandomId(), 26, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Harper Walker", generateRandomId(), 33, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Levi White", generateRandomId(), 30, PassengerClass.NORMAL_CLASS));
        passengers.add(new Passenger("Amelia Young", generateRandomId(), 24, PassengerClass.FIRST_CLASS));
        passengers.add(new Passenger("Isaac Wright", generateRandomId(), 21, PassengerClass.NORMAL_CLASS));

        saveList();
    }
    //eliminar en el proto final
    private String generateRandomId() {
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000;
        return Integer.toString(randomNum);
    }
    //No necesarios por el momento, eliminar en el proto final
    private void saveList() throws IOException{
        FileOutputStream fos = new FileOutputStream(result);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        Gson gson = new Gson();
        String data = gson.toJson(passengers);
        writer.write(data);
        writer.flush();
        fos.close();
    }
}
