insert into PRODUCT_CATEGORIES(ID, CATEGORY_NAME) values (1, 'Bombs'), (2, 'Pistols'), (3, 'Knife');

insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (1, 'B001', 50.00, '/img/products/B001', 'A bomb with a clock so it explode on time', 5, 0, 1);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (2, 'B002', 30.00, '/img/products/B002', 'Massive attack', 10, 0, 1);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (3, 'B003', 70.00, '/img/products/B003', 'A massive bomb in you hand', 6, 0, 1);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (4, 'P001', 30.00, '/img/products/P001', 'Like a smocking gun', 6, 0, 2);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (5, 'P002', 5.00, '/img/products/P002', '#SummerTimeFun', 15, 0, 2);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (6, 'P003', 25.00, '/img/products/P003', 'Let the fight begin', 15, 0, 2);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (7, 'K001', 55.00, '/img/products/K001', 'Like a ninja', 10, 0, 3);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (8, 'K002', 20.00, '/img/products/K002', 'Like a Rambo', 17, 0, 3);


insert into USERS(ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, GENDER, PHONE, BIRTHDAY, IS_ADMIN) values (1, 'vamphelp1@yahoo.com', 'vamphelp1', 'ACME', 'admin', 'MALE', '6953580942', '1990-05-11 00:00:01.000000' , true),(2, 'vamphelp2@yahoo.com', 'vamphelp2', 'LooneyToons', 'RoadRunner', 'MALE', '6978450687', '1985-12-05 00:00:01.000000', false), (3, 'vamphelp3@yahoo.com', 'vamphelp3', 'LooneyToons', 'Koyot', 'MALE' , '2105446786', '1976-05-11 00:00:01.000000' , false);

insert into ADDRESS(ID, POSTAL_CODE, TOWN, STREET, USER_ID) values (1, '12133', 'Peristeri', 'Parodos Filippiados 23', 1),(2, '12351', 'Agia Varvara', 'Plati P 12', 2), (3, '12351', 'Agia Varvara', 'Plati P 14', 3);