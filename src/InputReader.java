

import java.util.Scanner;

/**
 * Created by Admin on 24/09/2016.
 */
public class InputReader {
    Scanner scanner=new Scanner(System.in);

    public int getMenuChoice(){
        int menuChoice;
        System.out.println("Please enter the menu number>>");
            menuChoice=scanner.nextInt();
        return menuChoice;
    }

    public String getPlayerName(){
        System.out.println("Please enter your name>>");
        String name=scanner.nextLine();
        return name;
    }

    public int getNumberOfPlayers(){
        int numberOfPlayers=0;
        String input;
        boolean validChoice=false;

        while (!validChoice) {
            System.out.println("Please enter the number of players>>");
            input = scanner.next();
            if (input.matches("^\\d$")){
                int number=Integer.parseInt(input);
                if((number>=3)&&(number<=5)){
                    numberOfPlayers=number;
                    validChoice=true;
                }else {
                    System.out.println("The allowed number of players is between 3 and 5 inclusive.");
                }
            }else {
                System.out.println("You must enter an integer");
            }
        }
        return numberOfPlayers;
    }

}
