create table if not exists ClientsINFO
(
    id         SERIAL PRIMARY KEY,
    name       varchar(255),
    middlename varchar(255),
    surname    varchar(255),
    age        int2,
    birthday   Date,
    phone      varchar(255)

);
CREATE TABLE IF NOT EXISTS Clients
(
    id           SERIAL PRIMARY KEY,
    full_name    VARCHAR(255),
    birthday     DATE,
    phone        VARCHAR(255),
    message_send BOOLEAN,
    FOREIGN KEY (id) REFERENCES ClientsINFO (Id)
);