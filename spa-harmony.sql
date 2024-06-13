-- create database spa_harmony;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS t01_customer;
CREATE TABLE t01_customer (
    t01_customer_id CHAR(36) PRIMARY KEY,
    t01_name VARCHAR(255) NOT NULL,
    t01_email VARCHAR(255) UNIQUE NOT NULL,
    t01_phone VARCHAR(10) UNIQUE NOT NULL,
    t01_created_date DATE,
    t01_birth_date DATE,
    t01_enabled BOOLEAN NOT NULL DEFAULT TRUE
);

DROP TABLE IF EXISTS t02_user;
CREATE TABLE t02_user (
    t02_user_id CHAR(36) PRIMARY KEY,
    t02_username VARCHAR(255) UNIQUE NOT NULL,
    t02_password VARCHAR(255) NOT NULL,
    t02_email VARCHAR(255) UNIQUE NOT NULL,
	t02_customer_id CHAR(36) NOT NULL,
    t02_enabled BOOLEAN NOT NULL DEFAULT TRUE
);

DROP TABLE IF EXISTS t04_user_role;
CREATE TABLE t04_user_role (
    t04_user_role_id CHAR(36) PRIMARY KEY,
    t04_user_id CHAR(36) NOT NULL,
    t04_role_id INT NOT NULL
);

DROP TABLE IF EXISTS t03_role;
CREATE TABLE t03_role (
    t03_role_id INT PRIMARY KEY,
    t03_name VARCHAR(255) UNIQUE NOT NULL
);


-- Add foreign key to t04_user_role table for t04_user_id
ALTER TABLE t04_user_role
ADD CONSTRAINT fk_t04_user_role_user
FOREIGN KEY (t04_user_id) REFERENCES t02_user(t02_user_id) ON DELETE CASCADE;

-- Add foreign key to t04_user_role table for t04_role_id
ALTER TABLE t04_user_role
ADD CONSTRAINT fk_t04_user_role_role
FOREIGN KEY (t04_role_id) REFERENCES t03_role(t03_role_id) ON DELETE CASCADE;

ALTER TABLE t02_user
ADD CONSTRAINT fk_t02_user_customer
FOREIGN KEY (t02_customer_id) REFERENCES t01_customer(t01_customer_id);

SET FOREIGN_KEY_CHECKS = 1;

