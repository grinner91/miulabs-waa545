----------------------
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (1, 'zaman@miu.edu', 'Monir', 'Zaman', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123
INSERT INTO users (id, email, firstname, lastname, password)
VALUES (2, 'rafsan@miu.edu', 'Rafsan', 'Zaman', '$2a$12$IKEQb00u5QpZMx4v5zMweu.3wrq0pS7XLCHO4yHZ.BW/yvWu1feo2'); --123

-------------------
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'CLIENT');

---------------
INSERT INTO users_roles (user_id, roles_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, roles_id)
VALUES (2, 1);
---------------

INSERT INTO posts (title, content, user_id)
VALUES ('Things Fall Apart', 'https://en.wikipedia.org/wiki/Things_Fall_Apart', 1);
INSERT INTO posts (title, content, user_id)
VALUES ('Fairy tales', 'https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection', 1);
INSERT INTO posts (title, content, user_id)
VALUES ('The Divine Comedy', 'https://en.wikipedia.org/wiki/Divine_Comedy', 2);
INSERT INTO posts (title, content, user_id)
VALUES ('The Epic Of Gilgamesh', 'https://en.wikipedia.org/wiki/Epic_of_Gilgamesh', 1);
INSERT INTO posts (title, content, user_id)
VALUES ('Achaemenid Empire', 'https://en.wikipedia.org/wiki/Book_of_Job', 2);

------------------------
INSERT INTO comments (content, post_id) VALUES ('Awesome!!!', 1);
INSERT INTO comments (content, post_id) VALUES ('What do you think!!!', 1);
INSERT INTO comments (content, post_id) VALUES ('This is not good!!!', 2);
INSERT INTO comments (content, post_id) VALUES ('Always!!!', 3);
