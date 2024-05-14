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
