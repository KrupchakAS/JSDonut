SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;
-- Table: users
CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  firstName VARCHAR(255) NOT NULL,
  surName VARCHAR(255) NOT NULL,
  phoneNumber VARCHAR(10) NOT NULL,
  email VARCHAR(255) NOT NULL,
  birthDate DATE
)ENGINE = InnoDB;

-- Table: roles
CREATE TABLE roles (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(10) NOT NULL
)  ENGINE = InnoDB;

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  UNIQUE (user_id, role_id)
) ENGINE = InnoDB;

-- Table: address
CREATE TABLE address (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  country VARCHAR(32) NOT NULL,
  city VARCHAR(32) NOT NULL,
  postCode INT(10) NOT NULL ,
  street VARCHAR(32) NOT NULL ,
  houseNumber INT (10) NOT NULL ,
  apartmentNumber VARCHAR(10) NOT NULL,
  user_id INT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE = InnoDB;

-- Table: product
CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  image VARCHAR(255),
  price INT(10) NOT NULL ,
  weight INT(10) NOT NULL ,
  quantity INT(10),
  category_id INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id)
)ENGINE = InnoDB;

-- Table: category
CREATE TABLE category(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL
) ENGINE = InnoDB;

-- Table: parameters
CREATE TABLE parameters (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  calories INT(10) NOT NULL ,
  fat INT(10) NOT NULL ,
  carbohydrate INT(10) NOT NULL ,
  protein INT(10) NOT NULL ,
  composition VARCHAR(255) NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES product(id)
)ENGINE = InnoDB;

-- Table: order
CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL ,
  user_address_id INT NOT NULL  ,
  product_id INT NOT NULL ,
  paymentOption VARCHAR(32) NOT NULL ,
  deliveryOption VARCHAR(32) NOT NULL ,
  paymentStatus VARCHAR(32) NOT NULL ,
  orderStatus VARCHAR(32) NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (user_address_id) REFERENCES address(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE = InnoDB;

-- Insert data!
INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG','Андрей','Крупчак','9650024321','krupchakas@yandex.ru','1989/02/23');
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');
INSERT INTO user_roles VALUES (1, 1);