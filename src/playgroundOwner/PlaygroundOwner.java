package playgroundOwner;

import player.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * PlaygroundOwner Class for GoFo App
 *
 * @author Mohamed Ashraf
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */

public class PlaygroundOwner extends Player {
    public List<Playground> ownedPlayground;

    /**
     * PlaygroundOwner constructor
     */
    public PlaygroundOwner(String pw, String name, String email, String phoneNum, String defaultLocation) {
        super(pw, name, email, phoneNum, defaultLocation);
        ownedPlayground = new ArrayList<Playground>();
    }

    /**
     * PlaygroundOwner Parameterized constructor
     *
     * @param ownedPlayground Playground object that is owned by the PlaygroundOwner object.
     * @param pw              the password to access the player's account
     * @param name            the username of the player
     * @param email           the email of the player
     * @param phoneNum        the phone number of the player
     * @param defaultLocation the location that the player prefer to play in
     */
    public PlaygroundOwner(Playground ownedPlayground, String pw, String name, String email, String phoneNum, String defaultLocation) {
        super(pw, name, email, phoneNum, defaultLocation);
        this.ownedPlayground = new ArrayList<Playground>();
        this.ownedPlayground.add(ownedPlayground);
        currentStatus = status.owner;
    }

    /**
     * PlaygroundOwner Parametrized constructor
     *
     * @param ownedPlayground Collection of Playground objects that are owned by the PlaygroundOwner
     * @param pw              the password to access the player's account
     * @param name            the username of the player
     * @param email           the email of the player
     * @param phoneNum        the phone number of the player
     * @param defaultLocation the location that the player prefer to play in
     */
    public PlaygroundOwner(Collection<Playground> ownedPlayground, String pw, String name, String email, String phoneNum, String defaultLocation) {
        super(pw, name, email, phoneNum, defaultLocation);
        this.ownedPlayground = new ArrayList<Playground>();
        this.ownedPlayground.addAll(ownedPlayground);
        currentStatus = status.owner;
    }

    /**
     * PlaygroundOwner constructor with a player object. Upgrades player to playground owner.
     *
     * @param player   The original player
     * @param password The player's password
     */
    public PlaygroundOwner(Player player, String password) {
        super(password, player.name, player.email, player.phoneNum, player.defaultLocation);
        eWalletBalance = player.eWalletBalance;
        favoriteTeam = player.favoriteTeam;
        bookingList = player.bookingList;
        currentStatus = status.owner;
    }

    /**
     * Deletes one of the owned playgrounds
     *
     * @param toDel Playground Object that is desired to be deleted from the owned list.
     */
    public void deletePlayground(int toDel) {
        this.ownedPlayground.remove(toDel);
    }

    /**
     * Adds a playground to the owned list.
     *
     * @param toAdd Playground object that is desired to add to the owned list.
     */
    public void addPlayground(Playground toAdd) {
        this.ownedPlayground.add(toAdd);
    }

    /**
     * Switched the suspended state for a Playground object
     *
     * @param toSuspend Playground object that is desired to be suspended / unsuspended.
     *                  It must be Approved by an admin else the playground is suspended automatically.
     */
    public void suspendPlayground(Playground toSuspend) {
        if (toSuspend.isApproved) toSuspend.isSuspended = !toSuspend.isSuspended;
        else toSuspend.isSuspended = false;
    }
}
