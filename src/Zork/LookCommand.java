package Zork;

public class LookCommand implements Command{

    public String itemName;

    public LookCommand(String item) {
        this.itemName = item;
    }

    public String execute() {
        if(itemName == null){
            Room currentRoom = GameState.instance().getAdventurersCurrentRoom();
            String description = currentRoom.getDesc();

            return description;
        }else {
            try {
                Item item = GameState.instance().getItemFromInventoryNamed(itemName);
                return item.getDescription();
            } catch (Item.NoItemException e) {
                return itemName + " is not in your inventory.";
            }
        }
    }
}
