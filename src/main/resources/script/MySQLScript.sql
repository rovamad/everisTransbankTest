DROP TABLE IF EXISTS auth_user_role;
DROP TABLE IF EXISTS auth_role;
DROP TABLE IF EXISTS auth_user;
DROP TABLE IF EXISTS sale;

CREATE TABLE auth_role (
  auth_role_id int(11) NOT NULL AUTO_INCREMENT,
  role_name varchar(255) DEFAULT NULL,
  role_desc varchar(255) DEFAULT NULL,
  PRIMARY KEY (auth_role_id)
);
INSERT INTO auth_role VALUES (1,'USER','Rol de usuario del sistema');

CREATE TABLE auth_user (
  auth_user_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  user varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (auth_user_id)
);

CREATE TABLE auth_user_role (
  auth_user_id int(11) NOT NULL,
  auth_role_id int(11) NOT NULL,
  PRIMARY KEY (auth_user_id,auth_role_id),
  KEY FK_user_role (auth_role_id),
  CONSTRAINT FK_auth_user FOREIGN KEY (auth_user_id) REFERENCES auth_user (auth_user_id),
  CONSTRAINT FK_auth_user_role FOREIGN KEY (auth_role_id) REFERENCES auth_role (auth_role_id)
) ;

insert into auth_user (auth_user_id,first_name,last_name,user,password) values (1,'Marcos','Rojas','rovamad','$2a$10$2GlFCCDP7CxXhsZGRjBlueonORlHKaRV7GXeksWxIG7e.NhY.HNkq');
insert into auth_user_role (auth_user_id, auth_role_id) values ('1','1');

CREATE TABLE sale (
  sale_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  amount int NOT NULL,
  subtotal float NOT NULL,
  date varchar(10) NOT NULL,
  PRIMARY KEY (sale_id)
);

insert into sale (name,amount,subtotal,date) values ('Dummy1',1,100,'09-01-2020');
insert into sale (name,amount,subtotal,date) values ('Dummy2',2,200,'10-01-2020');