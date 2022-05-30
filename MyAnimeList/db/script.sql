create table animes
(
    id            varchar(50)
        constraint animes_pk
            primary key,
    title         VARCHAR(100) default NULL not null,
    title_english VARCHAR(100) default NULL not null,
    status        VARCHAR(50)  default NULL not null,
    genre         VARCHAR(50)  default NULL not null,
    releaseDate   DATE         default NULL not null,
    imageUrl      text        not null,
    episodes      integer     not null,
    rating        varchar(50) not null,
    type          varchar(20)
);

create table sqlite_master
(
    type     text,
    name     text,
    tbl_name text,
    rootpage int,
    sql      text
);

create table usuarios
(
    id              varchar(50)  not null
        constraint usuarios_pk
            primary key,
    nombre          varchar(100) not null,
    date_alta       Date         not null,
    password        varchar(50)  not null,
    imageurl        text,
    email           varchar(100) not null,
    date_nacimiento Date,
    admin           boolean default false
);

create table animeLists
(
    idUser  varhcar(50)
        references animes
        references usuarios,
    idAnime varchar(50),
    constraint animeLists_pk
        primary key (idUser, idAnime)
);

create table reviews
(
    idUser  varchar(50) not null
        references usuarios,
    idAnime varchar(50) not null
        references animes,
    score   integer,
    id      varchar(50)
        constraint reviews_pk
            primary key,
    review  varchar(500),
    constraint reviews_pk_2
        unique (idAnime, idUser)
);


