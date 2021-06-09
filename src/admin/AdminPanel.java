package admin;

import playgroundOwner.Playground;

import java.util.Scanner;

/**
 * Admin Panel Class for GoFo App
 *
 * @author Kareem Morsy
 * @version 1.00 2021/6/7
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class AdminPanel {
    private final Scanner input = new Scanner(System.in);
    private final Database database = Database.getInstance();

    /**
     * Initiate the admin panel
     */
    public void show() {
        while (true) {
            System.out.println("Welcome to GoFo admin panel");
            System.out.println("Choose an operation:");
            System.out.println("1- Show all playgrounds");
            System.out.println("2- Show playgrounds pending approval");
            System.out.println("3- Exit");

            switch (input.nextInt()) {
                case 1:
                    onClickShowAllPlaygrounds();
                    break;
                case 2:
                    onClickShowPendingPlaygrounds();
                    break;
                default:
                    return;
            }
        }
    }

    /**
     * Handle show all playgrounds choice
     */
    private void onClickShowAllPlaygrounds() {
        for (int i = 0; i < database.playgroundsDb.size(); i++) {
            System.out.print(i);
            System.out.println("-");
            System.out.println(database.playgroundsDb.get(i));
        }

        handlePlayground();
    }

    /**
     * Handle show playgrounds pending approval choice
     */
    private void onClickShowPendingPlaygrounds() {
        for (int i = 0; i < database.playgroundsDb.size(); i++) {
            if (!database.playgroundsDb.get(i).isApproved) {
                System.out.print(i);
                System.out.println("-");
                System.out.println(database.playgroundsDb.get(i));
            }
        }

        handlePlayground();
    }

    /**
     * Handle playground operation menu
     */
    private void handlePlayground() {
        System.out.println("Choose a playground ID to edit or -1 to exit:");
        int playgroundId = input.nextInt();
        if (playgroundId == -1) return;
        Playground pg = database.playgroundsDb.get(playgroundId);

        System.out.println(pg);
        System.out.println("Choose an operation:");
        System.out.println("1- Suspend playground");
        System.out.println("2- Activate playground");
        System.out.println("3- Delete playground");
        System.out.println("4- Approve playground");
        System.out.println("5- Exit");

        switch (input.nextInt()) {
            case 1:
                onClickSuspend(pg);
                break;
            case 2:
                onClickActivate(pg);
                break;
            case 3:
                onClickDelete(pg);
                break;
            case 4:
                onClickApprove(pg);
                break;
            default:
                return;
        }
    }

    /**
     * Handle suspend playground choice
     */
    private void onClickSuspend(Playground playground) {
        if (!playground.isApproved) {
            System.out.println("Playground isn't approved yet, no operations are allowed");
            return;
        }

        if (playground.isSuspended) {
            System.out.println("Playground is already suspended");
        }

        playground.isSuspended = true;
    }

    /**
     * Handle delete playground choice
     */
    private void onClickDelete(Playground playground) {
        database.playgroundsDb.remove(playground);
    }

    /**
     * Handle activate playground choice
     */
    private void onClickActivate(Playground playground) {
        if (!playground.isApproved) {
            System.out.println("Playground isn't approved yet, no operations are allowed");
            return;
        }

        if (!playground.isSuspended) {
            System.out.println("Playground is already active");
        }

        playground.isSuspended = false;
    }

    /**
     * Handle approve playground choice
     */
    private void onClickApprove(Playground playground) {
        if (playground.isApproved) {
            System.out.println("Playground is already approved");
        }

        playground.isApproved = true;
    }
}
