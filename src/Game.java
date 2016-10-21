/**
 * This class greets the user gets their name and starts a new game
 */
public class Game {
    public static void main(String[] args) {
        InputReader inputReader = new InputReader();
        // Greet player
        System.out.println("Hello!");
        String name = inputReader.getPlayerName();
        SuperTrumpGame superTrumpGame=new SuperTrumpGame(name);
        System.out.println("Nice to meet you " + name);
        System.out.println("What would you like to do?");
        showMenu();
        if (inputReader.getMenuChoice(1,2)==1){
            //Start new game
            System.out.println("Starting new game");
            superTrumpGame.startNewGame();

        }else{
            System.out.println("Exiting game");
        }
    }

    private static void showMenu(){
        System.out.println("1. Start new game");
        System.out.println("2. Exit");
    }
}
