package Zork;

public class MovementCommand implements Command {
    private String dir;

    public MovementCommand(String dir) {
        this.dir = dir;
    }

    public String execute(){
        if (dir.contains("move")){
            return "Move to where.\nThe moon?";
        }

        Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
        try {
            Room nextRoom = currentRoom.leaveBy(dir);
            if (nextRoom != null){
                GameState.instance().setAdventurersCurrentRoom(nextRoom);
                return nextRoom.describe();
            }else {
                return "You can't move " + dir + " from here";
            }
        } catch (Room.ExitLockedException e) {
            return "The exit is locked.\nYou need a key to open it.";
        }
    }
}
