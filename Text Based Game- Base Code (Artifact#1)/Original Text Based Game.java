import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BasicTextGame {

    // Room class to represent each room
    static class Room {
        private String name;
        private String description;
        private Map<String, Room> exits;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.exits = new HashMap<>();
        }

        public void setExit(String direction, Room room) {
            exits.put(direction, room);
        }

        public Room getExit(String direction) {
            return exits.get(direction);
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        public void displayExits() {
            System.out.println("Exits: " + exits.keySet());
        }
    }

    public static void main(String[] args) {
        // Create rooms
        Room startRoom = new Room("Start Room", "You are in a small dimly lit room. There's a door to the north.");
        Room hallway = new Room("Hallway", "You are in a narrow hallway. The walls are lined with torches.");
        Room treasureRoom = new Room("Treasure Room", "You have entered a bright room filled with treasure! You win!");
        Room trapRoom = new Room("Trap Room", "You stepped into a dark room. It's a trap! Game over.");

        // Set room exits
        startRoom.setExit("north", hallway);
        hallway.setExit("south", startRoom);
        hallway.setExit("east", treasureRoom);
        hallway.setExit("west", trapRoom);

        // Initialize game state
        Room currentRoom = startRoom;
        boolean gameRunning = true;
        Scanner scanner = new Scanner(System.in);

        // Main game loop
        while (gameRunning) {
            // Display current room
            System.out.println("You are in the " + currentRoom.getName());
            System.out.println(currentRoom.getDescription());
            currentRoom.displayExits();

            // Get player input
            System.out.print("Enter a direction (north, south, east, west) or 'quit' to exit: ");
            String input = scanner.nextLine().trim().toLowerCase();

            // Process input
            if (input.equals("quit")) {
                gameRunning = false;
                System.out.println("Thanks for playing!");
            } else if (currentRoom.getExit(input) != null) {
                currentRoom = currentRoom.getExit(input);

                // Check for special rooms
                if (currentRoom == treasureRoom) {
                    System.out.println(currentRoom.getDescription());
                    gameRunning = false;
                } else if (currentRoom == trapRoom) {
                    System.out.println(currentRoom.getDescription());
                    gameRunning = false;
                }
            } else {
                System.out.println("You can't go that way!");
            }
        }

        scanner.close();
    }
}
