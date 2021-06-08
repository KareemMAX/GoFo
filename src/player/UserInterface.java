package player;

import admin.Database;
import playgroundOwner.Playground;
import playgroundOwner.PlaygroundOwner;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author Jonathan Saad
 * @version 1.00 2021/6/9
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class UserInterface {
    private Scanner scan;
    public Player player;
    public Database db;

    /**
     * Entry point of the Player's panel
     *
     */
    public UserInterface (Player player) {
        this.player = player;
        db = Database.getInstance();
        scan = new Scanner(System.in);
        while (true){
            System.out.println("--- Player Panel ---");
            System.out.println("-----------------------------");
            System.out.println("1. Show playgrounds");
            System.out.println("2. Search playground");
            System.out.println("3. Booking menu");
            System.out.println("4. Send funds");
            System.out.println("5. Check balance");
            System.out.println("6. Exit");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    listPlayGrounds ();
                    break;
                case 2:
                    String playgroundSearchName = scan.nextLine ();
                    searchPlayground (playgroundSearchName);
                    break;
                case 3:
                    System.out.println("--- Book Menu ---");
                    System.out.println("-----------------------------");
                    System.out.println("1. Add new booking");
                    System.out.println("2. Cancel booking");
                    System.out.println("3. Back to Player Panel");
                    int bookChoice = scan.nextInt ();
                    switch (bookChoice){
                        case 1:
                            System.out.println ("Please Enter the playground ID, Date, and number of hours");
                            int ID = scan.nextInt () ,hours;
                            Playground bookPG = db.playgroundsDb.get (ID);
                           //msh 3aref htsht8l wla la2
                            LocalDateTime date = LocalDateTime.parse (scan.next());
                            hours  = scan.nextInt ();
                            try {
                                player.newBooking (bookPG, date, hours);
                            } catch (Exception e){
                                System.out.println (e.toString ());
                            }
                             break;
                        case 2:
                            System.out.println ("Please Enter the Booking ID that you want to delete.");
                            int bookID = scan.nextInt ();
                            try{
                            player.cancelBooking (bookID);
                            } catch (Exception e) {
                                System.out.println (e.toString ());
                            }
                            break;
                    }
                    case 4:
                        System.out.println ("Enter the player ID you want to transfer money to, and the amount of money.");
                        int pID = scan.nextInt ();
                        float money = scan.nextFloat ();
                        try {
                            player.sendFunds (db.playersDb.get (pID), money);
                        } catch (Exception e){
                            System.out.println (e.toString ());
                        }
                case 5:
                    System.out.println ("Balance: "+player.eWalletBalance);
                    break;
                case 6:
                    return;
                default:
                    System.out.println ("Invalid operation");
                    }

            }
        }
    /**
     * Shows a list of all playgrounds.
     * */
    public void  listPlayGrounds(){
        for (int i = 0; i < db.playgroundsDb.size (); i++){
            System.out.println("--- Playground Number " + Integer.toString(i + 1) + " ---");
            System.out.println(db.playgroundsDb.get(i).toString());
        }
    }

    /**
     * Search playground by its name
     * @param name the name of playground that we needed to be found
     */
    public void searchPlayground(String name){
        if(player.playgroundSearch(name)==null) {
            System.out.println ("playground Not found");
            return ;
        } else{
            System.out.println ("Playground: "+player.playgroundSearch(name));
        }
    }
    }

