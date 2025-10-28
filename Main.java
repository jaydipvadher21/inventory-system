/**
 * Main class to run the Inventory Management System
 * This is the entry point of the application
 */
public class Main {
    public static void main(String[] args) {
        try {
            InventoryManager manager = new InventoryManager();
            manager.run();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}



