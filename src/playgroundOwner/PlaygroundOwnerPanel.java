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
    public PlaygroundOwner owner;
    public Playground playgroundOfInterset;

    public PlaygroundOwnerPanel(PlaygroundOwner owner){
        this.owner = owner;
        scan = new Scanner(System.in);
        while (true){
            System.out.println("--- Playground Owner Panel ---");
            System.out.println("------------------------------");
            System.out.println("1. List Owned Playgrounds");
            System.out.println("2. List Suspended Playgrounds");
            System.out.println("3. Add a New Playground");
            System.out.println("4. Return to Player Panel");
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    listOwnedPlaygrounds();
                case 2:
                    listSuspendedPlaygrounds();
                case 3:
                    onClickAddPlayground();
                case 4:
                    break;
            }
        }
    }

    public void listOwnedPlaygrounds(){
        for (int i = 0; i < owner.ownedPlayground.size(); i++){
            System.out.println("--- Playground Number " + Integer.toString(i + 1) + " ---");
            System.out.println(owner.ownedPlayground.get(i).toString());
        }
        System.out.println("Which playground would you like to select ? (Input -1 to return)");
        int choice = scan.nextInt();
        if (choice == -1) return;
        try{
            playgroundOfInterset = owner.ownedPlayground.get(choice - 1);
            // TODO --> handle playgroundOfInterset
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid playground was chosen, Returning to Playground Owner Panel");
        }
    }

    public void listSuspendedPlaygrounds(){
        int j = 0;
        for (int i = 0; i < owner.ownedPlayground.size(); i++){
            if (!owner.ownedPlayground.get(i).isSuspended) j++;
            else {
                System.out.println("--- Playground Number " + Integer.toString(i + 1) + " ---");
                System.out.println(owner.ownedPlayground.get(i - j).toString());
            }
        }
        System.out.println("Which playground would you like to select ? (Input -1 to return)");
        int choice = scan.nextInt();
        if (choice == -1) return;
        try{
            playgroundOfInterset = owner.ownedPlayground.get(choice - 1);
            // TODO --> handle playgroundOfInterset
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid playground was chosen, Returning to Playground Owner Panel");
        }
    }

    public void onClickAddPlayground(){
        owner.ownedPlayground.add(Playground.createPlaygroundFromUserInput(owner));
    }

    public void onClickSuspendPlayground(){
        playgroundOfInterset.isSuspended = !playgroundOfInterset.isSuspended;
    }

    public void onClickDeletePlayground(int index){
        owner.ownedPlayground.remove(index);
    }
}
