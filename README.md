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

