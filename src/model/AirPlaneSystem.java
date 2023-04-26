package model;
import java.io.*;


public class AirPlaneSystem {
    private File result;
    private HashTable hash;

    public AirPlaneSystem(){
        File projectDir = new File(System.getProperty("user.dir"));
        File dataDirectory = new File(projectDir.getAbsolutePath() + "/data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        result = new File(dataDirectory, "result.txt");
        hash= new HashTable<Passenger>();
    }
    
    public String uploadDataPassenger() {
        String msj="";
        try {
            FileInputStream fis = new FileInputStream(result); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = " ";
            try {
                while ((line = reader.readLine()) != null){
                String [] partes =line.split(",");
                String name= partes[0];
                String id= partes[1];
                int age = Integer.parseInt(partes[2]);
                int seat = Integer.parseInt(partes[3]);
                Passenger passenger=new Passenger(name, id, age, seat);
                hash.insert(passenger);
                }
                msj=" Se subio los datos ";

            } catch (IOException e) {
                msj="Otro error que no se que es ";
            }
        } catch (FileNotFoundException e) {
            msj=("No se encontron el archivo");
        }
        return msj;
    }
}
