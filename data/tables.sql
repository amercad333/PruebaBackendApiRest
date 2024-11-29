CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

CREATE TABLE branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE franchises (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE branches_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    branch_id INT NOT NULL,
    product_id  INT NOT NULL
);

CREATE TABLE franchises_branches (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    franchise_id  INT NOT NULL,
    branch_id INT NOT NULL
);