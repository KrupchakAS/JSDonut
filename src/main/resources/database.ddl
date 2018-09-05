SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
-- Table: users
CREATE TABLE users (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login       VARCHAR(16)  NOT NULL,
  password    VARCHAR(100) NOT NULL,
  firstName   VARCHAR(32)  NOT NULL,
  surName     VARCHAR(32)  NOT NULL,
  phoneNumber VARCHAR(10)  NOT NULL,
  email       VARCHAR(50)  NOT NULL,
  birthDate   DATE         NOT NULL,
  role        VARCHAR(10)  NOT NULL,
  UNIQUE (login, email)
)
  ENGINE = InnoDB;

-- Table: address
CREATE TABLE address (
  id              INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
  country         VARCHAR(32) NOT NULL,
  city            VARCHAR(32) NOT NULL,
  street          VARCHAR(32) NOT NULL,
  houseNumber     SMALLINT    NOT NULL,
  apartmentNumber SMALLINT    NOT NULL,
  postCode        INT         NOT NULL,
  user_id         INT         NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB;

-- Table: category
CREATE TABLE category (
  id   TINYINT     NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
CREATE TABLE corn (
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
  user_id        INT     NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id),
  address_id     INT     NOT NULL,
  FOREIGN KEY (address_id) REFERENCES address (id)
)
  ENGINE = InnoDB;

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
  id             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name           VARCHAR(32)  NOT NULL,
  description    VARCHAR(255) NOT NULL,
  image          VARCHAR(255),
  price          FLOAT        NOT NULL,
  priceForCustom FLOAT,
  weight         SMALLINT     NOT NULL,
  quantity       SMALLINT,
  calories       SMALLINT     NOT NULL,
  creator        VARCHAR(32)  NOT NULL,
  category_id    TINYINT      NOT NULL,
  FOREIGN KEY (category_id) REFERENCES category (id),
  corn_id        TINYINT,
  FOREIGN KEY (corn_id) REFERENCES corn (id),
  filling_id     TINYINT,
  FOREIGN KEY (filling_id) REFERENCES filling (id),
  UNIQUE (name, image)
)
  ENGINE = InnoDB;

-- Insert data!
INSERT INTO users VALUES(1, 'admin', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG', 'Андрей', 'Крупчак', '9650024321','krupchakas@yandex.ru', '1989/02/23', 'ROLE_ADMIN');
INSERT INTO address VALUES (1, 'Россия', 'Санкт-Петербург', 'Бухарестская', '114', '24', '192288', '1');

INSERT INTO category VALUE (1, 'Донатсы');
INSERT INTO category VALUE (2, 'Пироженные');
INSERT INTO category VALUE (3, 'Торты');
INSERT INTO category VALUE (4, 'Макаруны');

INSERT INTO corn VALUE (1, 'Бисквитный', 320, 70);
INSERT INTO corn VALUE (2, 'Песочный', 440, 105);
INSERT INTO corn VALUE (3, 'Слоеный', 405, 90);
INSERT INTO corn VALUE (4, 'Безе', 240, 125);
INSERT INTO corn VALUE (5, 'Миндальный', 465, 225);

INSERT INTO filling VALUE (1, 'Шоколад(темный)', 410, 145);
INSERT INTO filling VALUE (2, 'Шоколад(молочный)', 495, 125);
INSERT INTO filling VALUE (3, 'Банан', 260, 115);
INSERT INTO filling VALUE (4, 'Клубника', 240, 125);
INSERT INTO filling VALUE (5, 'Вишня', 265, 130);
INSERT INTO filling VALUE (6, 'Киви', 190, 120);
INSERT INTO filling VALUE (7, 'Сгущеное молоко', 305, 100);
INSERT INTO filling VALUE (8, 'Заварной крем', 160, 80);
INSERT INTO filling VALUE (9, 'Творог', 155, 90);

INSERT INTO sprinkle VALUE (1, 'Шарики разноцветные(шоколад)', 390, 100);
INSERT INTO sprinkle VALUE (2, 'Вермишель разноцветная(глазурь)', 385, 110);
INSERT INTO sprinkle VALUE (3, 'Конфетти разноцветная(глазурь)', 387, 105);
INSERT INTO sprinkle VALUE (4, 'Кокосовая стружка', 399, 120);
INSERT INTO sprinkle VALUE (5, 'Мак', 395, 130);
INSERT INTO sprinkle VALUE (6, 'Вафельная крошка', 280, 120);
INSERT INTO sprinkle VALUE (7, 'Шоколадная крошка', 495, 130);
INSERT INTO sprinkle VALUE (8, 'Кунжут', 535, 80);

INSERT INTO products VALUE (1, 'Шоколадный', 'С шоколадной глазурью', NULL, 70.5, NULL, 90, 20, 320, 'admin', 1, NULL, NULL);
INSERT INTO products VALUE (2, 'Ванильный', 'С мадагаскарской ванилью', NULL, 55, NULL, 80, 45, 290, 'admin', 1, NULL, NULL);
INSERT INTO products VALUE (3, 'Вишневый', 'Со спелой вишней', NULL, 73.6, NULL, 85, 35, 300, 'admin', 1, NULL, NULL);
INSERT INTO products VALUE (4, 'Абрикосовый', 'С ярким абрикосом', NULL, 68.1, NULL, 75, 30, 310, 'admin', 1, NULL, NULL);
INSERT INTO products VALUE (5, 'Черничный', 'Со сладкой черникой', NULL, 77, NULL, 75, 250, 270, 'admin', 1, NULL, NULL);

INSERT INTO products VALUE (6, 'Малиновая радость', 'Тарталетка Малиновая', NULL, 50.2, NULL, 66, 20, 230, 'admin', 2, NULL, NULL);
INSERT INTO products VALUE (7, 'Черная Смородинка', 'Тарталетка с Черной Смородиной', NULL, 58.7, NULL, 70, 45, 280, 'admin', 2, NULL, NULL);
INSERT INTO products VALUE (8, 'Клубника-шейк', 'Тарталетка Клубничная', NULL, 61.2, NULL, 71, 35, 290, 'admin', 2, NULL, NULL);
INSERT INTO products VALUE (9, 'Эклер Ваниль', 'Эклер Классический ванильный', NULL, 46.1, NULL, 55, 30, 295, 'admin', 2, NULL, NULL);
INSERT INTO products VALUE (10, 'Эклер Кофейный', 'Эклер Классический кофейный', NULL, 47, NULL, 65, 150, 280, 'admin', 2, NULL, NULL);

INSERT INTO products VALUE (11, 'Миндальный', 'Два бисквитных коржа с хрустящим слоем миндаля в карамели и лепестками миндаля', NULL, 600, NULL, 330, 150, 390, 'admin', 3, NULL, NULL);
INSERT INTO products VALUE (12, 'Брусничный', 'Пирог с брусничной начинкой и начинкой из белого шоколада', NULL, 410, NULL, 800, 12, 420, 'admin', 3, NULL, NULL);
INSERT INTO products VALUE (13, 'Три шоколада', 'Слой брауни с воздушным муссом из молочного шоколада, шоколадным бисквитом, покрыт темным шоколадом', NULL, 500, NULL, 700, 22, 460, 'admin', 3, NULL, NULL);
INSERT INTO products VALUE (14, 'Медовик', 'Класический медовик с традиционным вкусом', NULL, 390, NULL, 480, 25, 380, 'admin', 3, NULL, NULL);
INSERT INTO products VALUE (15, 'Захер', 'Традиционный шоколадный торт с абрикосовым джемом и шоколадной глазурью', NULL, 620, NULL, 700, 9, 370, 'admin', 3, NULL, NULL);

INSERT INTO products VALUE (16, 'Корица-Мускат', 'Французское миндальное печенье с корицей и мускатом и с начинкой', NULL, 35, NULL, 15, 500, 100, 'admin', 4, NULL, NULL);
INSERT INTO products VALUE (17, 'Имбирь', 'Французское миндальное имбирное печенье с начинкой.', NULL, 40, NULL, 15, 450, 110, 'admin', 4, NULL, NULL);
INSERT INTO products VALUE (18, 'Фисташка', 'Французское миндальное фисташковое печенье с начинкой', NULL, 45, NULL, 15, 350, 100, 'admin', 4, NULL, NULL);
INSERT INTO products VALUE (19, 'Вишня', 'Французское миндальное вишневое печенье с начинкой', NULL, 40, NULL, 15, 400, 110, 'admin', 4, NULL, NULL);
INSERT INTO products VALUE (20, 'Смородина', 'Французское миндальное смородина микс печенье с начинкой', NULL, 40, NULL, 15, 550, 120, 'admin', 4, NULL, NULL);
