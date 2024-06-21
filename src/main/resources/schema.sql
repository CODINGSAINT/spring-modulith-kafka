create table if not exists orders (
    id serial not null primary key
);
CREATE table if not  exists order_line_items(
  id serial not null primary key,
       quantity int not null,
        product int not null,
       orders int  references orders(id)
);