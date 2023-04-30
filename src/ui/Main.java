package ui;
import java.io.IOException;
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
    public static void main(String[] args) throws Exception {
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
    public void executeOption(int option) throws Exception {

        switch(option){

            case 1:
                //controller.uploadDataPassenger();
                print(controller.printPassengers());
                break;
            case 2:
                print(passengerValidation());
                break;
            case 3:
                break;
            case 4:
                break;
            case 0:
                System.out.print("Exit program");
                System.exit(0);
                break;

            default :
                System.out.println("Su eleccion no es correcta");
                break;
        }
    }
    private String passengerValidation() throws InterruptedException {
        Passenger passenger = controller.passengerComes();
        try{
            print("Se ha reportado un pasajero : \n"+
                    "Nombre : "+passenger.getName()+"\n"+
                    "    ID : "+passenger.getId());
            esperar(2);
            print("\n Searching Passenger......");
            esperar(1);
            if(controller.validatePassenger(passenger)){
                return "\nPassenger Found";
            }else{
                return "\nPassenger not Found";
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return "Error";
        }
    }


    public String printMenu(){
        return
                "\n" +
                        "<< --------------------------------------------------------------------- >>\n" +
                        "<<                      Airplane System                                  >> \n"+
                        "<< --------------------------------------------------------------------- >>\n"+
                        "1. Imprimir pasajeros esperados \n"+
                        "2. Pasajero se presenta\n"+
                        "3. Avión va a despegar, aborden los pasajeros\n"+
                        "4. Desabordar pasajeros\n"+
                        "0. Exit";
    }






}
