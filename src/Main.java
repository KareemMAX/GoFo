import admin.AdminPanel;
import playgroundOwner.PlaygroundOwner;
import playgroundOwner.PlaygroundOwnerPanel;

import java.util.Scanner;

/**
 * The entry class of the GoFo App
 *
 * @author Mohamed Ashraf, Kareem Morsy, Jonathan Saad.
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("--- GoFo application ---");
            System.out.println("------------------------------");
            System.out.println("1. Player panel");
            System.out.println("2. Playground Owner panel");
            System.out.println("3. Admin Panel");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    break;
                case 2:
                    //new PlaygroundOwnerPanel();
                    break;
                case 3:
                    new AdminPanel().show();
                    break;
                case 4:
                    return;
            }
        }
    }
}
