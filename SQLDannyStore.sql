create database DannyStore
go

use DannyStore
go

create table Categories
(
ID [int] primary key,
[name] nvarchar(30) ,
)
go

insert into Categories values(1,'Adidas')
insert into Categories values(2,'Nike')
insert into Categories values(3,'Puma')
go

select * from Categories
drop table Products
create table Products
(
ID varchar(10) primary key,
[name] [nvarchar](max) NULL,
[describe] [nvarchar](max) NULL,
[quantity] [int] NULL,
[price] [money] NULL,
[image] [nvarchar](max) NULL,
[cid] [int] references Categories(ID),
)
go

create table Users
(
username varchar(20) primary key,
[name] [nvarchar](max) NULL,
[email] [nvarchar](max) NULL,
[Role] [int] NULL,
[cart] [nvarchar](max) NULL,
[balance] [float] NULL,
[password] [nvarchar](max) NULL,
)
go

drop table Products


insert into Users values('sa', 'Admin 1', 'khanggia85@gmail.com', 0, null, 0, '123')
insert into Users values('giakhang', 'Truong Nguyen Gia Khang', 'khanggia85@gmail.com', 1, null, 0, 'gk123')

select * from Users where username = 'sa' and password = '123'

select * from Products
insert into Products values('add1', 'ADDIDAS YEEZY SLATE BONE', 'Mens Original', 1, 6000000, './img/add1.jpg', 1)
insert into Products values('add2', 'ADDIDAS RESPONSE CL', 'Mens Original', 1, 3300000, './img/add2.jpg', 1)
insert into Products values('add3', 'ADDIDAS Humanrace Samba', 'Original', 1, 5000000, './img/add3.jpg', 1)
insert into Products values('add4', 'ADDIDAS STAN SMITH', 'Kids Original', 1, 1800000, './img/add4.jpg', 1)
insert into Products values('add5', 'ADDIDAS STAN SMITH BONEGA 2B', 'Mens Original', 1, 3300000, './img/add5.jpg', 1)
insert into Products values('add6', 'ADDIDAS SUPERSTAR XLG', 'Original', 1, 3000000, './img/add6.jpg', 1)
insert into Products values('add7', 'ADIDAS ORIGINALS X JAMES JARVIS 360', 'Kids Original', 1, 3300000, './img/add7.jpg', 1)
insert into Products values('add8', 'ULTRABOOST LIGHT', 'Womens Original', 1, 5200000, './img/add8.jpg', 1)
insert into Products values('add9', 'ULTRABOOST 1.0', 'Womens Original', 1, 4500000, './img/add9.jpg', 1)
insert into Products values('add10', 'ULTRA ADIDAS 4D', 'Womens Original', 1, 6000000, './img/add10.jpg', 1)

insert into Products values('ni1', 'Nike Air Force 1 0 7E', 'The radiance lives on in the Nike Air Force 1 07, the b-ball icon that puts a fresh spin on what you know best: crisp leather, bold colours and the perfect amount of flash to make you shine.', 1, 2929000, './img/ni1.jpg', 2)
insert into Products values('ni2', 'Nike Air Force 1 Mid 07', 'Mens Shoes', 1, 3519000, './img/ni2.jpg', 2)
insert into Products values('ni3', 'Nike Air Force 1 Shadow', 'Everything you love about the AF-1—but doubled! The Air Force 1 Shadow puts a playful twist on a hoops icon to highlight the best of AF-1 DNA. With 2 eyestays, 2 mudguards, 2 backtabs and 2 Swoosh logos, you get a layered look with double the branding.', 1, 3829000, './img/ni3.jpg', 2)
insert into Products values('ni4', 'Nike Air Force 1 LV8', 'Let the fun shine through with these playful kicks. The legendary AF-1 design and comfortable Air cushioning make this hoops original the perfect shoe for playing every day. Plus, this version features a Swoosh logo that changes colour when exposed to UV light for fun in the sun with every step.', 1, 2679000, './img/ni4.jpg', 2)
insert into Products values('ni5', 'Nike Air Force 1 Low By You', 'Custom Womens Shoes', 1, 3519000, './img/ni5.jpg', 2)
insert into Products values('ni6', 'Nike Air Force 1 Shadow', 'Make every step unique. These kicks put a playful twist on a hoops icon by doubling up on everything you love about the AF-1. Layered materials like linen-evoking textiles and synthetic leather pair with an exaggerated midsole and a pop of pastels to bring you double the style.', 1, 3829000, './img/ni6.jpg', 2)
insert into Products values('ni7', 'Nike Air Force 1 High By You', 'Mens Custom Shoes', 1, 4259000, './img/ni7.jpg', 2)
insert into Products values('ni8', 'Nike Air Force 1 Mid By You', 'Let your design shine in satin, keep it classic in canvas or get luxe with leather. No matter what you choose, these AF-1s are all about you. 12 colour choices and an additional Gum option for the sole mean your design is destined to be one of a kind, just like you.', 1, 3529000, './img/ni8.jpg', 2)
insert into Products values('ni9', 'Nike Air Force 1 LX United', 'Celebrate the moment in this b-ball original mixed with off-pitch style. Crossing hardwood comfort with off-court flair, it puts a fresh spin on what you know best: era-echoing, 80s construction and nothin-but-net style.', 1, 3519000, './img/ni9.jpg', 2)
insert into Products values('ni10', 'Nike Air Force 1 Next Nature','Add some legendary flair to your look with this Air Force 1 Next Nature. Durable synthetic leather and Nike Air cushioning give you timeless style and comfort, while special Swoosh logos feature colours that shift subtly as you move. These kicks will have you rocking nothing-but-net style from the classroom to the park and everywhere in between.', 1, 2549000, './img/ni10.jpg', 2)

insert into Products values('pu1', 'RS-XL Cassette Tape Mens Sneakers', 'Break out the stereos and dust off the tape decks because it’s time for a throwback.', 1, 3251000, './img/pu1.jpg', 3)
insert into Products values('pu2', 'PUMA x LAMELO BALL MB.02 Fade Mens Basketball Shoes', 'The second signature shoe. The follow up. The MB.02. This version is just as disruptive as the first', 1, 3251000, './img/pu2.jpg', 3)
insert into Products values('pu3', 'Suede Classic XXI Mens Sneakers', 'The Suede hit the scene in 1968 and has been changing the game ever since', 1, 1720000, './img/pu3.jpg', 3)
insert into Products values('pu4', 'FUTURE ULTIMATE FG/AG Mens Soccer Cleats', 'Calling all playmakers. Inspired by Neymar Jr’s style of play, the new FUTURE ULTIMATE is here ', 1, 6700000, './img/pu4.jpg', 3)
insert into Products values('pu5', 'Suede Classic XXI Mens Sneakers', 'The Suede hit the scene in 1968 and has been changing the game ever since.', 1, 3300000, './img/pu5.jpg', 3)

select * from Products where id = 'test2'

drop table Orders
create table Orders
(
OID varchar(10) primary key,
[username] [nvarchar](max) NULL,
[cart] [nvarchar](max) NULL,
[date] [nvarchar](max) NULL,
[totalPrice] [float] NULL,
)
go
select * from Orders

