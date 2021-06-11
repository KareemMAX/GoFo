package player;
/**
 * This class indicates the current status of the user either a PlaygroundOwner or a Player
 */
public class UserStatus {
    public enum status {player, owner};
    public status currentStatus;
}
