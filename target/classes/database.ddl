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
  name VARCHAR(100) NOT NULL
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
  postCode INT NOT NULL ,
  street VARCHAR(32) NOT NULL ,
  houseNumber INT (32) NOT NULL ,
  apartment VARCHAR(32) NOT NULL
) ENGINE = InnoDB;

-- Table for mapping user and address: user_address
CREATE TABLE user_address (
  user_id INT NOT NULL,
  address_id INT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (address_id) REFERENCES address (id),
  UNIQUE (user_id, address_id)
) ENGINE = InnoDB;

-- Table: product
CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  price INT NOT NULL ,
  weight INT NOT NULL ,
  size INT NOT NULL ,
  quantity INT,
  image VARCHAR(255),
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
  calories INT NOT NULL ,
  fat INT NOT NULL ,
  carbohydrate INT NOT NULL ,
  protein INT NOT NULL ,
  composition VARCHAR(255) NOT NULL
)ENGINE = InnoDB;

-- Table for mapping product and parameters: product_parameters
CREATE TABLE product_parameters (
  product_id INT NOT NULL,
  parameters_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES product (id),
  FOREIGN KEY (parameters_id) REFERENCES parameters (id),
  UNIQUE (product_id, parameters_id)
) ENGINE = InnoDB;

-- Table: order
CREATE TABLE order (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL ,
  user_address_id VARCHAR(255) NOT NULL ,
  
)