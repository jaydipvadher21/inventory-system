import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Inventory class manages a collection of products
 * Provides CRUD operations and various inventory reports
 */
public class Inventory {
    private Map<String, Product> products;
    private String dataFile;
    
    // Constructor
    public Inventory() {
        this.products = new HashMap<>();
        this.dataFile = "inventory.dat";
        loadInventory();
    }
    
    // Add a new product to inventory
    public boolean addProduct(Product product) {
        if (product == null || product.getId() == null || product.getId().trim().isEmpty()) {
            return false;
        }
        
        if (products.containsKey(product.getId())) {
            System.out.println("Product with ID '" + product.getId() + "' already exists!");
            return false;
        }
        
        products.put(product.getId(), product);
        saveInventory();
        System.out.println("Product added successfully!");
        return true;
    }
    
    // Update an existing product
    public boolean updateProduct(String id, Product updatedProduct) {
        if (!products.containsKey(id)) {
            System.out.println("Product with ID '" + id + "' not found!");
            return false;
        }
        
        products.put(id, updatedProduct);
        saveInventory();
        System.out.println("Product updated successfully!");
        return true;
    }
    
    // Delete a product from inventory
    public boolean deleteProduct(String id) {
        if (!products.containsKey(id)) {
            System.out.println("Product with ID '" + id + "' not found!");
            return false;
        }
        
        Product removedProduct = products.remove(id);
        saveInventory();
        System.out.println("Product '" + removedProduct.getName() + "' deleted successfully!");
        return true;
    }
    
    // Get a product by ID
    public Product getProduct(String id) {
        return products.get(id);
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    // Search products by name (case-insensitive)
    public List<Product> searchProductsByName(String name) {
        return products.values().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Search products by category
    public List<Product> searchProductsByCategory(String category) {
        return products.values().stream()
                .filter(product -> product.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Get products with low stock
    public List<Product> getLowStockProducts() {
        return products.values().stream()
                .filter(Product::isLowStock)
                .collect(Collectors.toList());
    }
    
    // Get total inventory value
    public double getTotalInventoryValue() {
        return products.values().stream()
                .mapToDouble(Product::getTotalValue)
                .sum();
    }
    
    // Get inventory summary by category
    public Map<String, Integer> getInventorySummaryByCategory() {
        return products.values().stream()
                .collect(Collectors.groupingBy(
                    Product::getCategory,
                    Collectors.summingInt(Product::getQuantity)
                ));
    }
    
    // Update product quantity
    public boolean updateProductQuantity(String id, int quantityChange) {
        Product product = products.get(id);
        if (product == null) {
            System.out.println("Product with ID '" + id + "' not found!");
            return false;
        }
        
        product.updateQuantity(quantityChange);
        saveInventory();
        System.out.println("Quantity updated successfully! New quantity: " + product.getQuantity());
        return true;
    }
    
    // Save inventory to file
    private void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile))) {
            oos.writeObject(products);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }
    
    // Load inventory from file
    @SuppressWarnings("unchecked")
    private void loadInventory() {
        File file = new File(dataFile);
        if (!file.exists()) {
            return; // No existing data file
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
            products = (Map<String, Product>) ois.readObject();
            System.out.println("Inventory loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
            products = new HashMap<>(); // Initialize empty inventory on error
        }
    }
    
    // Get total number of products
    public int getTotalProducts() {
        return products.size();
    }
    
    // Check if inventory is empty
    public boolean isEmpty() {
        return products.isEmpty();
    }
    
    // Clear all products (for testing purposes)
    public void clearInventory() {
        products.clear();
        saveInventory();
        System.out.println("Inventory cleared!");
    }
}



