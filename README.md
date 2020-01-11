# everisTransbankTest
Ejercicio de Restaurant para Transbank (Everis)

### Proposito
Exponer endpoint de login, el cual debe aceptar un nombre y usuario y contraseña, las cuales deben ser almacenadas de manera segura. 

Luego de estar logueado en el punto 1, debe existir una api para crear ventas y otra que devuelva el listado de ventas del día

### Arquitectura Implementada en la solución.

- Springboot como servidor de aplicaciones.
- Base de Datos MySQL
- Se implementa solución basada en Security Service de Spring y Java para el login y seguridad de endpoints.
- Encriptación para contraseñas BCrypt.
- Desarrollo de servicios con capas de componentes (Controlador, Servicio, Repositorio, Entidades etc.)

### Instrucciones

#### IDE

El IDE debe tener instalado Lombok y tener habilitado el procesador de anotaciones.

* [Lombok for Eclipse](https://howtodoinjava.com/automation/lombok-eclipse-installation-examples/)
* [Lombok for Intellij](https://www.baeldung.com/lombok-ide)

Importar dependiencias de Maven.

#### Base de Datos

Ejecutar el siguiente Script en MySQL server.

```sql
CREATE SCHEMA IF NOT EXISTS EverisTest;
USE EverisTest;

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
```

Se debe Editar el application.properties con los datos del servidor de MySQL instalado en la maquina, para fin de esta prueba se consideró que MySQL estuviese instalado y ejecutandose en el puerto por defecto. 
* localhost:3306
* Usuario: root
* Contraseña: 

### Autentificación

Las credenciales para aprobar la solicitud de credenciales del las URL expuestas, tanto en el formulario como autenticación Basica. Son:

- User:     rovamad
- Password: rovamad

### Endpoints

http://localhost:8080/login

Despliega formulario html para colocar credenciales de autentificación. A este recurso se debe acceder usando un navegador para hayarle sentido.

http://localhost:8080/api/sales

Haciendo uso del verbo GET se obtiene un listado de las ventas del día. El recurso acepta parametrizacion para hacer filtrado de dias pasados, agregandole la key date e instanciadola con una fecha el el formato dd-MM-yyyy ejemplo: `?date=10-01-2020` quedaría algo como: http://localhost:8080/api/sales?date=10-01-2020

Se puede acceder a este servicio tanto en Navegador como en POSTMAN. 

```json
[
    {
        "id": 2,
        "name": "Dummy2",
        "amount": 2,
        "subtotal": 200,
        "date": "10-01-2020"
    },
    {
        "id": 3,
        "name": "Dummy3",
        "amount": 5,
        "subtotal": 300,
        "date": "10-01-2020"
    }
]
```

Haciendo uso del verbo POST se crean ventas que se almacenan en la Base de Datos, para probar este servicio, se debe utilizar herramientas como POSTMAN y agregarle las credenciales e autentificacion como "Basic Auth", esta limitado a este a unica fuente de acceso ya que no existe un formulario/vista que utilice el servicio a nivel de explorador. 

El cuerpo del mensaje queda con un JSON de la siguiente manera:

```json
{
    "id": null,
    "name": "Dummy4",
    "amount": 5,
    "subtotal": 400,
    "date": "10-01-2020"
}
```

http://localhost:8080/logout

Cierra la sesion abierta por navegador.

### Prueba de Carga con JMS

http://localhost:8080/api/jms

Ejecuta prueba de carga con Cola JMS a diferentes consumidores.