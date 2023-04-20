package ui;
import java.util.Scanner;
import model.*;
public class Main {


    private Scanner reader ;
    private Controller controller;
    public Main(){
        this.reader = new Scanner(System.in);
        this.controller=new Controller();

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
                        "<<                      jugo de las serpientes y escaleras                \n"+
                        "<< --------------------------------------------------------------------- >>\n"+
                        "1. Jugar\n"+
                        "0. Exit";
    }


    public void executeOption(int option){

        switch(option){

            case 1:

                break;

            case 0:
                System.out.print("Exit program");
                break;

            default :
                System.out.println("Su eleccion no es correcta");
                break;
        }


    }



}
