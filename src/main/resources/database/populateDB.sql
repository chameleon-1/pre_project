-- INSERT INTO clients (full_name, birthday, phone, message_send)
-- VALUES ('Ivan Ivanov', '2007-09-03', '7834', 'false'),
--        ('Petr Petrov', '1989-04-17', '8377', 'false'),
--        ('Zaur Tregulov', '1999-01-27', '2763', 'false'),
--        ('Artem Lebedev', '2002-05-10', '1947', 'false');

insert into ClientsINFO (name, middlename, surname, birthday, phone, age)
values ('ivan', 'ivanovich', 'ivanov', '1989-04-17', '7834', '20'),
       ('petr', 'petrovich', 'petrov', '2007-09-03', '4287', '15'),
       ('zaur', 'zaurovich', 'zaurov', '1995-11-15', '1577', '10');
--        ('anton', 'antonovich', 'antonov','2000-02-25','1987','15')

-- переделать поле "age"
-- почему при добавлении антона все крашится?