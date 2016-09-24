import com.sun.corba.se.impl.io.TypeMismatchException;

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


}
