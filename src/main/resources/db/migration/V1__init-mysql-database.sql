 drop table if exists customer;

 create table customer (
       id varchar(36) not null,
        created_date datetime(6),
        customer_name varchar(255),
        last_modified_date datetime(6),
        version integer,
        primary key (id)
    ) engine=InnoDB;