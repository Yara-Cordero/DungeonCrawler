package Zork;

public class InventoryCommand implements Command {

    public String execute() {
        if (GameState.instance().getInventory().isEmpty()){
            return "You're empty-handed.";
        }
        return GameState.instance().getAllItemsInInventory();
    }
}
