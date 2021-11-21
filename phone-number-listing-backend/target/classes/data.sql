insert into country (name, phone_number_code, regex) values ('Cameron', '+237', '\(237\)\?[2368]\d{7,8}');
insert into country (name, phone_number_code, regex) values ('Ethiopia', '+251', '\(251\)\?[1-59]\d{8}');
insert into customer (name, phone, valid_phone, country_id) values
 ('Omar', '+2372362323', '1', (select id from country where name = 'Cameron'));
insert into customer (name, phone, valid_phone, country_id) values
 ('Amr', '+25123623234', '1', (select id from country where name = 'Ethiopia'));



