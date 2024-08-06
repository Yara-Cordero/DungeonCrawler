package Zork;

public class LookCommand implements Command{


    public String execute() {
        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        String description = currentRoom.getDesc();

        return description;
    }
}
