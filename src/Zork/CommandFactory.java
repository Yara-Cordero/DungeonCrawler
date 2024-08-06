package Zork;

import org.apache.commons.lang3.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

class CommandFactory {

    private static CommandFactory Instance;
    private static String regex = "\\s+";

    private CommandFactory() {}

    public static CommandFactory instance() {
        if (Instance == null) {
            Instance = new CommandFactory();
        }
        return Instance;
    }

    public Command parse(String commandString) {
        commandString = commandString.trim();

        if (commandString.equals("move")) {
            return new MovementCommand(commandString);
        } else if (commandString.equals("i") || commandString.equals("inventory")) {
            return new InventoryCommand();
        } else if (commandString.equals("take")) {
            return new TakeCommand(commandString);
        }

        String[] splitCommand = commandString.split(regex);

        if (ArrayUtils.contains(splitCommand, "move")) {
            return new MovementCommand(splitCommand[1]);
        } else if (ArrayUtils.contains(splitCommand, "look")) {
            return new LookCommand();
        } else if (ArrayUtils.contains(splitCommand, "take")) {
            return new TakeCommand(splitCommand[1]);
        } else if (ArrayUtils.contains(splitCommand, "use")) {
            return new UseCommand(splitCommand[1], splitCommand[2]);
        } else if (splitCommand.length > 1) {
            String verb = splitCommand[0];
            String itemName = splitCommand[1];

            if (isValidItemSpecificCommand(verb, itemName)) {
                return new ItemSpecificCommand(verb, itemName);
            }
        }

        return new UnknownCommand(commandString);
    }

    // Method to check if the verb and item combination is valid
    private boolean isValidItemSpecificCommand(String verb, String itemName) {
        GameState gameState = GameState.instance();
        Set<Item> inventory = new HashSet<>(gameState.getInventory());
        Room currentRoom = gameState.getAdventurersCurrentRoom();
        Set<Item> roomItems = gameState.getItemsInRoom(currentRoom);

        if (roomItems == null) {
            roomItems = new HashSet<>();
        }

        // Check both inventory and current room for the item
        for (Item item : inventory) {
            if (item.goesBy(itemName) && item.getMessageForVerb(verb) != null) {
                return true;
            }
        }

        for (Item item : roomItems) {
            if (item.goesBy(itemName) && item.getMessageForVerb(verb) != null) {
                return true;
            }
        }

        return false;
    }
}
