package playgroundOwner;

import admin.Database;

import java.util.Scanner;


/**
 * PlaygroundOwnerPanel Class for GoFo App
 *
 * @author Mohamed Ashraf
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */

public class PlaygroundOwnerPanel {
    public PlaygroundOwner owner;
    public Playground playgroundOfInterest;
    public Database db;
    private Scanner scan;

    /**
     * Entry point of the Playground owner's panel
     */

    public PlaygroundOwnerPanel(PlaygroundOwner owner) {
        if(owner == null) {
            System.out.println("Playground owner is not logged!!");
            return;
        }

        db = Database.getInstance();
        for (int i = 0; i < db.playersDb.size(); i++){
            if (owner.name.equals(db.playersDb.get(i).name)) this.owner = (PlaygroundOwner) db.playersDb.get(i);
        }
        scan = new Scanner(System.in);
        while (true) {
            System.out.println("--- Playground Owner Panel ---");
            System.out.println("------------------------------");
            System.out.println("1. List Owned Playgrounds");
            System.out.println("2. List Suspended Playgrounds");
            System.out.println("3. Add a New Playground");
            System.out.println("4. Return");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    listOwnedPlaygrounds();
                    break;
                case 2:
                    listSuspendedPlaygrounds();
                    break;
                case 3:
                    onClickAddPlayground();
                    break;
                case 4:
                    return;
            }
        }
    }

    /**
     * Shows a list of all owned playgrounds and handles operations on them.
     */

    public void listOwnedPlaygrounds() {
        for (int i = 0; i < owner.ownedPlayground.size(); i++) {
            System.out.println("--- Playground Number " + Integer.toString(i + 1) + " ---");
            System.out.println(owner.ownedPlayground.get(i).toString());
        }
        System.out.println("Which playground would you like to select ? (Input -1 to return)");
        int choice = scan.nextInt();
        if (choice == -1) return;
        try {
            playgroundOfInterest = owner.ownedPlayground.get(choice - 1);
            int dbIndexOfInterset = -1;
            for (int i = 0; i < db.playgroundsDb.size(); i++) {
                if (db.playgroundsDb.get(i).equals(playgroundOfInterest)) dbIndexOfInterset = i;
            }

            //TODO For Debug perposes
            if (dbIndexOfInterset == -1) System.out.println("CRITICAL ERROR MISSING PLAYGROUND FROM DB");

            System.out.println("1.Delete Playground");
            System.out.println("2.Suspend Playground");
            System.out.println("3.Return");
            int choice1 = scan.nextInt();
            if (choice1 == 1) onClickDeletePlayground(choice);
            else if (choice1 == 2) onClickSuspendPlayground();
            else return;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid playground was chosen, Returning to Playground Owner Panel");
        }
    }

    /**
     * Shows a list of suspended owned playgrounds and handles operations on them
     */

    public void listSuspendedPlaygrounds() {
        int j = 0;
        for (int i = 0; i < owner.ownedPlayground.size(); i++) {
            if (!owner.ownedPlayground.get(i).isSuspended) j++;
            else {
                System.out.println("--- Playground Number " + Integer.toString(i + 1) + " ---");
                if (!owner.ownedPlayground.get(i - j).isApproved) System.out.println(owner.ownedPlayground.get(i - j).toString());
            }
        }
        System.out.println("Which playground would you like to select ? (Input -1 to return)");
        int choice = scan.nextInt();
        if (choice == -1) return;
        try {
            playgroundOfInterest = owner.ownedPlayground.get(choice - 1);
            int dbIndexOfInterset = -1;
            for (int i = 0; i < db.playgroundsDb.size(); i++) {
                if (db.playgroundsDb.get(i).equals(playgroundOfInterest)) dbIndexOfInterset = i;
            }

            if (dbIndexOfInterset == -1) System.out.println("CRITICAL ERROR MISSING PLAYGROUND FROM DB");

            System.out.println("1.Delete Playground");
            System.out.println("2.Suspend Playground");
            System.out.println("3.Return");
            int choice1 = scan.nextInt();
            if (choice1 == 1) onClickDeletePlayground(choice);
            else if (choice1 == 2) onClickSuspendPlayground();
            else return;

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid playground was chosen, Returning to Playground Owner Panel");
        }
    }

    /**
     * Creates a playground from user input and adds it to the owned playground list
     */

    public void onClickAddPlayground() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Name of the playground --> ");
        String _name = scan.nextLine();
        System.out.print("Address of the playground --> ");
        String _address = scan.nextLine();
        System.out.print("Describe the size of the playground --> ");
        String _size = scan.nextLine();
        System.out.print("Price per hour in float value --> ");
        float _price = scan.nextFloat();
        System.out.print("Period within cancelling a booking is allowed [In days] --> ");
        int _cancel = scan.nextInt();
        Playground temp = new Playground(_name, _address, true, owner, _size, _price, _cancel);
        owner.ownedPlayground.add(temp);
        db.playgroundsDb.add(temp);
    }

    /**
     * Switch the the suspension state
     */
    public void onClickSuspendPlayground() {
        playgroundOfInterest.isSuspended = !playgroundOfInterest.isSuspended;
    }

    /**
     * Deletes the current playground from owned list
     *
     * @param index Index of playground desired to be deleted
     */
    public void onClickDeletePlayground(int index) {
        owner.ownedPlayground.remove(index);
        int dbIndexOfInterset = -1;
        for (int i = 0; i < db.playgroundsDb.size(); i++) {
            if (db.playgroundsDb.get(i).equals(playgroundOfInterest)) dbIndexOfInterset = i;
        }

        if (dbIndexOfInterset == -1) System.out.println("CRITICAL ERROR IN onClickDeletePlayground");
        db.playgroundsDb.remove(dbIndexOfInterset);
    }
}
