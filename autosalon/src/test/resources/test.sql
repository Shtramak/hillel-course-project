CREATE TABLE customer(
                id int(10) PRIMARY KEY NOT NULL,
                name VARCHAR(64) NOT NULL,
                surname VARCHAR(128) NOT NULL,
                date_of_birth DATE NOT NULL,
                phone_number VARCHAR(24),
                available_funds DECIMAL(15,2) DEFAULT 0);