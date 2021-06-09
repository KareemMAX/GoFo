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
    public int id;
    public float totalCost;

    public Booking(Playground playground, Team players, LocalDateTime date, float time, Player player, int id) {
        this.playground = playground;
        this.players = players;
        this.date = date;
        this.player = player;
        this.id = id;
        this.time = time;
        this.totalCost = getMoney(time);
    }

    public float getMoney(float time) {
        return time * playground.pricePerHour;
    }
}
