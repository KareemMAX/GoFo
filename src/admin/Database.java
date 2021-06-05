package admin;

import player.Player;
import playgroundOwner.PlaygroundOwner;
import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Player> playersDb = new ArrayList<>();
    public List<PlaygroundOwner> playgroundsDb = new ArrayList<>();

    static private Database _instance = null;

    static Database getInstance() {
        if(_instance == null) {
            _instance = new Database();
        }

        return _instance;
    }
}
