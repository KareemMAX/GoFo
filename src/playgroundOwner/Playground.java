package playgroundOwner;

import player.Booking;

import java.util.ArrayList;
import java.util.List;


/**
 * Playground Class for GoFo App
 *
 * @author Mohamed Ashraf
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */

public class Playground {
    public String playgroundName;
    public String address;
    public Boolean isSuspended;
    public PlaygroundOwner owner;
    public String size;
    public List<Booking> bookings;
    public Boolean isApproved = false;
    public List<List<Integer>> availableHours;
    public float pricePerHour;
    public int cancellationPeriodDays;

    /**
     * Playground default constructor
     */

    public Playground() {
        this.bookings = new ArrayList<Booking>();
        availableHours = new ArrayList<List<Integer>>();
        for (int i = 0; i < 7; i++){
            availableHours.add(new ArrayList<Integer>());
            for (int j = 0; j < 24; j++){
                availableHours.get(i).add(1);
            }
        }
    }

    /**
     * Playground parameterized constructor
     *
     * @param address                String object that indicates the Address of playground
     * @param cancellationPeriodDays Integer value that indicates the period in which bookings are allowed to be canceled in days.
     * @param isSuspended            Boolean value that indicates whether the playground is suspended or not.
     * @param owner                  Player object indicates the owner of the playground
     * @param playgroundName         String object indicates the commercial name of the playground.
     * @param pricePerHour           Float value indicates how much the playground charges per hour.
     * @param size                   String object describes the dimensions of the playground.
     */

    public Playground(String playgroundName,
                      String address,
                      Boolean isSuspended,
                      PlaygroundOwner owner,
                      String size,
                      float pricePerHour,
                      int cancellationPeriodDays) {
        this.playgroundName = playgroundName;
        this.address = address;
        this.isSuspended = isSuspended;
        this.owner = owner;
        this.size = size;

        this.bookings = new ArrayList<Booking>();
        availableHours = new ArrayList<List<Integer>>();
        for (int i = 0; i < 7; i++){
            availableHours.add(new ArrayList<Integer>());
            for (int j = 0; j < 24; j++){
                availableHours.get(i).add(1);
            }
        }

        this.pricePerHour = pricePerHour;
        this.cancellationPeriodDays = cancellationPeriodDays;
    }

    /**
     * From Playground object to String object
     *
     * @return String version of the Playground with all information within.
     */

    @Override
    public String toString() {
        return "playgroundName: " + playgroundName + '\n' +
                "address: " + address + '\n' +
                "isSuspended: " + isSuspended + '\n' +
                "owner: " + owner.toString() + '\n' +
                "size: " + size + '\n' +
                "isApproved: " + isApproved + '\n' +
                "availableHours: " + availableHours.toString() + '\n' +
                "pricePerHour: " + pricePerHour + '\n' +
                "cancellationPeriodDays: " + cancellationPeriodDays + '\n';
    }

}
