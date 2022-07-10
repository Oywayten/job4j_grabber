create table company
(
    id integer not null,
    name character varying,
    constraint company_pkey primary key (id)
);

insert into company (id, name) values (1, 'Google');
insert into company (id, name) values (2, 'Yandex');
insert into company (id, name) values (3, 'Yahoo');
insert into company (id, name) values (4, 'Bing');
insert into company (id, name) values (5, 'DuckDuckGo');

select * from company;

create table person
(
    id integer not null,
    name character varying,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);

insert into person (id, name, company_id)
values
(1, 'Petr', 1),
(2, 'Ivan', 2),
(3, 'Stas', 3),
(4, 'Alex', 3),
(5, 'Vik', 4),
(6, 'Bill', 4),
(7, 'Tom', 5);
select * from person;
delete from person;

/*В одном запросе получаем
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.*/
with p as (
    select name, company_id
    from person
    where company_id != 5
)
select p.name person, c.name company
from p
join company c on p.company_id = c.id ;

/*название компании с максимальным количеством человек + количество человек в этой компании
(нужно учесть, что таких компаний может быть несколько).*/
-- 1 вариант полный
select * from (
    select count(*) cnt, c.name
    from person p
    join company c
    on p.company_id = c.id
    group by c.name
) as sub
where cnt = (
    select max(cnt)
    from (
        select count(*) cnt, c.name
        from person p
        join company c
        on p.company_id = c.id
        group by c.name
    ) as sub2
);

-- 2 вариант с with
with sub as (
    select count(*) cnt, c.name
    from person p
    join company c
    on p.company_id = c.id
    group by c.name
)
select * from sub as sub
where cnt = (
    select max(cnt)
    from sub
);