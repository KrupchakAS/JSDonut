SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
-- Table: users
CREATE TABLE users (
  id          INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login       VARCHAR(16) NOT NULL,
  password    VARCHAR(60) NOT NULL,
  firstName   VARCHAR(32) NOT NULL,
  surName     VARCHAR(32) NOT NULL,
  phoneNumber VARCHAR(10) NOT NULL,
  email       VARCHAR(50) NOT NULL,
  birthDate   DATE        NOT NULL,
  role        VARCHAR(10) NOT NULL,
  UNIQUE (login, email)
)
  ENGINE = InnoDB;

-- Table: address
CREATE TABLE address (
  id              INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  city            VARCHAR(32) NOT NULL,
  street          VARCHAR(32) NOT NULL,
  houseNumber     SMALLINT    NOT NULL,
  apartmentNumber SMALLINT    NOT NULL,
  postCode        VARCHAR(32) NOT NULL,
  user_id         INT         NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

-- Table: category
CREATE TABLE category (
  id   INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  UNIQUE (name)
)
  ENGINE = InnoDB;

-- Table: filling
CREATE TABLE filling (
  id       TINYINT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(32) NOT NULL,
  calories SMALLINT    NOT NULL,
  price    FLOAT       NOT NULL,
  UNIQUE (name)
)
  ENGINE = InnoDB;

-- Table: corn
CREATE TABLE dough (
  id       TINYINT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(32) NOT NULL,
  calories SMALLINT    NOT NULL,
  price    FLOAT       NOT NULL,
  UNIQUE (name)
)
  ENGINE = InnoDB;
-- Table: SprinkleService
CREATE TABLE sprinkle (
  id       TINYINT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name     VARCHAR(32) NOT NULL,
  calories SMALLINT    NOT NULL,
  price    FLOAT       NOT NULL,
  UNIQUE (name)
)
  ENGINE = InnoDB;

CREATE TABLE products_sprinkle (
  product_id  INT     NOT NULL,
  sprinkle_id TINYINT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id),
  FOREIGN KEY (sprinkle_id) REFERENCES sprinkle (id)
)
  ENGINE = InnoDB;

-- Table: Order
CREATE TABLE orders (
  id             INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  paymentOption  TINYINT NOT NULL,
  deliveryOption TINYINT NOT NULL,
  paymentStatus  TINYINT NOT NULL,
  orderStatus    TINYINT NOT NULL,
  totalPrice     FLOAT   NOT NULL,
  purchaseDate   DATE    NOT NULL,
  user_id        INT     NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  address_id     INT,
  FOREIGN KEY (address_id) REFERENCES address (id)
)
  ENGINE = InnoDB;

CREATE TABLE orderProductsQuantity (
  id             INT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  quantity SMALLINT NOT NULL,
  order_id   INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id),
  product_id INT NOT NULL,
  FOREIGN KEY (product_id) REFERENCES products (id)

) ENGINE = InnoDB;

-- Table for mapping products and orders: order_products
CREATE TABLE order_products (
  order_id   INT NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (product_id) REFERENCES products (id)
)
  ENGINE = InnoDB;

-- Table: product
CREATE TABLE products (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(32)  NOT NULL,
  description VARCHAR(255) NOT NULL,
  image       VARCHAR(255),
  price       FLOAT        NOT NULL,
  workPrice   FLOAT        NOT NULL,
  weight      SMALLINT     NOT NULL,
  quantity    SMALLINT     NOT NULL,
  calories    SMALLINT     NOT NULL,
  user_id     INT,
  FOREIGN KEY (user_id) REFERENCES users (id),
  category_id INT          NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id),
  dough_id    TINYINT,
  FOREIGN KEY (dough_id) REFERENCES dough (id),
  filling_id  TINYINT,
  FOREIGN KEY (filling_id) REFERENCES filling (id)
)
  ENGINE = InnoDB;

-- Insert data!
INSERT INTO users VALUES (1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'Andrey', 'Krupchak', '9650024321','krupchakas@yandex.ru', '1989/02/23', 'ROLE_ADMIN');
INSERT INTO address VALUES (1, 'Saint-Petersburg', 'Buharestskay', '114', '24', '192288', '1');

INSERT INTO category VALUE (1, 'Donuts');
INSERT INTO category VALUE (2, 'Cakes');
INSERT INTO category VALUE (3, 'Pies');
INSERT INTO category VALUE (4, 'Macaroni');

INSERT INTO dough VALUE (1, 'Biscuit', 320, 70);
INSERT INTO dough VALUE (2, 'Shortbread', 440, 105);
INSERT INTO dough VALUE (3, 'Flaky Pastry', 405, 90);
INSERT INTO dough VALUE (4, 'Custard', 240, 125);
INSERT INTO dough VALUE (5, 'Almond', 465, 125);
INSERT INTO dough VALUE (6, 'Classic(yeast)', 425, 45);
INSERT INTO dough VALUE (7, 'Classic(unyeast)', 425, 45);

INSERT INTO filling VALUE (1, 'Chocolate(dark)', 410, 145);
INSERT INTO filling VALUE (2, 'Chocolate(milk)', 495, 125);
INSERT INTO filling VALUE (3, 'Banana', 260, 115);
INSERT INTO filling VALUE (4, 'Strawberry', 240, 125);
INSERT INTO filling VALUE (5, 'Cherry', 265, 130);
INSERT INTO filling VALUE (6, 'Kiwi', 190, 120);
INSERT INTO filling VALUE (7, 'Currant', 205, 100);
INSERT INTO filling VALUE (8, 'Custard cream', 160, 80);
INSERT INTO filling VALUE (9, 'Curd', 155, 90);
INSERT INTO filling VALUE (10, 'Vanilla', 125, 90);

INSERT INTO sprinkle VALUE (1, 'Colored balls(chocolate)', 390, 100);
INSERT INTO sprinkle VALUE (2, 'Colored noodle(glaze)', 385, 110);
INSERT INTO sprinkle VALUE (3, 'Colored confetti(glaze)', 387, 105);
INSERT INTO sprinkle VALUE (4, 'Coconut flakes', 399, 120);
INSERT INTO sprinkle VALUE (5, 'Poppy', 395, 130);
INSERT INTO sprinkle VALUE (6, 'Waffle Crumb', 280, 120);
INSERT INTO sprinkle VALUE (7, 'Chocolate Crumb', 495, 130);
INSERT INTO sprinkle VALUE (8, 'Vanilla', 400, 90);
INSERT INTO sprinkle VALUE (9, 'Nut slices', 735, 210);

INSERT INTO products VALUE (1, 'Chocolate', 'With chocolate glaze', NULL, 50, 20, 90, 20, 320, NULL, 1, 6, 1);
INSERT INTO products VALUE (2, 'Vanilla', 'With madagascar vanilla', NULL, 55, 15, 80, 45, 290, NULL, 1, 6, 3);
INSERT INTO products VALUE (3, 'Cherry', 'With ripe cherry', NULL, 55, 18, 85, 35, 300, NULL, 1, 6, 5);
INSERT INTO products VALUE (4, 'Apricot', 'With striking apricot', NULL, 52, 16, 75, 30, 310, NULL, 1, 6, 10);
INSERT INTO products VALUE (5, 'Bilberry', 'With sweet bilberry', NULL, 58, 18, 75, 250, 270, NULL, 1, 6, 10);

INSERT INTO products VALUE (6, 'Raspberry', 'Tartlet with raspberry', NULL, 40, 10, 66, 20, 230, NULL, 2, 2, 8);
INSERT INTO products VALUE (7, 'Black currant', 'Tartlet with black currant', NULL, 58, 12, 70, 45, 280, NULL, 2, 2, 8);
INSERT INTO products VALUE (8, 'Strawberry', 'Tartlet with strawberry', NULL, 50, 11, 71, 35, 290, NULL, 2, 2, 8);
INSERT INTO products VALUE (9, 'Eclair Vanilla', 'Eclair classic vanilla', NULL, 36, 9, 55, 30, 295, NULL, 2, 4, 8);
INSERT INTO products VALUE (10, 'Eclair Coffee', 'Eclair classic coffee', NULL, 38, 9, 65, 150, 280, NULL, 2, 4, 8);

INSERT INTO products VALUE (11, 'Almond', 'Two biscuits with a crispy layer of almonds in caramel and almond petals', NULL, 480, 120,330, 150, 390, NULL, 3, 5, 9);
INSERT INTO products VALUE (12, 'Cranberry', 'Cake with cranberry filling and a filling of white chocolate', NULL, 330, 80, 800, 12, 420, NULL, 3, 2, 10);
INSERT INTO products VALUE (13, 'Three Chocolate', 'A layer of brownie with milk chocolate air mousse, chocolate biscuit, covered with dark chocolate', NULL, 400, 90, 700, 22, 460, NULL, 3, 2, 9);
INSERT INTO products VALUE (14, 'Honey cake', 'Classic honey cake with traditional taste', NULL, 290, 85, 480, 25, 380, NULL, 3, 2, 10);
INSERT INTO products VALUE (15, 'Sacher', 'Traditional chocolate cake with apricot jam and chocolate glaze', NULL, 505, 110, 700, 9, 370,NULL, 3, 1, 10);

INSERT INTO products VALUE (16, 'Banana', 'French almond cookies with banana filling', NULL, 29, 6, 15, 500, 100, NULL, 4, 5, 3);
INSERT INTO products VALUE (17, 'Ginger', 'French almond cookies with ginger filling', NULL, 34, 6, 15, 450, 110, NULL, 4, 5, 6);
INSERT INTO products VALUE (18, 'Strawberry', 'French almond cookies with ginger filling', NULL, 38, 7, 15, 350, 100, NULL, 4, 5, 4);
INSERT INTO products VALUE (19, 'Cherry', 'French almond cookies with cherry filling', NULL, 34, 6, 15, 400, 110, NULL, 4, 5, 5);
INSERT INTO products VALUE (20, 'Currant', 'French almond cookies with currant filling', NULL, 34, 6, 15, 550, 120, NULL, 4, 5, 7);
