insert into PRODUCT_CATEGORIES(ID, CATEGORY_NAME) values (101, 'Bombs'), (102, 'Pistols'), (103, 'Knife');

insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (101, 'B001', 50.00, '/img/products/B001', 'A bomb with a clock so it explode on time', 5, 0, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (102, 'B002', 30.00, '/img/products/B002', 'Massive attack', 10, 0, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (103, 'B003', 70.00, '/img/products/B003', 'A massive bomb in you hand', 6, 0, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (104, 'P001', 30.00, '/img/products/P001', 'Like a smocking gun', 6, 0, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (105, 'P002', 5.00, '/img/products/P002', '#SummerTimeFun', 15, 0, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (106, 'P003', 25.00, '/img/products/P003', 'Let the fight begin', 15, 0, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (107, 'K001', 55.00, '/img/products/K001', 'Like a ninja', 10, 0, 103);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (108, 'K002', 20.00, '/img/products/K002', 'Like a Rambo', 17, 0, 103);

insert into ADDRESS(ID, POSTAL_CODE, TOWN, STREET) values (101, '12133', 'Peristeri', 'Parodos Filippiados 23'),(102, '12351', 'Agia Varvara', 'Plati P 12'), (103, '12351', 'Agia Varvara', 'Plati P 14');
insert into USERS(ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, GENDER, PHONE, BIRTHDAY, IS_ADMIN, ADDRESS_ID) values (101, 'vamphelp1@yahoo.com', 'vamphelp1', 'ACME', 'admin', 'MALE', '6953580942', '1990-05-11 00:00:01.000000' , true, 101),(102, 'vamphelp2@yahoo.com', 'vamphelp2', 'LooneyToons', 'RoadRunner', 'MALE', '6978450687', '1985-12-05 00:00:01.000000', false, 102), (103, 'vamphelp3@yahoo.com', 'vamphelp3', 'LooneyToons', 'Koyot', 'MALE' , '2105446786', '1976-05-11 00:00:01.000000' , false, 103);

