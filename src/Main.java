import admin.AdminPanel;
import admin.Database;
import player.Player;
import player.UserInterface;
import player.UserStatus;
import playgroundOwner.PlaygroundOwner;
import playgroundOwner.PlaygroundOwnerPanel;

import java.util.Scanner;


/**
 * The entry class of the GoFo App
 *
 * @author Mohamed Ashraf, Kareem Morsy, Jonathan Saad.
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static Player userPlayer;
    private static PlaygroundOwner userOwner;
    private static Database db = Database.getInstance();
    /**
     * Entry function of GoFo App
     *
     * @param args Arguments taken from command line, [REDUNDANT]
     */
    public static void main(String[] args) {
        while (true){
            System.out.println("--- GoFo application ---");
            System.out.println("------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Register a player");
            System.out.println("3. Player panel");
            System.out.println("4. Playground Owner panel");
            System.out.println("5. Admin Panel");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    new UserInterface(userPlayer);
                    break;
                case 4:
                    new PlaygroundOwnerPanel(userOwner);
                    break;
                case 5:
                    new AdminPanel().show();
                    break;
                case 6:
                    return;
            }
        }
    }

    /**
     * Creates a new Player or a new PlaygroundOwner
     */
    public static void register(){
        System.out.println("Enter your username");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter your password");
        String pw = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your phone number");
        String num = scanner.nextLine();
        System.out.println("Enter your default location");
        String loc = scanner.nextLine();

        System.out.println("Would you like to register as a playerground owner ? (Y/N)");

        if (scanner.nextLine().equals("Y")){
            PlaygroundOwner x = new PlaygroundOwner(pw, name, email, num, loc);
            userOwner = x;
            db.playersDb.add(x);
        }
        else{
            Player x = new Player(pw, name, email, num, loc);
            userPlayer = x;
            db.playersDb.add(x);
        }
    }
    /**
     * Sets the current player or playground owner user in use
     */
    public static void login(){
        System.out.println("Enter your username");
        scanner.nextLine();
        String name = scanner.nextLine();
        boolean flag = false;
        Player possibleUser = null;
        for (int i = 0; i < db.playersDb.size(); i++){
            if (db.playersDb.get(i).name.equals(name)){
                flag = true;
                possibleUser = db.playersDb.get(i);
                break;
            }
        }
        if (!flag){
            System.out.println("Username used in not in our database");
            return;
        }
        else{
            System.out.println("Enter your password");
            String pw = scanner.nextLine();
            if (possibleUser.password.equals(pw)){
                if (possibleUser.currentStatus == UserStatus.status.player){
                    userPlayer = possibleUser;
                }
                else{
                    userOwner = (PlaygroundOwner) possibleUser;
                }
            }
        }
    }

}
