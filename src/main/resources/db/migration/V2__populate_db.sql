INSERT INTO roles(role_name)
VALUES('ROLE_USER'),
      ('ROLE_ADMIN');

INSERT INTO users(username, password, email, is_enabled)
VALUES ('user', '$2a$12$owtTS8Q5teMgBiUMju1cy.NNDMGUhEKnelNJ8uL2Q/4FsvFg7/6Yq', 'user@gmail.com', true),
       ('admin', '$2a$12$owtTS8Q5teMgBiUMju1cy.NNDMGUhEKnelNJ8uL2Q/4FsvFg7/6Yq', 'admin@gmail.com', true);
-- user           |   password: Admin123
-- admin          |   password: Admin123

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);

INSERT INTO categories(id, category_name)
VALUES  (1, 'candles'),
        (2, 'picture'),
        (3, 'knives'),
        (4, 'curtains');

INSERT INTO materials(id, material_name)
VALUES  (1, 'wax'),
        (2, 'paper'),
        (3, 'steel'),
        (4, 'textile');

INSERT INTO styles(id, style_name)
VALUES  (1, 'light'),
        (2, 'dark'),
        (3, 'pinkish'),
        (4, 'official');

INSERT INTO colors(id, color_name)
VALUES  (1, 'red'),
        (2, 'orange'),
        (3, 'yellow'),
        (4, 'green'),
        (5, 'blue'),
        (6, 'purple'),
        (7, 'grey'),
        (8, 'pink'),
        (9, 'coloring');

INSERT INTO items(name, description, category_id, material_id, style_id, price, discount)
VALUES  ('mint scented candle', 'scented candle with mint flavor', 1, 1, 4, 10, 10),
        ('Landscape with deer', 'large painting "Landscape with a deer"',2, 2, 2, 45, 0),
        ('kitchen knife', 'artistic kitchen knife', 3, 3, 1, 30,5),
        ('curtains', 'pink curtains', 4, 4, 3, 25, 0);

INSERT INTO items_colors(item_id, color_id, quantity)
VALUES  (1, 1, 5),
        (1, 2, 4),
        (1, 3, 3),
        (2, 9, 2),
        (3, 7, 5),
        (4, 8, 4),
        (4, 5, 6);