package model;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class AirPlaneSystem {
    private File result;
    private HashTable<Passenger,String> hash;
    private ArrayList<Passenger> passengers;

    public AirPlaneSystem(){
        passengers = new ArrayList<>();
        createDataFile();
        uploadDataPassenger();
        passengerHashLoad();
    }
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
            String line = " ";
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
    private void passengerHashLoad(){
        hash = new HashTable<>();
        for(int i=0;i<passengers.size();i++){
            hash.add(passengers.get(i),passengers.get(i).getId());
        }
    }
    public Passenger passengerComes(){
        int pos = (int)Math.floor(Math.random()*passengers.size()); //Selecciona un pasajero aleatorio
        Passenger passenger = passengers.get(pos);
        return passenger;
    }
    public boolean validatePassenger(Passenger passenger){
        if(hash.search(passenger.getId())!=null){
            return true;
        }else{
            return false;
        }
    }
    //Este metodo se eliminara para el prototipo final
    public void createPassengerList() throws IOException {
        passengers.add(new Passenger("Alejandro Muñoz", generateRandomId(), 25));
        passengers.add(new Passenger("Samuel Ibarra", generateRandomId(), 32));
        passengers.add(new Passenger("Michael Brown", generateRandomId(), 43));
        passengers.add(new Passenger("Emily Davis", generateRandomId(), 19));
        passengers.add(new Passenger("David Wilson", generateRandomId(), 57));
        passengers.add(new Passenger("Sophia Garcia", generateRandomId(), 23));
        passengers.add(new Passenger("William Martinez", generateRandomId(), 35));
        passengers.add(new Passenger("Elizabeth Hernandez", generateRandomId(), 41));
        passengers.add(new Passenger("James Lee", generateRandomId(), 50));
        passengers.add(new Passenger("Mia Nguyen", generateRandomId(), 29));
        passengers.add(new Passenger("Christopher Jones", generateRandomId(), 38));
        passengers.add(new Passenger("Ava Martin", generateRandomId(), 27));
        passengers.add(new Passenger("Daniel Davis", generateRandomId(), 31));
        passengers.add(new Passenger("Emma Wilson", generateRandomId(), 22));
        passengers.add(new Passenger("Sarah Martinez", generateRandomId(), 45));
        passengers.add(new Passenger("Alexander Johnson", generateRandomId(), 49));
        passengers.add(new Passenger("Olivia Garcia", generateRandomId(), 20));
        passengers.add(new Passenger("Matthew Brown", generateRandomId(), 28));
        passengers.add(new Passenger("Isabella Lee", generateRandomId(), 36));
        passengers.add(new Passenger("Joseph Smith", generateRandomId(), 39));
        passengers.add(new Passenger("Samantha Nguyen", generateRandomId(), 26));
        passengers.add(new Passenger("Andrew Wilson", generateRandomId(), 33));
        passengers.add(new Passenger("Madison Hernandez", generateRandomId(), 30));
        passengers.add(new Passenger("Joshua Davis", generateRandomId(), 24));
        passengers.add(new Passenger("Chloe Martin", generateRandomId(), 21));
        passengers.add(new Passenger("Liam Baker", generateRandomId(), 25));
        passengers.add(new Passenger("Avery Williams", generateRandomId(), 32));
        passengers.add(new Passenger("Evelyn Cooper", generateRandomId(), 43));
        passengers.add(new Passenger("Benjamin Davis", generateRandomId(), 19));
        passengers.add(new Passenger("Lila Edwards", generateRandomId(), 57));
        passengers.add(new Passenger("Logan Flores", generateRandomId(), 23));
        passengers.add(new Passenger("Mia Garcia", generateRandomId(), 35));
        passengers.add(new Passenger("Owen Hall", generateRandomId(), 41));
        passengers.add(new Passenger("Aria Hill", generateRandomId(), 50));
        passengers.add(new Passenger("Caleb Johnson", generateRandomId(), 29));
        passengers.add(new Passenger("Aaliyah Jones", generateRandomId(), 38));
        passengers.add(new Passenger("Carson Lewis", generateRandomId(), 27));
        passengers.add(new Passenger("Madison Mitchell", generateRandomId(), 31));
        passengers.add(new Passenger("Abigail Perez", generateRandomId(), 22));
        passengers.add(new Passenger("Leo Reed", generateRandomId(), 45));
        passengers.add(new Passenger("Nora Richardson", generateRandomId(), 49));
        passengers.add(new Passenger("Wyatt Roberts", generateRandomId(), 20));
        passengers.add(new Passenger("Hazel Scott", generateRandomId(), 28));
        passengers.add(new Passenger("Grayson Taylor", generateRandomId(), 36));
        passengers.add(new Passenger("Aurora Thomas", generateRandomId(), 39));
        passengers.add(new Passenger("Ethan Turner", generateRandomId(), 26));
        passengers.add(new Passenger("Harper Walker", generateRandomId(), 33));
        passengers.add(new Passenger("Levi White", generateRandomId(), 30));
        passengers.add(new Passenger("Amelia Young", generateRandomId(), 24));
        passengers.add(new Passenger("Isaac Wright", generateRandomId(), 21));

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
