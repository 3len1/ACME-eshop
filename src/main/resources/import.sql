insert into PRODUCT_CATEGORIES(ID, CATEGORY_NAME) values (101, 'Bombs'), (102, 'Pistols'), (103, 'Knife');

insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (101, 'B001', 50.00, '/img/products/B001', 'A bomb with a clock so it explode on time', 5, 3, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (102, 'B002', 30.00, '/img/products/B002', 'Massive attack', 10, 1, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (103, 'B003', 70.00, '/img/products/B003', 'A massive bomb in you hand', 6, 2, 101);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (104, 'P001', 30.00, '/img/products/P001', 'Like a smocking gun', 6, 2, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (105, 'P002', 5.00, '/img/products/P002', '#SummerTimeFun', 15, 0, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (106, 'P003', 25.00, '/img/products/P003', 'Let the fight begin', 15, 0, 102);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (107, 'K001', 55.00, '/img/products/K001', 'Like a ninja', 10, 0, 103);
insert into PRODUCTS(ID, PRODUCT_CODE, PRICE, IMG_URL, DESCRIPTION, STOCK, PURCHASED, CATEGORY_ID) values (108, 'K002', 20.00, '/img/products/K002', 'Like a Rambo', 17, 0, 103);

insert into ADDRESS(ID, POSTAL_CODE, TOWN, STREET) values (101, '12133', 'Peristeri', 'Parodos Filippiados 23'),(102, '12351', 'Agia Varvara', 'Plati P 12'), (103, '12351', 'Agia Varvara', 'Plati P 14');
insert into USERS(ID, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, GENDER, PHONE, BIRTHDAY, IS_ADMIN, ADDRESS_ID) values (101, 'vamphelp1@yahoo.com', '6a5cdc76f2ba218514a06be7edd3deaf8a70c2601b814e4e417ec2f1c9052951', 'ACME', 'admin', 'MALE', '6953580942', '1990-05-11 00:00:01.000000' , true, 101),(102, 'vamphelp2@yahoo.com', 'd83260049c817ff82e69e27382c0573c4306d5dfae935990aafa351ab250dd07', 'LooneyToons', 'RoadRunner', 'MALE', '6978450687', '1985-12-05 00:00:01.000000', false, 102), (103, 'vamphelp3@yahoo.com', '1356989cbbeeb30066ca834ee90c55b8b9ef628bf6fddb04e530e623d4bde8ab', 'LooneyToons', 'Koyot', 'MALE' , '2105446786', '1976-05-11 00:00:01.000000' , false, 103);
insert into CART(ID, USER_ID ) values (101, 101),(102,102),(103,103);

insert into ORDERS(ID, ORDER_CODE, PAYMENT_METH, PAYMENT_DATE, TOTAL_PRICE, COMMENTS, CANCELED, USER_ID) values (101, 'ORD-000001', 'CASH', '2018-01-02', 130.00, null, false, 102), (102, 'ORD-000030', 'CASH', '2018-05-14', 75.00, null, false, 102), (103, 'ORD-000002', 'CASH', '2018-01-03', 220.00, 'Deliver between 09:00 - 12:00', false, 103);

insert into ORDER_ITEMS(ID, PRICE, AMOUNT, PRODUCT_ID, ORDER_ID) values (101, 100, 2, 101, 101), (102, 30, 1, 104, 101), (103,20,1,101,102),(104,55,1,103,102),(105,50,2,101,103),(106,70,1,102,103),(107,90,3,103,103),(108,10,2,104,103);