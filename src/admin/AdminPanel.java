package admin;

import java.util.Scanner;

public class AdminPanel {
    private final Scanner input = new Scanner(System.in);
    private final Database database = Database.getInstance();

    public void show() {
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

    private void onClickShowAllPlaygrounds() {

    }

    private void onClickShowPendingPlaygrounds() {

    }
}
