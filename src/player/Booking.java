package player;

import playgroundOwner.Playground;

import java.time.LocalDateTime;

/**
 * Player Class for GoFo App
 *
 * @author Jonathan Saad
 * @version 1.00 2021/6/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class Booking {
    public Playground playground;
    public Team players;
    public LocalDateTime date;
    public float time;
    public Player player;
    public int id,day,hour,amount;
    public float totalCost;

    /**
     * constructor for a book
     *
     * @param playground playground to book in
     * @param day day to book
     * @param hour start hour to book
     * @param amount amount of hours to book
     * @param player player who book
     * @param id id of booking
     */
    public Booking(Playground playground, int day, int hour,int amount , Player player, int id) {
        this.playground = playground;
        this.day = day;
        this.player = player;
        this.id = id;
        this.hour = hour;
        this.amount = amount;
        this.totalCost = getMoney(time);
    }

    public float getMoney(float time) {
        return time * playground.pricePerHour;
    }
}
