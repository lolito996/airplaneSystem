package ui;
import java.io.IOException;
import java.util.Scanner;
import model.*;
public class Main {


    private Scanner reader ;
    private AirPlaneSystem controller;
    public Main(){
        this.reader = new Scanner(System.in);
        this.controller=new AirPlaneSystem();

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
    public void executeOption(int option) throws IOException {

        switch(option){

            case 1:
                //controller.uploadDataPassenger();
                print(controller.printPassengers());
                break;
            case 2:
                controller.createPassengerList();
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
    public int getOptionShowMenu(){
        int option = 0;
        System.out.println(printMenu());
        option = reader.nextInt();
        return option;
    }

    public String printMenu(){
        return
                "\n" +
                        "<< --------------------------------------------------------------------- >>\n" +
                        "<<                      Airplane System                                  >> \n"+
                        "<< --------------------------------------------------------------------- >>\n"+
                        "1. Imprimir pasajeros esperados \n"+
                        "2. Pasajero se presenta\n"+
                        "3. Despegue del avión\n"+
                        "4. Desabordar pasajeros\n"+
                        "0. Exit";
    }






}
