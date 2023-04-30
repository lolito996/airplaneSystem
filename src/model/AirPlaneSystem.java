package model;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;


public class AirPlaneSystem {
    private File result;
    private HashTable hash;
    private ArrayList<Passenger> passengers;

    public AirPlaneSystem(){
        passengers = new ArrayList<>();
        createDataFile();
        uploadDataPassenger();
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
    //No necesarios por el momento
    public void createPassengerList() throws IOException {
        saveList();
    }
    //No necesarios por el momento
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
