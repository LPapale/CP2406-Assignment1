/**
 * Created by Admin on 24/09/2016.
 */
public class Game {

    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        System.out.println("Hello!");
        String name = inputReader.getPlayerName();
        System.out.println("Nice to meet you " + name);
        System.out.println("What would you like to do?");
        showMenu();
        if (inputReader.getMenuChoice()==1){
            //Start new game
            System.out.println("Starting new game");
        }

    }


        private static void showMenu(){
            System.out.println("1. Start new game");
            System.out.println("2. Exit");

    }


}
