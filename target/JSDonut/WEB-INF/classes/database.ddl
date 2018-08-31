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
  address_id INT ,
  FOREIGN KEY (address_id) REFERENCES address (id),
  UNIQUE (login,phoneNumber,email)
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
  country VARCHAR(32) NOT NULL ,
  city VARCHAR(32) NOT NULL ,
  street VARCHAR(32) NOT NULL ,
  houseNumber SMALLINT NOT NULL ,
  apartmentNumber SMALLINT NOT NULL,
  postCode INT NOT NULL
) ENGINE = InnoDB;

-- Table: product
CREATE TABLE products (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL ,
  description VARCHAR(255) NOT NULL ,
  image VARCHAR(255),
  price FLOAT NOT NULL ,
  weight SMALLINT NOT NULL ,
  quantity SMALLINT,
  category_id INT NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id),
  parameters_id INT NOT NULL,
  FOREIGN KEY (parameters_id) REFERENCES parameters(id),
  order_id INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  UNIQUE (name,image)
)ENGINE = InnoDB;

-- Table: category
CREATE TABLE category(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL
) ENGINE = InnoDB;

-- Table: parameters
CREATE TABLE parameters (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  calories SMALLINT NOT NULL ,
  fat TINYINT NOT NULL ,
  carbohydrate TINYINT NOT NULL ,
  protein TINYINT NOT NULL ,
  composition VARCHAR(255) NOT NULL
)ENGINE = InnoDB;

-- Table: Order
CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  paymentOption TINYINT NOT NULL ,
  deliveryOption TINYINT NOT NULL ,
  paymentStatus TINYINT NOT NULL ,
  orderStatus TINYINT NOT NULL ,
  user_id INT NOT NULL ,
  FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE = InnoDB;

-- Insert data!
INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG','Андрей','Крупчак','9650024321','krupchakas@yandex.ru','1989/02/23','1');
INSERT INTO address VALUES (1,'Россия','Санкт-Петербург','Бухарестская','114','24','192288');
INSERT INTO roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO roles VALUES (2, 'ROLE_USER');
INSERT INTO user_roles VALUES (1, 1);