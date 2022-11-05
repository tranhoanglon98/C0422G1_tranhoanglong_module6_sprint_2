drop database if exists book_store;
create database book_store;

use book_store;

CREATE TABLE city (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(60)
);

CREATE TABLE province (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(60),
    city_id BIGINT,
    FOREIGN KEY (city_id)
        REFERENCES city (id)
);

CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `street` VARCHAR(200),
    province_id BIGINT,
    FOREIGN KEY (province_id)
        REFERENCES province (id)
);

CREATE TABLE app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    address_id BIGINT,
    FOREIGN KEY (address_id)
        REFERENCES address (id)
);

CREATE TABLE app_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR(30)
);

CREATE TABLE `account` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30),
    `password` VARCHAR(200),
    user_id BIGINT,
    FOREIGN KEY (user_id)
        REFERENCES app_user (id)
);

CREATE TABLE account_role (
    account_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (account_id , role_id),
    FOREIGN KEY (account_id)
        REFERENCES `account` (id),
    FOREIGN KEY (role_id)
        REFERENCES app_role (id)
);


CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100)
);

CREATE TABLE book (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200),
    author VARCHAR(100),
    summary TEXT,
    price DOUBLE,
    quantity INT
);

CREATE TABLE book_detail (
    book_id BIGINT,
    category_id BIGINT,
    PRIMARY KEY (book_id , category_id),
    FOREIGN KEY (book_id)
        REFERENCES book (id),
    FOREIGN KEY (category_id)
        REFERENCES category (id)
);

CREATE TABLE cart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    account_id BIGINT,
    FOREIGN KEY (account_id)
        REFERENCES `account` (id)
);

CREATE TABLE cart_detail (
    cart_id BIGINT,
    book_id BIGINT,
    PRIMARY KEY (cart_id , book_id),
    FOREIGN KEY (cart_id)
        REFERENCES cart (id),
    FOREIGN KEY (book_id)
        REFERENCES book (id)
);

CREATE TABLE invoice (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    `code` VARCHAR(20),
    `time` DATE,
    account_id BIGINT,
    FOREIGN KEY (account_id)
        REFERENCES `account` (id)
);

CREATE TABLE invoice_detail (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    book_id BIGINT,
    invoice_id BIGINT,
    FOREIGN KEY (book_id)
        REFERENCES book (id),
    FOREIGN KEY (invoice_id)
        REFERENCES invoice (id)
);