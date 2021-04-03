BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
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
        tag varchar(60) NOT NULL,
        user_id int NOT NULL,
        UNIQUE (tag, user_id),

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


COMMIT TRANSACTION;
