drop database factory;
create database factory;
use factory;

-- Table: Users
CREATE TABLE Users (
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    U_password VARCHAR(255) NOT NULL,
    addrees VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    U_role VARCHAR(255) NOT NULL,
    favoriteColor VARCHAR(255) NOT NULL,
    budget double DEFAULT 0.0

);

CREATE TABLE Supplier (
    id int PRIMARY KEY auto_increment,
    Sname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Table: Employee
CREATE TABLE Employee (
    id INT PRIMARY KEY auto_increment,
	empName VARCHAR(255) NOT NULL,
    contact_info VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    salary double NOT NULL,
    E_role VARCHAR(255) NOT NULL
);

-- Table: Material
CREATE TABLE Material (
    id INT PRIMARY KEY auto_increment,
    Mname VARCHAR(255) NOT NULL,
    price double NOT NULL,
    quantity INT NOT NULL,
	murl VARCHAR(255) NOT NULL
);

CREATE TABLE Supplier_Order (
    id INT PRIMARY KEY auto_increment,
    supplier_id INT,
	material_id INT,
	employee_id INT,
	quantity int default 0 not null,
    FOREIGN KEY (employee_id) REFERENCES Employee(id),
    FOREIGN KEY (supplier_id) REFERENCES Supplier(id),
	FOREIGN KEY (material_id) REFERENCES Material(id)
);


-- Table: Machines
CREATE TABLE Machines (
    id INT PRIMARY KEY auto_increment,
    production VARCHAR(255) NOT NULL,
    rate DECIMAL(10, 2) NOT NULL,
    Mtype VARCHAR(255) NOT NULL
);

-- Table: machine_Requires_material
CREATE TABLE machine_Requires_material (
    machine_id INT NOT NULL,
    material_id INT NOT NULL,
    PRIMARY KEY (machine_id, material_id),
    FOREIGN KEY (machine_id) REFERENCES Machines(id),
    FOREIGN KEY (material_id) REFERENCES Material(id)
);

-- Table: Warehouse
CREATE TABLE Warehouse (
    id INT PRIMARY KEY auto_increment,
    address VARCHAR(255) NOT NULL,
    capacity INT NOT NULL
);

-- Table: Products
CREATE TABLE Products (
    id INT PRIMARY KEY auto_increment,
    Pname VARCHAR(255) NOT NULL,
    Productioncost Double NOT NULL,
	Sellingcost Double(10, 2) NOT NULL,
    warehouse_limit INT ,
    imgUrl varchar(1000) not Null,
    quantity int  default 0,
	materialRate Double NOT NULL,
    Warehouse_id INT,
	FOREIGN KEY (Warehouse_id) REFERENCES Warehouse(id)
);

-- Table: Produce
CREATE TABLE Produce (
    machine_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (machine_id, product_id),
    FOREIGN KEY (machine_id) REFERENCES Machines(id),
    FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Table: Customers
CREATE TABLE Customers (
    id INT PRIMARY KEY auto_increment,
    Cname VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    Ctype VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    budget double DEFAULT 0.0
    
);

-- Table: Orders
CREATE TABLE F_Order (
    Onumber INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    Odate DATE,
    price DOUBLE,
    customer_id INT,
    taken BOOLEAN DEFAULT false,
    FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

-- Table: Order_Line
CREATE TABLE Order_Line (
	 id INT PRIMARY KEY auto_increment,
     order_id INT NOT NULL,
     product_id INT NOT NULL,
     quantity INT NOT NULL,
     FOREIGN KEY (order_id) REFERENCES F_Order(Onumber),
     FOREIGN KEY (product_id) REFERENCES Products(id)
);

-- Table: WarehouseTake
CREATE TABLE Takes (
    employee_id INT NOT NULL,
    order_id INT NOT NULL,
	warehouse_id INT NOT NULL,
    PRIMARY KEY (employee_id,order_id,warehouse_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(id),
    FOREIGN KEY (order_id) REFERENCES F_Order(Onumber),
	FOREIGN KEY (warehouse_id) REFERENCES Warehouse(id)

);

-- Table: Submit maaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa   هل هي 1-1 او m-m
CREATE TABLE Submit (
    customer_id INT NOT NULL,
    order_id INT NOT NULL,
    PRIMARY KEY (customer_id, order_id),
    FOREIGN KEY (customer_id) REFERENCES Customers(id),
    FOREIGN KEY (order_id) REFERENCES F_Order(Onumber)
);

-- Table: Delivers
CREATE TABLE Delivers (
    employee_id INT NOT NULL,
    order_id INT NOT NULL,
    Onumber INT NOT NULL,
    PRIMARY KEY (employee_id, order_id,Onumber),
    FOREIGN KEY (employee_id) REFERENCES Employee(id),
    FOREIGN KEY (Onumber) REFERENCES F_Order(Onumber)
);

CREATE TABLE Emp_History (
	 id INT PRIMARY KEY auto_increment,
     order_id INT,
     employee_id INT,
     FOREIGN KEY (order_id) REFERENCES F_Order(Onumber),
     FOREIGN KEY (employee_id) REFERENCES Employee(id)
);


select * from products;

-- Products
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Aluminume Tray', 30, 55, 100, 'img\\aluminumeTray.png', 6.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Aluminume foil', 1.2, 2.7, 100, 'img\\aluminum-foil.png', 1.5);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Big Aluminum foil', 3, 7, 100, 'img\\bigAluminumFoil.png', 4.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Big Soup plate', 2.5, 4.5, 100, 'img\\bigSoupPlate.png', 3.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Cup Cack Holder', 0.75, 2, 100, 'img\\Cupcake.png', 1.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Cup Holders', 2.5, 4, 100, 'img\\cupHolder.png', 1.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Deep Plates', 1, 3, 100, 'img\\deepPlates.png', 3.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Divides Plates', 2, 5, 100, 'img\\divided-plates.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Aluminume Tray', 1.5, 3, 100, 'img\\forks.png', 1.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Black Garbage Bages', 2, 5, 100, 'img\\garbageBags.png', 1.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('White Garbage Bags', 2, 5, 100, 'img\\Garbage-Bags.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Gift Bags', 0.5, 2, 100, 'img\\giftBag.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Gift Bags1', 0.5, 2, 100, 'img\\giftPag1.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Gloves', 1.5, 4.5, 100, 'img\\Gloves.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Oval Plates', 1.5, 3, 100, 'img\\Oval-plates.png', 3.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Plastic Buckets', 4, 17, 100, 'img\\plasticBucket.png', 1.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Large Plastic Cups', 0.8, 1.5, 100, 'img\\plastic-cups-.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Small Plastic Cups', 0.8, 1.5, 100, 'img\\plasticCupsSmall.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('plastic Juice', 1, 3, 100, 'img\\plasticJuice.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Plastic Mugs', 2.5, 7, 100, 'img\\plasticMug.png', 1.5);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Plastic Plate', 1, 3, 100, 'img\\plasticPlate.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Plastic Spoons', 0.8, 1.5, 100, 'img\\plastic-poons.png', 1.5);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Pop Corn Puckets', 1.5, 6, 100, 'img\\popCorn.png', 3.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Rectnagle Tray', 1.5, 3, 100, 'img\\rectangleTray.png', 2.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Soup Plates', 1, 3, 100, 'img\\soupPlates.png', 1.7);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Straws', 0.4, 1.99, 100, 'img\\straws.png', 0.5);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Stretch Food', 7, 18, 100, 'img\\stretchFood.png', 4.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Table Clothe', 2, 6, 100, 'img\\tableClothe.png', 1.37);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('White Stretch films', 4.5, 12, 100, 'img\\white-Stretch-films.png', 3.00);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Blue Stretch', 3.5, 10, 100, 'img\\blue-Stretch.png', 2.5);
INSERT INTO Products (Pname, Productioncost, Sellingcost, warehouse_limit, imgUrl, materialRate) VALUES ('Food wood', 0.5, 3, 100, 'img\\wood.png', 1.00);

-- Materials
INSERT INTO Material (Mname, price, quantity, murl) VALUES
('Polyethylene Terephthalate (PET)', 1.00, 1400, 'img\\Polyethylene Terephthalate-PET.png'),
('Polypropylene (PP)', 1.20, 1000, 'img\\Polypropylene-PP.png'),
('Plastic Recycle(PE-HD)', 0.90, 1500, 'img\\Plastic Recycle-PE-HD.png'),
('Polyvinyl Chloride (PVC)', 1.10, 1200, 'img\\Polyvinyl Chloride-PVC.png'),
('Polystyrene (PS)', 0.85, 1300, 'img\\Polystyrene-PS.png'),
('Plastic Recycle (PE-LD)', 1.50, 1100, 'img\\Plastic Recycle-PE-LD.png');



insert into supplier values(1,'abd','0599');
insert into supplier values(2,'anas','0599');
insert into supplier values(3,'ahmad','0599');



-- 00:57:57	INSERT INTO Emp_History  (order_id,employee_id) VALUES (1, 1)	Error Code: 1452. Cannot add or update a child row: a foreign key constraint fails (`factory`.`emp_history`, CONSTRAINT `emp_history_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `f_order` (`Onumber`))	0.000 sec