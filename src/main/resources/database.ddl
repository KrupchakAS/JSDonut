SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;
-- Table: users
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(16) NOT NULL,
  password VARCHAR(100) NOT NULL,
  firstName VARCHAR(32) NOT NULL,
  surName VARCHAR(32) NOT NULL,
  phoneNumber VARCHAR(10) NOT NULL,
  email VARCHAR(50) NOT NULL,
  birthDate DATE NOT NULL ,
  role VARCHAR(10) NOT NULL ,
  UNIQUE (login,phoneNumber,email)
)ENGINE = InnoDB;

-- Table: address
CREATE TABLE address (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR(32) NOT NULL ,
  city VARCHAR(32) NOT NULL ,
  street VARCHAR(32) NOT NULL ,
  houseNumber SMALLINT NOT NULL ,
  apartmentNumber SMALLINT NOT NULL,
  postCode INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE = InnoDB;

-- Table: category
CREATE TABLE category(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL
) ENGINE = InnoDB;

-- Table: filling
CREATE TABLE filling (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  calories SMALLINT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id)
)ENGINE =InnoDB;

-- Table: corn
CREATE TABLE corn (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  calories SMALLINT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id)
)ENGINE =InnoDB;

-- Table: Sprinkle
CREATE TABLE sprinkle (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  calories SMALLINT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id)
)ENGINE =InnoDB;

-- Table: product
CREATE TABLE products (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  image VARCHAR(255),
  price FLOAT NOT NULL ,
  weight SMALLINT NOT NULL ,
  quantity SMALLINT,
  calories SMALLINT NOT NULL ,
  category_id INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id),
  UNIQUE (name,image)
)ENGINE = InnoDB;

-- Table: Order
CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  paymentOption TINYINT NOT NULL ,
  deliveryOption TINYINT NOT NULL ,
  paymentStatus TINYINT NOT NULL ,
  orderStatus TINYINT NOT NULL ,
  user_id INT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users(id),
  address_id INT NOT NULL ,
  FOREIGN KEY (address_id) REFERENCES address(id)
) ENGINE = InnoDB;

-- Table for mapping products and orders: order_products
CREATE TABLE order_products (
  order_id INT NOT NULL ,
  product_id INT NOT NULL ,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES products(id)
)ENGINE = InnoDB;

-- Insert data!
INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG','Андрей','Крупчак','9650024321','krupchakas@yandex.ru','1989/02/23','ROLE_ADMIN');
INSERT INTO address VALUES (1,'Россия','Санкт-Петербург','Бухарестская','114','24','192288','1');
