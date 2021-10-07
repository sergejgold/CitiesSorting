create table if not exists Cities
(
    idCities int auto_increment
    primary key,
    name varchar(45) null,
    region varchar(45) null,
    district varchar(45) null,
    population int null,
    foundation int null
    );
