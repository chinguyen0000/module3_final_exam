package com.example.final_exam.model;

import com.example.final_exam.repository.IProductRepository;
import com.example.final_exam.repository.ProductRepository;

public class Product {
    private int productID;
    private String productName;
    private double productPrice;
    private int quantity;
    private String color;
    private String description;
    private int categoryID;

    public Product(int productID, String productName, double productPrice, int quantity, String color, String description, int categoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.categoryID = categoryID;
    }

    public Product(String productName, double productPrice, int quantity, String color, String description, int categoryID) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.color = color;
        this.description = description;
        this.categoryID = categoryID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName(int categoryID) {
        IProductRepository productRepository = new ProductRepository();
        return productRepository.getCategoryName(categoryID);
    }
}
