import java.io.Serializable;

/**
 * Product class represents an item in the inventory
 * Implements Serializable for file I/O operations
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String category;
    private String description;
    
    // Default constructor
    public Product() {
        this.id = "";
        this.name = "";
        this.price = 0.0;
        this.quantity = 0;
        this.category = "";
        this.description = "";
    }
    
    // Parameterized constructor
    public Product(String id, String name, double price, int quantity, String category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.description = description;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    // Calculate total value of this product
    public double getTotalValue() {
        return price * quantity;
    }
    
    // Check if product is low in stock (less than 10 items)
    public boolean isLowStock() {
        return quantity < 10;
    }
    
    // Update quantity (for adding/removing stock)
    public void updateQuantity(int change) {
        this.quantity += change;
        if (this.quantity < 0) {
            this.quantity = 0;
        }
    }
    
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Price: $%.2f | Quantity: %d | Category: %s | Description: %s",
                id, name, price, quantity, category, description);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return id != null ? id.equals(product.id) : product.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}



