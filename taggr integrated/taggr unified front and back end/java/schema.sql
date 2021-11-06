BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS photos CASCADE;
DROP TABLE IF EXISTS tags CASCADE;
DROP TABLE IF EXISTS photo_and_tag_relation CASCADE;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users  (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS photos (
        photo_id SERIAL PRIMARY KEY,
        user_id int NOT NULL,
        url varchar(200) NOT NULL,
        UNIQUE (url, user_id),
        description varchar (280) NOT NULL,

        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
        );

CREATE TABLE IF NOT EXISTS tags (
        tag_id SERIAL PRIMARY KEY,
        tag_name varchar(60) NOT NULL,
        user_id int NOT NULL,
        UNIQUE (tag_name, user_id),

        CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
        );

CREATE TABLE IF NOT EXISTS photo_and_tag_relation (
        photo_id int NOT NULL,
        tag_id int NOT NULL,

        CONSTRAINT pk_photo_id_tag_id PRIMARY KEY (photo_id, tag_id),
        CONSTRAINT fk_photo_id FOREIGN KEY (photo_id) REFERENCES photos(photo_id),
        CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
        );

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('danmess','$2a$10$DE2ZHxNrHeSvJvSx5dUhne5Jn6x1zt4l1hhhN50MugfHRmds6VRAW', 'ROLE_USER');

INSERT INTO photos (user_id, url, description)
VALUES (3,'https://www.instagram.com/p/CNVUYUwJmfw/','whole wheat oat and chocolate chip cookies'),
       (3, 'https://www.instagram.com/p/CNSzS23pfP4/', 'egg salad on rye'),
       (3, 'https://www.instagram.com/p/CNPukgcpQAS/', 'carrot cake pancakes'),
       (3, 'https://www.instagram.com/p/CNNr89PJuvs/', 'chicken salad rye toasts'),
       (3, 'https://www.instagram.com/p/CNNJDwEJeQ9/', 'eggs baked in spinach');

INSERT INTO tags (tag_name, user_id)
VALUES ('baking', 3),
('chocolate', 3),
('cookies', 3),
('healthy-ish', 3),
('egg', 3),
('light', 3),
('Easter', 3),
('brunch', 3),
('carrot', 3),
('pancake', 3),
('herbs', 3),
('chicken', 3),
('rye', 3),
('toast', 3),
('eggs', 3),
('vegetarian', 3),
('mushroom', 3),
('spinach', 3);

INSERT INTO photo_and_tag_relation (photo_id, tag_id)
VALUES (1,1),
(1,2),
(1,3),
(1,4),
(2,5),
(2,6),
(2,7),
(2,8),
(2,13),
(3,7),
(3,10),
(3,8),
(4,8),
(4,11),
(4,12),
(4,13),
(4,14),
(5,15),
(5,16),
(5,17),
(5,18),
(5,8);

COMMIT TRANSACTION;
