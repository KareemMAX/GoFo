package admin;

import player.Player;
import playgroundOwner.Playground;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Class for GoFo App
 *
 * @author Kareem Morsy
 * @version 1.00 2021/6/7
 * Course: Software Engineering 1 CS251 2020/2021 - Homework 4 Final Draft
 */
public class Database {
    public List<Player> playersDb = new ArrayList<>();
    public List<Playground> playgroundsDb = new ArrayList<>();

    static private Database _instance = null;

    /**
     * Gets the singleton instance
     *
     * @return Singleton instance
     */
    public static Database getInstance() {
        if(_instance == null) {
            _instance = new Database();
        }

        return _instance;
    }
}
