# Inventory Management System

A comprehensive Java-based Inventory Management System that allows you to manage products, track stock levels, and generate various reports.

## Features

### Core Functionality
- **Product Management**: Add, update, delete, and view products
- **Inventory Tracking**: Monitor stock levels and quantities
- **Search & Filter**: Search products by name or category
- **Data Persistence**: Automatically save and load inventory data
- **Reports**: Generate various inventory reports

### Product Properties
- Product ID (unique identifier)
- Product Name
- Price
- Quantity in stock
- Category
- Description

### Available Operations
1. **Add Product** - Add new products to inventory
2. **Update Product** - Modify existing product details
3. **Delete Product** - Remove products from inventory
4. **View Product Details** - Display specific product information
5. **View All Products** - List all products in inventory
6. **Search Products** - Find products by name, category, or low stock
7. **Update Quantity** - Add or remove stock quantities
8. **Generate Reports** - Create inventory summaries and reports

### Reports Available
- **Inventory Summary**: Total products and inventory value
- **Low Stock Report**: Products with quantity less than 10
- **Category Summary**: Stock quantities grouped by category

## File Structure

```
Inventory Management System/
├── Main.java              # Entry point of the application
├── Product.java           # Product class with properties and methods
├── Inventory.java         # Inventory management with CRUD operations
├── InventoryManager.java  # User interface and menu system
├── inventory.dat          # Data file (created automatically)
├── compile.bat            # Windows batch file to compile
├── run.bat               # Windows batch file to run
└── README.md             # This file
```

## Requirements

- Java Development Kit (JDK) 8 or higher
- Windows Command Prompt or any Java IDE

## How to Run

### Method 1: Using Batch Files (Windows)
1. Double-click `compile.bat` to compile the Java files
2. Double-click `run.bat` to start the application

### Method 2: Using Command Line
1. Open Command Prompt in the project directory
2. Compile the Java files:
   ```bash
   javac *.java
   ```
3. Run the application:
   ```bash
   java Main
   ```

### Method 3: Using IDE
1. Open the project in your favorite Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Compile and run the `Main.java` file

## Usage Guide

### Starting the Application
When you run the application, you'll see a welcome message and the main menu. The system automatically loads any existing inventory data from `inventory.dat`.

### Adding Products
1. Select option 1 from the main menu
2. Enter the required product details:
   - Product ID (must be unique)
   - Product Name
   - Price (in dollars)
   - Quantity
   - Category
   - Description (optional)
3. The product will be automatically saved to the inventory

### Updating Products
1. Select option 2 from the main menu
2. Enter the Product ID of the item you want to update
3. Modify the fields you want to change (press Enter to keep current values)
4. Changes are automatically saved

### Searching Products
1. Select option 6 from the main menu
2. Choose search type:
   - Search by Name: Find products containing specific text
   - Search by Category: Find products in a specific category
   - View Low Stock: Show products with quantity < 10

### Generating Reports
1. Select option 8 from the main menu
2. Choose report type:
   - Inventory Summary: Overview of total products and value
   - Low Stock Report: Products needing restocking
   - Category Summary: Stock breakdown by category
   - All Reports: Display all reports at once

## Data Persistence

The system automatically saves all changes to `inventory.dat` file. This file is created in the same directory as the Java files and contains serialized inventory data. The data persists between application sessions.

## Error Handling

The application includes comprehensive error handling for:
- Invalid input formats
- Duplicate product IDs
- Non-existent products
- File I/O errors
- Negative values for price and quantity

## Sample Data

You can start with sample products to test the system:

**Product 1:**
- ID: LAPTOP001
- Name: Dell Laptop
- Price: 999.99
- Quantity: 15
- Category: Electronics
- Description: High-performance laptop for business use

**Product 2:**
- ID: MOUSE001
- Name: Wireless Mouse
- Price: 29.99
- Quantity: 5
- Category: Accessories
- Description: Ergonomic wireless mouse

## Troubleshooting

### Common Issues
1. **"Error loading inventory"**: The `inventory.dat` file may be corrupted. Delete it to start fresh.
2. **"Product ID already exists"**: Each product must have a unique ID.
3. **"Product not found"**: Verify the Product ID is correct and exists in inventory.

### File Permissions
Ensure the application has read/write permissions in the directory where it's running to create and modify the `inventory.dat` file.

## Future Enhancements

Potential improvements for future versions:
- Database integration (MySQL, PostgreSQL)
- Web-based interface
- Barcode scanning support
- Multi-user access with authentication
- Advanced reporting with charts and graphs
- Export functionality (CSV, Excel)
- Email notifications for low stock
- Product image support

## License

This project is open source and available under the MIT License.

## Support

For issues or questions, please check the troubleshooting section above or create an issue in the project repository.



