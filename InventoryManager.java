import java.util.*;
import java.util.stream.Collectors;

/**
 * InventoryManager class provides the main user interface
 * Handles user input and coordinates with Inventory class
 */
public class InventoryManager {
    private Inventory inventory;
    private Scanner scanner;
    
    public InventoryManager() {
        this.inventory = new Inventory();
        this.scanner = new Scanner(System.in);
    }
    
    // Main menu loop
    public void run() {
        System.out.println("=== INVENTORY MANAGEMENT SYSTEM ===");
        System.out.println("Welcome to the Inventory Management System!");
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewProduct();
                    break;
                case 5:
                    viewAllProducts();
                    break;
                case 6:
                    searchProducts();
                    break;
                case 7:
                    updateQuantity();
                    break;
                case 8:
                    generateReports();
                    break;
                case 9:
                    System.out.println("Thank you for using the Inventory Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    // Display main menu
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View Product Details");
        System.out.println("5. View All Products");
        System.out.println("6. Search Products");
        System.out.println("7. Update Product Quantity");
        System.out.println("8. Generate Reports");
        System.out.println("9. Exit");
        System.out.println("=".repeat(50));
    }
    
    // Add a new product
    private void addProduct() {
        System.out.println("\n--- ADD NEW PRODUCT ---");
        
        String id = getStringInput("Enter Product ID: ");
        if (id.trim().isEmpty()) {
            System.out.println("Product ID cannot be empty!");
            return;
        }
        
        String name = getStringInput("Enter Product Name: ");
        if (name.trim().isEmpty()) {
            System.out.println("Product Name cannot be empty!");
            return;
        }
        
        double price = getDoubleInput("Enter Price: $");
        if (price < 0) {
            System.out.println("Price cannot be negative!");
            return;
        }
        
        int quantity = getIntInput("Enter Quantity: ");
        if (quantity < 0) {
            System.out.println("Quantity cannot be negative!");
            return;
        }
        
        String category = getStringInput("Enter Category: ");
        String description = getStringInput("Enter Description: ");
        
        Product product = new Product(id, name, price, quantity, category, description);
        inventory.addProduct(product);
    }
    
    // Update an existing product
    private void updateProduct() {
        System.out.println("\n--- UPDATE PRODUCT ---");
        
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        String id = getStringInput("Enter Product ID to update: ");
        Product existingProduct = inventory.getProduct(id);
        
        if (existingProduct == null) {
            System.out.println("Product not found!");
            return;
        }
        
        System.out.println("Current Product Details:");
        System.out.println(existingProduct);
        System.out.println("\nEnter new details (press Enter to keep current value):");
        
        String name = getStringInput("Enter Product Name [" + existingProduct.getName() + "]: ");
        if (name.trim().isEmpty()) {
            name = existingProduct.getName();
        }
        
        String priceInput = getStringInput("Enter Price [" + existingProduct.getPrice() + "]: ");
        double price = existingProduct.getPrice();
        if (!priceInput.trim().isEmpty()) {
            try {
                price = Double.parseDouble(priceInput);
                if (price < 0) {
                    System.out.println("Price cannot be negative! Keeping current value.");
                    price = existingProduct.getPrice();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format! Keeping current value.");
            }
        }
        
        String quantityInput = getStringInput("Enter Quantity [" + existingProduct.getQuantity() + "]: ");
        int quantity = existingProduct.getQuantity();
        if (!quantityInput.trim().isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityInput);
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative! Keeping current value.");
                    quantity = existingProduct.getQuantity();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format! Keeping current value.");
            }
        }
        
        String category = getStringInput("Enter Category [" + existingProduct.getCategory() + "]: ");
        if (category.trim().isEmpty()) {
            category = existingProduct.getCategory();
        }
        
        String description = getStringInput("Enter Description [" + existingProduct.getDescription() + "]: ");
        if (description.trim().isEmpty()) {
            description = existingProduct.getDescription();
        }
        
        Product updatedProduct = new Product(id, name, price, quantity, category, description);
        inventory.updateProduct(id, updatedProduct);
    }
    
    // Delete a product
    private void deleteProduct() {
        System.out.println("\n--- DELETE PRODUCT ---");
        
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        String id = getStringInput("Enter Product ID to delete: ");
        Product product = inventory.getProduct(id);
        
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        
        System.out.println("Product to delete:");
        System.out.println(product);
        
        String confirm = getStringInput("Are you sure you want to delete this product? (yes/no): ");
        if (confirm.toLowerCase().equals("yes")) {
            inventory.deleteProduct(id);
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    // View a specific product
    private void viewProduct() {
        System.out.println("\n--- VIEW PRODUCT ---");
        
        String id = getStringInput("Enter Product ID: ");
        Product product = inventory.getProduct(id);
        
        if (product == null) {
            System.out.println("Product not found!");
        } else {
            System.out.println("\nProduct Details:");
            System.out.println(product);
            System.out.println("Total Value: $" + String.format("%.2f", product.getTotalValue()));
            System.out.println("Low Stock: " + (product.isLowStock() ? "Yes" : "No"));
        }
    }
    
    // View all products
    private void viewAllProducts() {
        System.out.println("\n--- ALL PRODUCTS ---");
        
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        List<Product> products = inventory.getAllProducts();
        System.out.println("Total Products: " + products.size());
        System.out.println("-".repeat(100));
        
        for (Product product : products) {
            System.out.println(product);
        }
        
        System.out.println("-".repeat(100));
        System.out.println("Total Inventory Value: $" + String.format("%.2f", inventory.getTotalInventoryValue()));
    }
    
    // Search products
    private void searchProducts() {
        System.out.println("\n--- SEARCH PRODUCTS ---");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Category");
        System.out.println("3. View Low Stock Products");
        
        int choice = getIntInput("Enter search option: ");
        
        switch (choice) {
            case 1:
                String name = getStringInput("Enter product name to search: ");
                List<Product> nameResults = inventory.searchProductsByName(name);
                displaySearchResults(nameResults, "Products matching name: " + name);
                break;
            case 2:
                String category = getStringInput("Enter category to search: ");
                List<Product> categoryResults = inventory.searchProductsByCategory(category);
                displaySearchResults(categoryResults, "Products in category: " + category);
                break;
            case 3:
                List<Product> lowStockResults = inventory.getLowStockProducts();
                displaySearchResults(lowStockResults, "Low Stock Products");
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // Display search results
    private void displaySearchResults(List<Product> results, String title) {
        System.out.println("\n--- " + title.toUpperCase() + " ---");
        
        if (results.isEmpty()) {
            System.out.println("No products found!");
            return;
        }
        
        System.out.println("Found " + results.size() + " product(s):");
        System.out.println("-".repeat(100));
        
        for (Product product : results) {
            System.out.println(product);
        }
    }
    
    // Update product quantity
    private void updateQuantity() {
        System.out.println("\n--- UPDATE QUANTITY ---");
        
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        String id = getStringInput("Enter Product ID: ");
        Product product = inventory.getProduct(id);
        
        if (product == null) {
            System.out.println("Product not found!");
            return;
        }
        
        System.out.println("Current quantity: " + product.getQuantity());
        int change = getIntInput("Enter quantity change (+/-): ");
        
        inventory.updateProductQuantity(id, change);
    }
    
    // Generate reports
    private void generateReports() {
        System.out.println("\n--- INVENTORY REPORTS ---");
        
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory!");
            return;
        }
        
        System.out.println("1. Inventory Summary");
        System.out.println("2. Low Stock Report");
        System.out.println("3. Category Summary");
        System.out.println("4. All Reports");
        
        int choice = getIntInput("Enter report option: ");
        
        switch (choice) {
            case 1:
                displayInventorySummary();
                break;
            case 2:
                displayLowStockReport();
                break;
            case 3:
                displayCategorySummary();
                break;
            case 4:
                displayInventorySummary();
                displayLowStockReport();
                displayCategorySummary();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // Display inventory summary
    private void displayInventorySummary() {
        System.out.println("\n--- INVENTORY SUMMARY ---");
        System.out.println("Total Products: " + inventory.getTotalProducts());
        System.out.println("Total Inventory Value: $" + String.format("%.2f", inventory.getTotalInventoryValue()));
    }
    
    // Display low stock report
    private void displayLowStockReport() {
        System.out.println("\n--- LOW STOCK REPORT ---");
        List<Product> lowStockProducts = inventory.getLowStockProducts();
        
        if (lowStockProducts.isEmpty()) {
            System.out.println("No products with low stock!");
        } else {
            System.out.println("Products with low stock (< 10 items):");
            System.out.println("-".repeat(80));
            for (Product product : lowStockProducts) {
                System.out.println(product.getName() + " (ID: " + product.getId() + ") - Quantity: " + product.getQuantity());
            }
        }
    }
    
    // Display category summary
    private void displayCategorySummary() {
        System.out.println("\n--- CATEGORY SUMMARY ---");
        Map<String, Integer> categorySummary = inventory.getInventorySummaryByCategory();
        
        if (categorySummary.isEmpty()) {
            System.out.println("No categories found!");
        } else {
            System.out.println("Quantity by Category:");
            System.out.println("-".repeat(40));
            for (Map.Entry<String, Integer> entry : categorySummary.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " items");
            }
        }
    }
    
    // Helper method to get string input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    // Helper method to get integer input
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
    
    // Helper method to get double input
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }
}



