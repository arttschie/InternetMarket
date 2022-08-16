CREATE SEQUENCE "public".product_category_id_seq START WITH 50 INCREMENT BY 1;

CREATE SEQUENCE "public".cart_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".locale_id_seq START WITH 5 INCREMENT BY 1;

CREATE SEQUENCE "public".order_detail_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".order_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".product_category_locale_id_seq START WITH 100 INCREMENT BY 1;

CREATE SEQUENCE "public".product_id_seq START WITH 100 INCREMENT BY 1;

CREATE SEQUENCE "public".status_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".status_locale_id_seq START WITH 50 INCREMENT BY 1;

CREATE SEQUENCE "public".user_id_seq START WITH 10 INCREMENT BY 1;

CREATE  TABLE "public".locale ( 
	id                   bigint DEFAULT nextval('locale_id_seq'::regclass) NOT NULL  ,
	short_name           varchar(32)  NOT NULL  ,
	name                 varchar(100)    ,
	CONSTRAINT locale_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".product_category ( 
	id                   bigint  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT product_category_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".product_category_locale ( 
	id                   bigint DEFAULT nextval('product_category_locale_id_seq'::regclass) NOT NULL  ,
	locale_id            integer  NOT NULL  ,
	product_category_id  integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	CONSTRAINT product_category_locale_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".status ( 
	id                   bigint DEFAULT nextval('status_id_seq'::regclass) NOT NULL  ,
	name                 varchar(100)    ,
	CONSTRAINT status_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".status_locale ( 
	id                   bigint DEFAULT nextval('status_locale_id_seq'::regclass) NOT NULL  ,
	locale_id            integer  NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	status_id            integer  NOT NULL  ,
	CONSTRAINT status_locale_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public"."user" ( 
	id                   bigint DEFAULT nextval('user_id_seq'::regclass) NOT NULL  ,
	first_name           varchar(100)    ,
	last_name            varchar(100)    ,
	birthday             date    ,
	phone_number         varchar(100)    ,
	address              varchar(100)    ,
	"password"           varchar(100)  NOT NULL  ,
	is_admin             boolean  NOT NULL  ,
	status_id            integer DEFAULT 1 NOT NULL  ,
	username             varchar(100)  NOT NULL  ,
	CONSTRAINT user_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public"."order" ( 
	id                   bigint DEFAULT nextval('order_id_seq'::regclass) NOT NULL  ,
	user_id              integer  NOT NULL  ,
	status_id            integer DEFAULT 1 NOT NULL  ,
	total_cost           numeric(10,2)    ,
	date_start           date    ,
	date_finish          date    ,
	CONSTRAINT order_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".product ( 
	id                   bigint DEFAULT nextval('product_id_seq'::regclass) NOT NULL  ,
	name                 varchar(100)  NOT NULL  ,
	description          text    ,
	cost                 numeric(10,2)  NOT NULL  ,
	"count"              integer    ,
	product_category_id  integer  NOT NULL  ,
	image_url            varchar(100)    ,
	CONSTRAINT product_pkey PRIMARY KEY ( id )
 );

CREATE  TABLE "public".cart ( 
	id                   bigint DEFAULT nextval('cart_id_seq'::regclass) NOT NULL  ,
	user_id              integer  NOT NULL  ,
	product_id           integer  NOT NULL  ,
	"count"              integer    ,
	CONSTRAINT pk_cart PRIMARY KEY ( id )
 );

CREATE  TABLE "public".order_detail ( 
	id                   bigint DEFAULT nextval('order_detail_id_seq'::regclass) NOT NULL  ,
	order_id             integer  NOT NULL  ,
	product_id           integer  NOT NULL  ,
	"count"              integer    ,
	cost                 numeric(10,2)    ,
	CONSTRAINT order_detail_pkey PRIMARY KEY ( id )
 );

ALTER TABLE "public".cart ADD CONSTRAINT cart_product FOREIGN KEY ( product_id ) REFERENCES "public".product( id );

ALTER TABLE "public".cart ADD CONSTRAINT cart_user FOREIGN KEY ( user_id ) REFERENCES "public"."user"( id );

ALTER TABLE "public"."order" ADD CONSTRAINT order_status FOREIGN KEY ( status_id ) REFERENCES "public".status( id );

ALTER TABLE "public"."order" ADD CONSTRAINT order_user_id_fkey FOREIGN KEY ( user_id ) REFERENCES "public"."user"( id );

ALTER TABLE "public".order_detail ADD CONSTRAINT order_detail_order FOREIGN KEY ( order_id ) REFERENCES "public"."order"( id );

ALTER TABLE "public".order_detail ADD CONSTRAINT order_detail_product FOREIGN KEY ( product_id ) REFERENCES "public".product( id );

ALTER TABLE "public".product ADD CONSTRAINT fk_product_product_category FOREIGN KEY ( product_category_id ) REFERENCES "public".product_category( id );

ALTER TABLE "public".product_category_locale ADD CONSTRAINT product_category_locale_locale_id_fkey FOREIGN KEY ( locale_id ) REFERENCES "public".locale( id );

ALTER TABLE "public".product_category_locale ADD CONSTRAINT product_category_locale_product_category_id_fkey FOREIGN KEY ( product_category_id ) REFERENCES "public".product_category( id );

ALTER TABLE "public".status_locale ADD CONSTRAINT status_locale_locale_id_fkey FOREIGN KEY ( locale_id ) REFERENCES "public".locale( id );

ALTER TABLE "public".status_locale ADD CONSTRAINT status_locale FOREIGN KEY ( status_id ) REFERENCES "public".status( id );

ALTER TABLE "public"."user" ADD CONSTRAINT fk_user_status FOREIGN KEY ( status_id ) REFERENCES "public".status( id );

INSERT INTO "public".locale( id, short_name, name ) VALUES ( 1, 'ru', 'russian');
INSERT INTO "public".locale( id, short_name, name ) VALUES ( 2, 'en', 'english');
INSERT INTO "public".product_category( id, name ) VALUES ( 1, 'fruits');
INSERT INTO "public".product_category( id, name ) VALUES ( 2, 'vegetables');
INSERT INTO "public".product_category( id, name ) VALUES ( 3, 'milky_products');
INSERT INTO "public".product_category( id, name ) VALUES ( 4, 'eggs');
INSERT INTO "public".product_category( id, name ) VALUES ( 5, 'meat_and_poultree');
INSERT INTO "public".product_category( id, name ) VALUES ( 6, 'grocery');
INSERT INTO "public".product_category( id, name ) VALUES ( 7, 'water_and_drinks');
INSERT INTO "public".product_category( id, name ) VALUES ( 8, 'sausages');
INSERT INTO "public".product_category( id, name ) VALUES ( 9, 'tea_coffee');
INSERT INTO "public".product_category( id, name ) VALUES ( 10, 'sweets');
INSERT INTO "public".product_category( id, name ) VALUES ( 11, 'bread');
INSERT INTO "public".product_category( id, name ) VALUES ( 12, 'snacks');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 1, 1, 1, 'Фрукты');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 2, 2, 1, 'Fruits');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 3, 1, 2, 'Овощи');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 4, 2, 2, 'Vegetables');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 5, 1, 3, 'Молочные изделия');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 6, 2, 3, 'Milky products');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 7, 1, 4, 'Яйца');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 8, 2, 4, 'Eggs');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 9, 1, 5, 'Мясо, птица');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 10, 2, 5, 'Meat, poultree');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 11, 1, 6, 'Бакалея');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 12, 2, 6, 'Grocery');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 13, 1, 7, 'Вода, соки, напитки');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 14, 2, 7, 'Water, juice, drinks');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 15, 1, 8, 'Колбасы, сосиски');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 16, 2, 8, 'Sausages');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 17, 1, 9, 'Чай и кофе');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 18, 2, 9, 'Tea and coffee');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 19, 1, 10, 'Сладости');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 20, 2, 10, 'Sweets');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 21, 1, 11, 'Хлеб');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 22, 1, 12, 'Снэки');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 23, 2, 12, 'Snacks');
INSERT INTO "public".product_category_locale( id, locale_id, product_category_id, name ) VALUES ( 24, 2, 11, 'Bread');
INSERT INTO "public".status( id, name ) VALUES ( 1, 'active');
INSERT INTO "public".status( id, name ) VALUES ( 2, 'inactive');
INSERT INTO "public".status( id, name ) VALUES ( 3, 'Processed');
INSERT INTO "public".status( id, name ) VALUES ( 4, 'On the road');
INSERT INTO "public".status( id, name ) VALUES ( 5, 'Delivered');
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 1, 1, 'активен', 1);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 2, 1, 'не активен', 2);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 3, 2, 'active', 1);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 4, 2, 'inactive', 2);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 5, 1, 'Оформлен', 3);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 6, 2, 'Processed', 3);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 7, 1, 'В пути', 4);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 8, 2, 'On the road', 4);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 9, 1, 'Доставлен', 5);
INSERT INTO "public".status_locale( id, locale_id, name, status_id ) VALUES ( 10, 2, 'Delivered', 5);
INSERT INTO "public"."user"( id, first_name, last_name, birthday, phone_number, address, "password", is_admin, status_id, username ) VALUES ( 1, 'admin', 'admin', '1998-06-28', '87072422806', 'Караганда', '295ce6711606eaea5a2e8f0c4703e7b7', true, 1, 'admin');
INSERT INTO "public"."user"( id, first_name, last_name, birthday, phone_number, address, "password", is_admin, status_id, username ) VALUES ( 2, 'user', 'user', '1978-11-11', '+7 (999) 966-28-06', 'Москва', 'c3154c5ade255b20220f88685620dc7e', false, 1, 'user');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 63, 'Мороженое пломбир 110г', 'с вафельным рожком', 300, 750, 10, 'icecream_plombir.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 64, 'Молочный шоколад 80г', 'обычный молочный шоколад', 300, 1000, 10, 'icecream_chocolate.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 68, 'Хлеб пшеничный', 'обычный хлеб', 130, 1000, 11, 'bread.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 1, 'Бананы', 'обычные бананы', 480, 99961, 1, 'banana.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 33, 'Спагетти', '500г', 360, 89, 6, 'spagetti.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 69, 'Хлеб ржаной', 'обыный ржаной хлеб', 180, 589, 11, 'bread_rye.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 52, 'Колбаса сервелат', '500г', 1300, 239, 8, 'sausage_servelat.png');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 2, 'Лимон', 'обычный лимон', 800, 49988, 1, 'limon.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 72, 'Чипсы 80г', 'чипсы картофельные', 360, 749, 12, 'chips.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 25, 'Яйцо перепелиное', 'перепилиное ячйцо, 8шт', 400, 177, 4, 'small_egg.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 9, 'Морковь', 'морковь', 180, 100000, 2, 'carrot.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 15, 'Молоко 2.5%', 'обычное молоко, жирность 2.5%', 380, 200, 3, 'milk2,5.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 24, 'Яйцо куриное', 'обычное куриное ячйцо, 10шт', 500, 200, 4, 'egg.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 77, 'Печенье', 'топленое молоко', 500, 500, 10, 'coockies.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 76, 'Огурцы', 'цена за 1кг', 1000, 0, 2, 'cucumber.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 26, 'Свинина', 'мякоть шеи', 2000, 100000, 5, 'meat_pig.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 27, 'Говядина', 'мякоть лопатки', 3500, 100000, 5, 'meat_cow.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 35, 'Рис для плова', '800г', 600, 200, 6, 'rice.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 39, 'Вода негазированная 0.5', 'обычная вода', 100, 1000, 7, 'water.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 40, 'Вода газированная 0.5', 'обычная газированная вода', 90, 500, 7, 'water_sparky.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 53, 'Колбаса докторская', '500г', 1000, 250, 8, 'sausage_boiled.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 57, 'Чай черный пакетированный 100шт', 'цейлонский чай', 1000, 750, 9, 'tea_black.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 58, 'Чай зеленый пакетированный 100шт', 'обычный зеленый чай', 1250, 750, 9, 'tea_green.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 73, 'Попкорн 100г', 'попкорн с карамелью', 400, 500, 12, 'popcorn.jpg');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 16, 'Молоко 3.2%', 'обычное молоко, жирность 3.2%', 400, 178, 3, 'milk3,2.png');
INSERT INTO "public".product( id, name, description, cost, "count", product_category_id, image_url ) VALUES ( 8, 'Лук репчатый', 'лук репчатый', 300, 99978, 2, 'onion.jpg');
