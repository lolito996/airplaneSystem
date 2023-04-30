package model;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class AirPlaneSystem {
    public static final int arrows = 9;
    public static final int columns = 6;
    public static final int totalSeatsVip = 18;

    public static final int totalSeatsNormal = 36;
    private File result;
    private PlaneState state;
    private HashTable<Passenger,String> hash;
    private ArrayList<Passenger> passengers;
    private ArrayList<Passenger> listTemp;
    private ArrayList<Seat> seats;
    private Queue<Passenger,String> normalQueue;
    private Queue<Passenger,String> firstClassQueue;
    private ArrayList<Seat> normalSeats;
    private ArrayList<Seat> firstClassSeats;
    private Queue<Passenger,String> planeQueue;

    public AirPlaneSystem(){
        passengers = new ArrayList<>();
        listTemp = new ArrayList<>();
        state = PlaneState.WAITING;
        normalQueue = new Queue<>();
        firstClassQueue = new Queue<>();
        planeQueue = new Queue<>();
        normalSeats = new ArrayList<>();
        firstClassSeats = new ArrayList<>();
        seats = new ArrayList<>(54);
        createDataFile();
        uploadDataPassenger();
        listTemp = passengers;
        passengerHashLoad();
        setSeatList();

    }
    public ArrayList<Passenger> getPassengers(){
        return passengers;
    }
    public ArrayList<Passenger> getListTemp(){
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
        //lista de asientos de primera Clase
        for(int i=1;i<=18;i++){
            firstClassSeats.add(new Seat(i,false));
            seats.add(new Seat(i,false));
        }
        //lista de asientos de clase económica
        for(int i=19;i<=54;i++){
            normalSeats.add(new Seat(i,false));
            seats.add(new Seat(i,false));
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
            orderPassenger(passenger);
            setPassengerSeat(passenger,pos);
            return true;
        }else{
            return false;
        }
    }
    private void setPassengerSeat(Passenger passenger,int pos){
        if(passenger.getPassengerClass().equals(PassengerClass.FIRST_CLASS)){
            int seatPos = (int)Math.floor(Math.random()*(firstClassSeats.size()));
            Seat seat = firstClassSeats.get(seatPos);
            firstClassSeats.remove(seatPos);
            passengers.get(pos).setSeat(seat);
            seats.get(seat.getNumber()-1).setPassenger(passenger);

        }else if(passenger.getPassengerClass().equals(PassengerClass.NORMAL_CLASS)){
            int seatPos = (int)Math.floor(Math.random()*(normalSeats.size()));
            Seat seat = normalSeats.get(seatPos);
            normalSeats.remove(seatPos);
            passengers.get(pos).setSeat(seat);
            seats.get(seat.getNumber()-1).setPassenger(passenger);
        }
        passengers.remove(pos);
    }
    public String getOrderedPassengers(){
        StringBuilder msj = new StringBuilder();
        msj.append("\nOrden de Abordaje :");
        int cont = 0;
        while(!normalQueue.isEmpty()){
            cont++;
            Passenger passenger = normalQueue.dequeue();
            planeQueue.enqueue(passenger,passenger.getId());
            seats.get(passenger.getSeat().getNumber()-1).setOcupied(true);
            msj.append("\n"+cont+" ) "+passenger.getName()+"  ::  "+passenger.getPassengerClass()+"  ::  Seat :"+passenger.getSeat().getNumber());
        }
        while(!firstClassQueue.isEmpty()){
            cont++;
            Passenger passenger = firstClassQueue.dequeue();
            planeQueue.enqueue(passenger,passenger.getId());
            seats.get(passenger.getSeat().getNumber()-1).setOcupied(true);
            msj.append("\n"+cont+" ) "+passenger.getName()+"  ::  "+passenger.getPassengerClass()+"  ::  Seat :"+passenger.getSeat().getNumber());
        }
        return msj.toString();
    }
    private void orderPassenger(Passenger passenger){
        if(passenger.getPassengerClass().equals(PassengerClass.FIRST_CLASS)){
            firstClassQueue.enqueue(passenger,passenger.getId());
        }else if(passenger.getPassengerClass().equals(PassengerClass.NORMAL_CLASS)){
            normalQueue.enqueue(passenger,passenger.getId());
        }
    }
    public String disboardOrder(){
        HNode<String,Integer> node = new HNode<>("",-1);
        StringBuilder msj = new StringBuilder();
        msj.append("\nOrden de Salida de los Pasajeros :\n");
        int cont = 0;
        boolean flag = true;
        for(int i=0;i<seats.size();i++){
            if(flag){;
                node = reversePrint(i,cont);
                msj.append(node.getElement());
                cont = node.getKey();
                i+=2;
                flag = false;
            }else{
                node = normalPrint(i,cont);
                msj.append(node.getElement());
                cont = node.getKey();
                i+=2;
                flag = true;
            }
        }
        return msj.toString();
    }
    private HNode<String,Integer> reversePrint(int pos, int cont){
        HNode<String,Integer> node = new HNode<>("",-1);
        StringBuilder msj = new StringBuilder();
        Passenger passenger;
        for(int i=pos+2;i>=pos;i--){
            passenger = seats.get(i).getPassenger();
            if(passenger!=null){
                cont++;
                msj.append("\n"+(cont)+") "+passenger.getName()+"  ::  "+passenger.getPassengerClass()+"  ::  Seat :"+passenger.getSeat().getNumber());
            }

        }
        node.setElement(msj.toString());
        node.setKey(cont);
        return node;
    }
    private HNode<String,Integer> normalPrint(int pos, int cont){
        HNode<String,Integer> node = new HNode<>("",-1);
        StringBuilder msj = new StringBuilder();
        Passenger passenger;
        for(int i=pos;i<=pos+2;i++){
            passenger = seats.get(i).getPassenger();
            if(passenger!=null){
                cont++;
                msj.append("\n"+(cont)+") "+passenger.getName()+"  ::  "+passenger.getPassengerClass()+"  ::  Seat :"+passenger.getSeat().getNumber());
            }
        }
        node.setElement(msj.toString());
        node.setKey(cont);
        return node;
    }
    public String printSeatarirplane() {
        int count = 1;
        String msj="";
        for (int countSeatVip = 1; countSeatVip <= totalSeatsVip; countSeatVip++) {

            msj += "[ V ] ";

            if (count == 6 || count == 12 || count ==18) {
                msj += "  \n";
            }
            if (count == 3) {
                msj += "  1   ";
            }
            if (count == 9) {
                msj += "  2   ";
            }
            if (count ==15){
                msj+= "  3   ";
            }
            count++;
        }

        for (int countSeatNormal = 1; countSeatNormal <= totalSeatsNormal; countSeatNormal++) {

            msj += "[ T ] ";

            if (count == 30 || count == 36 || count == 42 || count == 48 || count == 54) {
                msj += " \n";
            }
            if (count == 18 || count == 24) {
                msj += "\n";
            }
            if (count == 15) {
                msj += "  3   ";
            }
            if (count == 21) {
                msj += "  4   ";
            }
            if (count == 27) {
                msj += "  5   ";
            }
            if (count == 33) {
                msj += "  6   ";
            }
            if (count == 39) {
                msj += "  7   ";
            }
            if (count == 45) {
                msj += "  8   ";
            }
            if (count == 51) {
                msj += "  9   ";
            }

            count++;

        }

        return msj;

    }
    public String printSeatwhitPassengers() {
        String msj = "";

        for (int cont = 0; cont < totalSeatsVip + totalSeatsNormal; cont++) {
            if (cont % 6==0) {
                msj += "\n";
            }
            if (seats.get(cont).isOcupied()) {
                msj += "[ O ] ";
            } else {
                msj += "[ F ] ";
            }
            if (cont == 14) {
                msj += "  3   ";
            }
            if (cont == 20) {
                msj += "  4   ";
            }
            if (cont == 26) {
                msj += "  5   ";
            }
            if (cont == 32) {
                msj += "  6   ";
            }
            if (cont == 38) {
                msj += "  7   ";
            }
            if (cont == 44) {
                msj += "  8   ";
            }
            if (cont == 50) {
                msj += "  9   ";
            }
            if (cont == 2) {
                msj += "  1   ";
            }
            if (cont == 8) {
                msj += "  2   ";
            }
        }
        return  msj;
    }
    public String printSeatNumbers() {
        String msj = "";

        for (int cont = 0; cont < totalSeatsVip + totalSeatsNormal; cont++) {
            if (cont % 6==0) {
                msj += "\n";
            }
            msj += "[ "+seats.get(cont).getNumber()+" ] ";

            if (cont == 14) {
                msj += "  3   ";
            }
            if (cont == 20) {
                msj += "  4   ";
            }
            if (cont == 26) {
                msj += "  5   ";
            }
            if (cont == 32) {
                msj += "  6   ";
            }
            if (cont == 38) {
                msj += "  7   ";
            }
            if (cont == 44) {
                msj += "  8   ";
            }
            if (cont == 50) {
                msj += "  9   ";
            }
            if (cont == 2) {
                msj += "  1   ";
            }
            if (cont == 8) {
                msj += "  2   ";
            }
        }
        return  msj;
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
