package ui;
import java.util.Scanner;
import model.*;
public class Main {


    private Scanner reader ;
    private AirPlaneSystem controller;
    public Main(){
        this.reader = new Scanner(System.in);
        this.controller=new AirPlaneSystem();

    }
    public static void main(String[] args) {

        Main main = new Main();

        int option = -1;
        do{
            option = main.getOptionShowMenu();
            main.executeOption(option);

        }while(option != 0);
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
                        "1. Importar archivo \n"+
                        "0. Exit";
    }


    public void executeOption(int option){

        switch(option){

            case 1:
                controller.uploadDataPassenger();
                break;
            case 2:
        

            case 0:
                System.out.print("Exit program");
                break;

            default :
                System.out.println("Su eleccion no es correcta");
                break;
        }


    }



}
