-- drop all the tables while testing
DROP TABLE IF EXISTS items;

-- generate tables
create table items
(
    id   serial,
    type      varchar(64),
    occasion varchar(50),
    style varchar(50),
    size varchar(50),
    color varchar(50),
    location varchar(50),
    container varchar(64),
    quantity    numeric,
    PRIMARY KEY (id)
);