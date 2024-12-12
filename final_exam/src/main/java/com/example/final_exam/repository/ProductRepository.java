package com.example.final_exam.repository;

import com.example.final_exam.model.Category;
import com.example.final_exam.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private String SHOW_ALL_PRODUCTS = "select * from product";
    private String SHOW_ALL_CATEGORIES = "select * from category";
    private String ADD_PRODUCT = "insert into product (productName, productPrice, quantity, color, categoryID) values (?, ?, ?, ?, ?)";
    private String REMOVE_PRODUCT = "delete from product where productID = ?";



    @Override
    public List<Product> showAllProducts() {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        List<Product> products = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_PRODUCTS);
            while (resultSet.next()) {
                int id = resultSet.getInt("productID");
                String productName = resultSet.getString("productName");
                double price = resultSet.getDouble("productPrice");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryID = resultSet.getInt("categoryID");
                products.add(new Product(id, productName, price, quantity, color, description, categoryID));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public List<Category> showAllCategories() {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SHOW_ALL_CATEGORIES);
            while (resultSet.next()) {
                int id = resultSet.getInt("categoryID");
                String categoryName = resultSet.getString("categoryName");
                categories.add(new Category(id, categoryName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public String getCategoryName(int id) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select categoryName from category where categoryID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("categoryName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean saveProduct(Product product) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        boolean isSaved = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2,product.getProductPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setInt(5,product.getCategoryID());
            preparedStatement.executeUpdate();
            isSaved = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isSaved;
    }

    @Override
    public boolean removeProduct(int id) {
        BaseRepository base = new BaseRepository();
        Connection connection = base.getConnection();
        boolean isRemoved = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            isRemoved = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isRemoved;
    }

    @Override
    public List<Product> findProduct(String name, double price, int quantity, String color, int categoryID) {
        BaseRepository baseRepository = new BaseRepository();
        Connection connection = baseRepository.getConnection();
        StringBuilder query = new StringBuilder("SELECT * FROM product WHERE 1=1");
        List<Object> parameters = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            query.append(" AND productName LIKE ?");
            parameters.add("%" + name + "%");
        }
        if (price > 0) {
            query.append(" AND productPrice = ?");
            parameters.add(price);
        }
        if (quantity > 0) {
            query.append(" AND quantity = ?");
            parameters.add(quantity);
        }
        if (color != null && !color.isEmpty()) {
            query.append(" AND color like ?");
            parameters.add("%" + color + "%");
        }
        if (categoryID > 0) {
            query.append(" AND categoryID = ?");
            parameters.add(categoryID);
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("productID"));
                product.setProductName(resultSet.getString("productName"));
                product.setProductPrice(resultSet.getDouble("productPrice"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setColor(resultSet.getString("color"));
                product.setDescription(resultSet.getString("description"));
                product.setCategoryID(resultSet.getInt("categoryID"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
