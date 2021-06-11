package player;

import admin.Database;
import playgroundOwner.Playground;

import java.util.ArrayList;
import java.util.List;

/**
 * Player Class for GoFo App
 *
 * @author Jonathan Saad
 * @version 1.00 2021/6/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class Player extends UserStatus{
    public static int idCount,idBook;
    public String name, email, phoneNum, defaultLocation;
    public List<Team> favoriteTeam = new ArrayList<>();
    public List<Booking> bookingList = new ArrayList<>();
    public int numOfBooking = 0;
    public float eWalletBalance;
    int id;
    Database data;
    public String password;

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
        currentStatus = status.player;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.defaultLocation = defaultLocation;
        this.id = idCount++;
    }

    /**
     * Search for playground by its name
     *
     * @param name the name of the playground desired to be found
     */
    public Playground playgroundSearch(String name) {
        int i = 0;
        while (i++ < data.playgroundsDb.size()) {
            if (data.playgroundsDb.get(i).playgroundName.equals(name)) return data.playgroundsDb.get(i);
        }
        return null;
    }

    /**
     * add a new booking
     *
     * @param reqPlayground     Playground to be booked
     * @param reqDay            Day to be booked
     * @param reqHours          Hour to be booked
     * @param amountHours       Number of hours to be booked
     * @throws Exception
     */
    public void newBooking(Playground reqPlayground, int reqDay, int reqHours, int amountHours) throws Exception {
        Boolean aval = true;
        int i = 0,coun = reqHours;
        while (i++ < amountHours){
        if(reqPlayground.availableHours.get(reqDay).get(coun++)==0){
            aval = false;
            }
        }if (aval) {
            Booking book = new Booking(reqPlayground, reqDay, reqHours, amountHours, this, idBook);
            System.out.println("Booking Id: "+idBook);
            bookingList.add(idBook++,book);
            i=0;
            while (i++ < amountHours){
                reqPlayground.availableHours.get(reqDay).set(reqHours,0) ;
                reqHours++;
            }
        }
         else {
            throw new Exception("The selected date/time is not valid.\n");
        }

    }

    /**
     * cancel a desired booking
     *
     * @param idBook the id of the booking that is needed to be canceled
     */
    public void cancelBooking(int idBook) throws Exception {
        if (bookingList.get(idBook).playground.cancellationPeriodDays > 0) {
            eWalletBalance += bookingList.get(idBook).totalCost;
            int i=0;
            while (i++ < bookingList.get(idBook).amount){
                bookingList.get(idBook).playground.availableHours.get(bookingList.get(idBook).day).set(bookingList.get(idBook).hour, 1);
                bookingList.get(idBook).hour++;
            }
            bookingList.remove(idBook);
        } else {
            throw new Exception("Cancellation period is over.");
        }
    }

    /**
     * transfer funds from one user to another one
     *
     * @param playerToTrans the desired player to transfer funds to
     * @param amountMoney   the amount of money desired to transfer
     */
    public void sendFunds(Player playerToTrans, float amountMoney) throws Exception {
        if (this.eWalletBalance >= amountMoney) {
            playerToTrans.eWalletBalance += amountMoney;
            this.eWalletBalance -= amountMoney;
        } else {
            throw new Exception("Insufficient funds");
        }
    }
}
