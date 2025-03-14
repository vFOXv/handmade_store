CREATE TABLE IF NOT EXISTS users(
                      id 		BIGSERIAL PRIMARY KEY,
                      username	VARCHAR(30) NOT NULL UNIQUE,
                      password	VARCHAR(100) NOT NULL,
                      email		VARCHAR(50) NOT NULL UNIQUE,
                      is_enabled 	BOOLEAN NOT NULL
                      );

CREATE TABLE IF NOT EXISTS roles(
                      id		BIGSERIAL PRIMARY KEY,
                      role_name	VARCHAR(50) NOT NULL
                      );

CREATE TABLE IF NOT EXISTS users_roles(
                      user_id	BIGSERIAL NOT NULL,
                      role_id	BIGSERIAL NOT NULL,
                      PRIMARY KEY(user_id, role_id),
                      FOREIGN KEY(user_id) REFERENCES users(id),
                      FOREIGN KEY(role_id) REFERENCES roles(id)
                      );

CREATE TABLE IF NOT EXISTS categories(
						id 		BIGSERIAL PRIMARY KEY,
						category_name	VARCHAR(50) NOT NULL
						);

CREATE TABLE IF NOT EXISTS materials(
						id 		BIGSERIAL PRIMARY KEY,
						material_name	VARCHAR(50) NOT NULL
						);

CREATE TABLE IF NOT EXISTS colors(
						id 		BIGSERIAL PRIMARY KEY,
						color_name	VARCHAR(50) NOT NULL
						);

CREATE TABLE IF NOT EXISTS items(
						id 		BIGSERIAL PRIMARY KEY,
						name		VARCHAR(50) NOT NULL,
						description	VARCHAR(5000),
                        created_at  DATE NOT NULL,
						category_id BIGSERIAL NOT NULL,
						material_id BIGSERIAL NOT NULL,
						price		MONEY NOT NULL,
						discount	INT DEFAULT 0 CHECK(discount >=0 AND discount <= 100) NOT NULL,
						FOREIGN KEY(category_id) REFERENCES categories(id),
						FOREIGN KEY(material_id) REFERENCES materials(id)
						);

CREATE TABLE items_colors(
						id 		BIGSERIAL PRIMARY KEY,
						item_id 	BIGSERIAL NOT NULL,
						color_id 	BIGSERIAL NOT NULL,
						quantity 	INT NOT NULL,
						FOREIGN KEY(item_id) REFERENCES items(id),
						FOREIGN KEY(color_id) REFERENCES colors(id)
						);

CREATE TABLE IF NOT EXISTS liked_products(
						user_id 	BIGSERIAL NOT NULL,
						item_id 	BIGSERIAL NOT NULL,
						PRIMARY KEY(user_id, item_id),
						FOREIGN KEY(user_id) REFERENCES users(id),
						FOREIGN KEY(item_id) REFERENCES items(id)
						);

CREATE TABLE IF NOT EXISTS orders(
						id 		BIGSERIAL PRIMARY KEY,
						user_id 	BIGSERIAL NOT NULL,
						create_time TIMESTAMP DEFAULT now(),
						is_paid 	BOOLEAN DEFAULT FALSE NOT NULL,
						is_delivery 	BOOLEAN DEFAULT FALSE NOT NULL,
						FOREIGN KEY(user_id) REFERENCES users(id)
						);

CREATE TABLE IF NOT EXISTS orders_items(
						id 		BIGSERIAL PRIMARY KEY,
						order_id 	BIGSERIAL NOT NULL,
						item_id		BIGSERIAL NOT NULL,
						quantity 	INT NOT NULL,
						FOREIGN KEY(order_id) REFERENCES orders(id),
						FOREIGN KEY(item_id) REFERENCES items(id)
						);

CREATE TABLE IF NOT EXISTS images(
						id BIGSERIAL PRIMARY KEY,
						image_url VARCHAR(255) NOT NULL,
						item_id BIGSERIAL,
						FOREIGN KEY (item_id) REFERENCES items(id)
						);

CREATE TABLE IF NOT EXISTS reviews(
						id BIGSERIAL PRIMARY KEY,
						review_text VARCHAR(2000) NOT NULL,
						item_id BIGSERIAL,
						user_id BIGSERIAL,
						FOREIGN KEY (user_id) REFERENCES users(id),
						FOREIGN KEY (item_id) REFERENCES items(id)
						);

CREATE TABLE IF NOT EXISTS buskets(
						id 		BIGSERIAL PRIMARY KEY,
						user_id 	BIGSERIAL NOT NULL,
						item_id		BIGSERIAL NOT NULL,
						quantity 	INT NOT NULL,
						FOREIGN KEY(user_id) REFERENCES users(id),
						FOREIGN KEY(item_id) REFERENCES items(id)
						);

CREATE TABLE IF NOT EXISTS confirmation_tokens(
                      id BIGSERIAL PRIMARY KEY,
                      token VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      expires_at TIMESTAMP NOT NULL,
                      confirmed_at TIMESTAMP,
                      user_id BIGSERIAL NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users(id)
                      );

CREATE TABLE IF NOT EXISTS reset_password_tokens(
                      id BIGSERIAL PRIMARY KEY,
                      token VARCHAR(255) NOT NULL,
                      created_at TIMESTAMP NOT NULL,
                      expires_at TIMESTAMP NOT NULL,
                      confirmed_at TIMESTAMP,
                      user_id BIGSERIAL NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users(id)
                      );

CREATE TABLE IF NOT EXISTS refresh_tokens(
                      id BIGSERIAL PRIMARY KEY,
                      token VARCHAR(255) NOT NULL,
                      expires_at TIMESTAMP NOT NULL,
                      user_id BIGSERIAL NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users(id)
                      );
