DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(255) UNIQUE,
  releaseYear VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO movies (name,releaseYear,country) VALUES ("シン・ゴジラ", 2016, "日本");
INSERT INTO movies (name,releaseYear,country) VALUES ("RRR", 2022, "インド");
INSERT INTO movies (name,releaseYear,country) VALUES ("アイアンマン", 2008, "アメリカ");

CREATE VIEW movies_view AS
SELECT id, name, CONCAT(releaseYear, '年') AS releaseYear, country
FROM movies;
