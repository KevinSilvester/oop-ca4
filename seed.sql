DROP DATABASE IF EXISTS `oop_movie_db`;
CREATE DATABASE `oop_movie_db`;
USE `oop_movie_db`;

DROP TABLE IF EXISTS `movies`;
CREATE TABLE movies
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `title`     varchar(255) NOT NULL,
    `year`      int          NOT NULL,
    `boxOffice` double(10, 1) NOT NULL,
    `director`  varchar(255) NOT NULL,
    `leadActor` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO movies (title, year, boxOffice, director, leadActor)
VALUES ('The Fast and the Furious', 2001, 207.3, 'Rob Cohen', 'Vin Diesel'),
       ('2 Fast 2 Furious', 2003, 236.4, 'John Singleton', 'Paul Walker'),
       ('The Fast and the Furious: Tokyo Drift', 2006, 158.9, 'Justin Lin', 'Sung Kang'),
       ('Fast & Furious', 2009, 360.4, 'Justin Lin', 'Vin Diesel'),
       ('Fast Five', 2011, 626.1, 'Justin Lin', 'Vin Diesel'),
       ('Fast & Furious 6', 2013, 788.7, 'Justin Lin', 'Vin Diesel'),
       ('Furious 7', 2015, 1516.0, 'James Wan', 'Vin Diesel'),
       ('The Fate of the Furious', 2017, 1239.0, 'F. Gary Gray', 'Vin Diesel'),
       ('F9', 2021, 726.2, 'Justin Lin', 'Vin Diesel'),
       ('Toy Story', 1995, 373.0, 'John Lasseter', 'Tom Hanks');
