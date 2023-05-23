create table metalor.client_phone_number
(
    client_name  varchar not null
        constraint client_phone_number_pk primary key,
    phone_number varchar not null
);
