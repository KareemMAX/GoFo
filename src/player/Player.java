package player;

import admin.Database;
import playgroundOwner.Playground;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Player Class for GoFo App
 *
 * @author Jonathan Saad
 * @version 1.00 2021/6/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class Player {
    private String password;
    public String name, email, phoneNum, defaultLocation;
    public List<Team> favoriteTeam = new ArrayList<> ();
    public List<Booking> bookingList = new ArrayList<> ();
    public int numOfBooking = 0;
    public static int id;
    public float eWalletBalance;
    Database data;

    /**
     * Player parameterized constructor
     *
     * @param password        the password to access the player's account
     * @param name            the username of the player
     * @param email           the email of the player
     * @param phoneNum        the phone number of the player
     * @param defaultLocation the location that the player prefer to play in
     */
    public Player(String password, String name, String email, String phoneNum, String defaultLocation) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.defaultLocation = defaultLocation;
        this.id++;
    }

    /**
     * Search for playground by its name
     *
     * @param name the name of the playground desired to be found
     */
    public Playground playgroundSearch(String name) {
        int i = 0;
        while (i++ < data.playgroundsDb.size ()) {
            if(data.playgroundsDb.get (i).playgroundName == name) return data.playgroundsDb.get (i);
        }
        System.out.println ("Playground name is wrong or not valid\n");
        return null;
    }

    /**
     * Add new booking
     * 
     * @param reqPlayground the desired playground to book in
     * @param reqDate desired date to play 
     * @param reqHours desired amount of hours to play
     */
    public void newBooking(Playground reqPlayground, LocalDateTime reqDate, float reqHours) {
        Boolean aval = false;
        int i = 0;
        while (i++ < reqPlayground.availableHours.size ()) {
            if(reqPlayground.availableHours.get (i) == reqDate) {
                aval = true;
                break;
            }
        }
        if(aval) {
            System.out.println ("Enter which team id you want to book with.\n");
            int teamNum;
            Scanner in = new Scanner (System.in);
            teamNum = in.nextInt ();
            Booking book = new Booking (reqPlayground, favoriteTeam.get (teamNum), reqDate, reqHours, this, numOfBooking++);
            System.out.println ("The cost is : " + book.getMoney (reqHours));
            if(eWalletBalance - book.getMoney (reqHours) >= 0) {
                System.out.println ("Press y/n to continue.");
                String choice = in.next ();
                switch (choice) {
                    case "y": {
                        System.out.println ("Current Balance is: " + (eWalletBalance - book.getMoney (reqHours)));
                        eWalletBalance -= book.getMoney (reqHours);
                        break;
                    }
                    case "n": {
                        System.out.println ("Operation is canceled.");
                        break;
                    }
                }
            } else {
                System.out.println ("Insufficient funds");
            }
        } else {
            System.out.println ("The selected date/time is not valid.\n");
        }

    }

    /**
     * cancel a desired booking
     *
     * @param idBook the id of the booking that is needed to be canceled
     */
    public void cancelBooking(int idBook) {
        if(bookingList.get (idBook).playground.cancellationPeriodDays < 0) {
            eWalletBalance += bookingList.get (idBook).totalCost;
            bookingList.remove (idBook);
        } else {
            System.out.println ("Cancellation period is over.");
        }
    }

    /**
     * transfer funds from one user to another one
     *
     * @param playerToTrans the desired player to transfer funds to
     * @param amountMoney   the amount of money desired to transfer
     */
    public void sendFunds(Player playerToTrans, float amountMoney) {
        if(this.eWalletBalance >= amountMoney) {
            playerToTrans.eWalletBalance += amountMoney;
            this.eWalletBalance -= amountMoney;
        } else {
            System.out.println ("Insufficient funds");
        }
    }
}
