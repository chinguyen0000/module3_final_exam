create database product_management;
use product_management;

CREATE TABLE category (
    categoryID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    categoryName VARCHAR(50) NOT NULL
);

CREATE TABLE product (
    productID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    productName VARCHAR(100) NOT NULL,
    productPrice DOUBLE NOT NULL,
    quantity INT,
    color ENUM('Đỏ', 'Xanh', 'Đen', 'Trắng', 'Vàng'),
    description VARCHAR(255),
    categoryID INT NOT NULL,
    CONSTRAINT check_price CHECK (productPrice > 10000000),
    FOREIGN KEY (categoryID)
        REFERENCES category (categoryID)
);

insert into category(categoryName) values ('Điện thoại'), ('Tivi'), ('Tủ lạnh'), ('Máy giặt');

insert into product (productName, productPrice, quantity, color, categoryID) values ('iPhone 14', 16990000, 15, 'Đen', 1),
('Samsung Galaxy Z Fold6', 41990000, 15, 'Trắng', 1),
('Smart Tivi 55 inch', 33590000, 15, 'Xanh', 2),
('Tủ lạnh 2 cánh Toshiba', 13900000, 15, 'Đỏ', 3),
('Máy giặt cửa ngang Samsung', 68500000, 15, 'Vàng', 4);