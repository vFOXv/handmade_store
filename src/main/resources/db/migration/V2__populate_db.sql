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

INSERT INTO subcategories(id, name)
VALUES  (1, 'bracelets'),
        (2, 'pendants'),
        (3, 'earrings'),
        (4, 'candle'),
        (5, 'image'),
        (6, 'cutlery'),
        (7, 'tulle'),
        (8, 'lighter');

INSERT INTO categories(id, category_name, subcategory_id)
VALUES  ( 1,'jewelry', 1),
        ( 2,'picture',5),
        ( 3,'knives', 6),
        ( 4,'curtains', 7),
        ( 5,'light', 4),
        ( 6,'light', 8);

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

INSERT INTO items(name, description, created_at, category_id, material_id, price, quantity, discount)
VALUES  ('bear shaped candle', 'brown bear shaped candle', '2025-03-11', 5, 1, 12, 4,12),
        ('pig shaped candle', 'pink pig shaped candle', '2025-03-13', 5, 1, 13, 5,15),
        ('mint scented candle', 'scented candle with mint flavor', '2025-03-14', 5, 1, 6,10, 10),
        ('Landscape with deer', 'large painting "Landscape with a deer"', '2025-03-15',2, 2, 7,45, 0),
        ('kitchen knife', 'artistic kitchen knife', '2025-03-12', 3, 3, 30, 1,5),
        ('curtains', 'pink curtains', CURRENT_DATE, 4, 4, 25, 2,0);

INSERT INTO items_colors(item_id, color_id)
VALUES  (1, 1),
        (1, 2),
        (1, 3),
        (2, 9),
        (3, 7),
        (4, 8),
        (4, 5);

INSERT INTO images(item_id, image_url)
VALUES  (1, 'http://example.com/photo1_1.jpg'),
        (1, 'http://example.com/photo1_2.jpg'),
        (1, 'http://example.com/photo1_3.jpg'),
        (2, 'http://example.com/photo2_1.jpg'),
        (2, 'http://example.com/photo2_2.jpg');