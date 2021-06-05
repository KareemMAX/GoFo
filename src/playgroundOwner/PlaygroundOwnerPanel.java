package playgroundOwner;

import java.util.Scanner;


/** PlaygroundOwnerPanel Class for GoFo App
 *
 * @author Mohamed Ashraf
 * @version 1.00 2021/5/6
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */

public class PlaygroundOwnerPanel{
    private Scanner scan;

    public PlaygroundOwnerPanel(){
        scan = new Scanner(System.in);
        while (true){
            System.out.println("--- Playground Owner Panel ---");
            System.out.println("------------------------------");
            System.out.println("1. List Owned Playgrounds");
            System.out.println("2. List Suspended Playgrounds");
            System.out.println("3. List Pending Playgrounds");
            System.out.println("4. Add a New Playground");
            System.out.println("5. Return to Player Panel");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    return; //TODO
                case 2:
                    return; //TODO
                case 3:
                    return; //TODO
                case 4:
                    return; //TODO
                case 5:
                    break;
            }
        }
    }

    public void onClickAddPlayground(){
        //TODO
    }

    public void onClickSuspendPlayground(){
        //TODO
    }

    public void onClickViewPlayground(){
        //TODO
    }

    public void onClickDeletePlayground(){
        //TODO
    }
}
