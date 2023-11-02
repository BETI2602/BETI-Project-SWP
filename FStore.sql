create database FStore;

create table [User](
userID int primary key IDENTITY(1,1) not null,
userEmail nvarchar(50) not null,
userPassword nvarchar(50) not null,
userName nvarchar(100),
userDayOfBirth date,
userPhone nvarchar(10),
userAddress nvarchar(500)
);

create table [Admin](
adminID int primary key IDENTITY(1,1) not null,
adminEmail nvarchar(50) not null,
adminPassword nvarchar(50) not null,
adminName nvarchar(100),
adminDayOfBirth date,
adminPhone nvarchar(10),
adminAddress nvarchar(500)
);

create table Staff (
staffID int primary key IDENTITY(1,1) not null,
staffEmail nvarchar(50) not null,
staffPassword nvarchar(50) not null,
staffName nvarchar(100),
staffDayOfBirth date,
staffPhone nvarchar(10),
staffAddress nvarchar(500)
);


create table Product(
productID int primary key IDENTITY(1,1) not null,
productName nvarchar(500),
productPrice money,
productQuantity int,
productImg nvarchar(500),
productCategory nvarchar(200),
productDis nvarchar(500)
);

create table Cart(
cartID int primary key IDENTITY(1,1) not null,
userID int foreign key references [User](userID) not null,
productID int foreign key references [Product](productID) not null,
size nvarchar(3),
quantity int
);

create table [Order](
orderID int primary key IDENTITY(1,1) not null,
productID int foreign key references [Product](productID) not null,
userID int foreign key references [User](userID) not null,
cartID int foreign key references [Cart](cartID) not null,
totalPrice money,
purchaseDate date
);

create table ProductUser(
userID int foreign key references [User](userID) not null,
productID int foreign key references [Product](productID) not null
);



INSERT INTO [FStore].[dbo].[User] ( userEmail, userPassword, userName, userDayOfBirth, userPhone, userAddress)
VALUES ( 'user@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Dat Nguyen', '2003-03-12', '0312697274', 'An Giang');

INSERT INTO [FStore].[dbo].[Staff] ( staffEmail, staffPassword, staffName, staffDayOfBirth, staffPhone, staffAddress)
VALUES ( 'staff@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Dat Nguyen', '2003-03-12', '0312697274', 'An Giang');

INSERT INTO [FStore].[dbo].[Admin] ( adminEmail, adminPassword, adminName, adminDayOfBirth, adminPhone, adminAddress)
VALUES ( 'admin@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', 'Dat Nguyen', '2003-03-12', '0312697274', 'An Giang');

-- Chèn dữ liệu cho sản phẩm vào bảng Product
INSERT INTO Product (productName, productPrice, productQuantity, productImg, productCategory, productDis)
VALUES (N'Sản phẩm 1', 200000, 100, 'img/product/hoodiefognau.png', N'Áo', N'Mô tả sản phẩm 1'),
       (N'Sản phẩm 2', 250000, 75, 'img/product/hoodienikeden.png', N'Áo', N'Mô tả sản phẩm 2'),
       (N'Sản phẩm 3', 350000, 50, 'img/product/hoodienikedodo.png', N'Áo', N'Mô tả sản phẩm 3'),
       (N'Sản phẩm 4', 500000, 120, 'img/product/shortnikexam.png', N'Quần', N'Mô tả sản phẩm 4'),
	   (N'Sản phẩm 5', 450000, 30, 'img/product/joggernikeden.png', N'Quần', N'Mô tả sản phẩm 5'),
	   (N'Sản phẩm 6', 300000, 70, 'img/product/shortne.png', N'Quần', N'Mô tả sản phẩm 6'),
	   (N'Sản phẩm 7', 400000, 60, 'img/product/swtmlbmonob.png', N'Áo', N'Mô tả sản phẩm 7'),
	   (N'Sản phẩm 8', 750000, 50, 'img/product/polonenau.png', N'Áo', N'Mô tả sản phẩm 8'),
	   (N'Sản phẩm 9', 600000, 60, 'img/product/shortfog.png', N'Quần', N'Mô tả sản phẩm 9'),
	   (N'Sản phẩm 10', 950000, 50, 'img/product/polotrangbasic.png', N'Áo', N'Mô tả sản phẩm 10');


