package ui;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import model.*;
public class Main {
    private Scanner reader ;
    private AirPlaneSystem controller;
    private Timer timer;
    public Main(){
        this.reader = new Scanner(System.in);
        this.controller=new AirPlaneSystem();
        timer = new Timer();
    }
    private void print(Object o){System.out.println(o);}
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        int option = -1;
        do{
            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);
    }
    public void esperar(double seconds){
        final int[] flag = {0};
        seconds *= 1000;
        int cont = 0;
        TimerTask task = new TimerTask(){
            public void run(){
                flag[0] = 1;
            }
        };
        timer.schedule(task,(long)seconds);
        do{
            if(flag[0]==1){System.out.println("");}
        }while(flag[0]==0);
    }
    public int getOptionShowMenu(){
        int option = 0;
        System.out.println(printMenu());
        option = reader.nextInt();
        return option;
    }
    public void executeOption(int option) throws IOException {

        switch(option){

            case 1:
                //controller.uploadDataPassenger();
                print(controller.printPassengers());
                break;
            case 2:
                enterPassenger();
                break;
            case 3:
                print(boardPassengers());
                break;
            case 4:
                print(disboardPlane());
                break;
            case 5:
                print(controller.printSeatarirplane());
                print(controller.printSeatwhitPassengers());
                break;
            case 6:
                controller.createPassengerList();
            case 0:
                System.out.print("Exit program");
                System.exit(0);
                break;

            default :
                System.out.println("Su eleccion no es correcta");
                break;
        }
    }
    private void enterPassenger(){
        if(controller.getPlaneState().equals(PlaneState.LANDED)){
            print("\n El avión ya no recibe pasajeros....");
        }

        print("\n1. Entrar un solo pasajero\n"+"2. Casualmente llegan todos los pasajeros al tiempo\n(Para no undir la otra opción 54 veces)");
        switch(reader.next()){
            case "1":
                print(passengerValidation());
                break;
            case "2":
                print(validationTest());
                break;
            default:
                print("invalid option");
        }

    }
    private String passengerValidation(){
        int pos = controller.passengerComes();
        if(pos==-1){
            return "Lista de llegada de pasajeros Completa";
        }
        Passenger passenger = controller.getPassengers().get(pos);
        try{
            print("Se ha reportado un pasajero : \n"+
                    "Nombre : "+passenger.getName()+"\n"+
                    "    ID : "+passenger.getId());
            print("\n Searching Passenger......");
            if(controller.validatePassenger(passenger,pos)){
                return "\nPassenger Arrival Registered\n_______________";
            }else{
                return "\nPasajero no encontrado";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "Error\n_________________";
        }
    }
    private String validationTest(){
        ArrayList<Passenger> list = controller.getPassengers();
        StringBuilder msj = new StringBuilder();
        while(list.size()>0){
            print(passengerValidation());
        }
        ArrayList<Passenger> listTemp = controller.getListTemp();
        for(Passenger p:listTemp){
            msj.append("\n"+p.getName()+" :: "+p.getSeat().getNumber());
        }
        return msj.toString();
    }
    private String boardPassengers(){
        if(controller.getPlaneState().equals(PlaneState.LANDED)){
            return "\n El avión ya no recibe pasajeros....";
        }
        controller.setPlaneState();
        String msj = controller.getOrderedPassengers();
        return msj;
    }
    private String disboardPlane(){
        if(controller.getPlaneState().equals(PlaneState.WAITING)){
            return "\n El avión aún no ha despegado.";
        }
        return "";
    }
    public String printMenu(){
        return
                "\n" +
                        "<< ------------------------------------------------ >>\n" +
                        "<<                 Airplane System                   >> \n"+
                        "<< ------------------------------------------------ >>\n"+
                        "1. Imprimir pasajeros esperados \n"+
                        "2. Pasajero se presenta\n"+
                        "3. Avión va a despegar, aborden los pasajeros\n"+
                        "4. Desabordar pasajeros\n"+
                        "5. Imprimir Avión\n"+
                        "0. Exit";
    }






}
