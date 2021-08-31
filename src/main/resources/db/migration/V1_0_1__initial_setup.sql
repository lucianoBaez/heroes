CREATE TABLE CAR_ENTITY
(
    CAR_ID    IDENTITY NOT NULL PRIMARY KEY,
    MAKE     VARCHAR  NOT NULL,
    NUMBER_OF_SEATS INTEGER
);

CREATE TABLE hero
(
    pkey_     IDENTITY NOT NULL PRIMARY KEY,
    name_     VARCHAR  NOT NULL,
    duration_ VARCHAR  NOT NULL
);

insert into hero (name_, duration_)
values ('a', 'a');
insert into hero (name_, duration_)
values ('b', 'b');
insert into hero (name_, duration_)
values ('c', 'c');

--http://localhost:8080/h2-console/