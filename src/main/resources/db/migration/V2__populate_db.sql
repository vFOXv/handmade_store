INSERT INTO roles(name)
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