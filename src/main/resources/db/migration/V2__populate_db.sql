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
VALUES  (1, 'jewelry'),
        (2, 'bracelets'),
        (3, 'pendants'),
        (4, 'earrings'),
        (5, 'broches'),
        (6, 'picture'),
        (7, 'knives'),
        (8, 'curtains');

INSERT INTO materials(id, material_name)
VALUES  (1, 'wax'),
        (2, 'paper'),
        (3, 'steel'),
        (4, 'textile');

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

INSERT INTO items(name, description, created_at, category_id, material_id, price, discount)
VALUES  ('mint scented candle', 'scented candle with mint flavor', CURRENT_DATE, 1, 1, 10, 10),
        ('Landscape with deer', 'large painting "Landscape with a deer"', CURRENT_DATE,2, 2, 45, 0),
        ('kitchen knife', 'artistic kitchen knife', CURRENT_DATE, 3, 3, 30,5),
        ('curtains', 'pink curtains', CURRENT_DATE, 4, 4, 25, 0);

INSERT INTO items_colors(item_id, color_id, quantity)
VALUES  (1, 1, 5),
        (1, 2, 4),
        (1, 3, 3),
        (2, 9, 2),
        (3, 7, 5),
        (4, 8, 4),
        (4, 5, 6);