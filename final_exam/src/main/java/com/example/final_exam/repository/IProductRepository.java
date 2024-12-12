package com.example.final_exam.repository;

import com.example.final_exam.model.Category;
import com.example.final_exam.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> showAllProducts();
    List<Category> showAllCategories();
    String getCategoryName(int id);
    boolean saveProduct(Product product);
    boolean removeProduct(int id);
    Product findProduct(String name, double price, int quantity, String color, int categoryID);

}
