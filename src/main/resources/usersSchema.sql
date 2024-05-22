-- Create roles table
CREATE TABLE roles (
   id SERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL
);

-- Create users table
CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL
);

-- Create user_roles table to link users and roles
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
            REFERENCES users(id)
            ON DELETE CASCADE,
    CONSTRAINT fk_role
        FOREIGN KEY(role_id)
            REFERENCES roles(id)
            ON DELETE CASCADE
);
-- Create books table
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  price DECIMAL(6, 2) NOT NULL,
  quantity INT NOT NULL
);

-- Insert books into 'books' table
INSERT INTO books (title, author, price, quantity) VALUES
   ('To Kill a Mockingbird', 'Harper Lee', 10.99, 100),
   ('1984', 'George Orwell', 8.99, 150),
   ('The Great Gatsby', 'F. Scott Fitzgerald', 12.99, 200),
   ('Pride and Prejudice', 'Jane Austen', 9.99, 120),
   ('Moby Dick', 'Herman Melville', 11.99, 80);
