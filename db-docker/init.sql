create table users(
    id          bigserial primary key,
    name        varchar(100) not null,
    email       varchar(100) not null
);

create table items(
    id          bigserial primary key,
    name        varchar(100) not null,
    cost        integer
);

create table orders(
    id          bigserial primary key,
    user_id     bigint references users (id)
);

create table order_items (
    id          bigserial primary key,
    item_id     bigint references items(id),
    order_id    bigint references orders(id)
);

insert into users(name, email) values
('some_user', 'voyzvz@gmail.com'),
('admin', 'admin@gmail.com');

insert into items(name, cost) values
('item1', 100),
('item2', 2500),
('item3', 500),
('item4', 13500),
('empty_item', 0);

insert into orders(user_id) values
(1),
(2),
(2);

insert into order_items(order_id, item_id) values
(1, 1),
(2, 2),
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(2, 3),
(3, 4),
(3, 5);