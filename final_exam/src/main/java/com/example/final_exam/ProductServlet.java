package com.example.final_exam;

import com.example.final_exam.model.Category;
import com.example.final_exam.model.Product;
import com.example.final_exam.repository.IProductRepository;
import com.example.final_exam.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product_servlet")
public class ProductServlet extends HttpServlet {
    private final IProductRepository repository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "show-add":
                showAddProduct(req, resp);
                break;
            case "remove-product":
                removeProduct(req, resp);
            default:
                showAllProducts(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add-product":
                addProduct(req, resp);
                break;
            default:
                break;
        }
    }

    private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = repository.showAllProducts();
        req.setAttribute("products", products);
        req.getRequestDispatcher("product_display.jsp").forward(req, resp);
    }
    private void showAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = repository.showAllCategories();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("add_product.jsp").forward(req, resp);
    }
    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String description = req.getParameter("description");
        int categoryID = Integer.parseInt(req.getParameter("category"));
        Product product = new Product(name, price, quantity, color, description, categoryID);
        boolean isSaved = repository.saveProduct(product);

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
        if (isSaved) {
            out.println("<html><body>");
            out.println("<h1>Add new product successfully !</h1>");
            out.println("<a href='/product_servlet'>Go Back</a>");
            out.println("</body></html>");
        }
    }
    private void removeProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));
        boolean isRemoved = repository.removeProduct(productID);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html; charset=UTF-8");
        if (isRemoved) {
            out.println("<html><body>");
            out.println("<h1>Remove product successfully !</h1>");
            out.println("<a href='/product_servlet'>Go Back</a>");
            out.println("</body></html>");
        }
    }
}
