# Cafeteria Konecta

## Ejecución Local
Esta aplicación necesita de `Apache Netbeans IDE` y como gestor de base de base de datos MySQL a `XAMPP`, despúes de descargar y configurar por defecto estos dos programas, procedemos con los siguiente pasos:
1. Crear una base de datos en phpMyAdmin con el nombre de `bDCafeteria` y despúes importar la base de datos que se encuentra en la ruta `librerias_usadas/bdcafeteria.sql`, esto con el fin de tener una base de datos de prueba.
2. Verificar que la base de datos este corriendo desde el `localhost:3306` para poder conectar adecuamente.
3. Clonar el proyecto y abrirlo con Netbeans, este proceso puede demorar un momento, ya que el IDE realiza unas compilaciones iniciales.
4. Las librerias usadas para el proyecto, ya se encuentran importadas en el mismo, de caso contrario y de tener incovenientes con estas importarlas como JAR.
5. Las librerías son:
 * mysql-connector-java-8.0.26.jar
 * jcommon-1.0.23.jar
 * jfreechar-1.0.19.jar
 * postgresql-42.6.0.jar
Estas fueron adjuntas en la carpeta "librerias_usadas"
5. En el archivo `conexion.java` se deve verificar las variables:
- Class.fornmae (linea 22) = "com.mysql.cj.jdbc.Driver"
- jdbcUrl (Linea 23) = "jdbc:mysql://localhost:3306/bDCafeteria","root",""
6. Por último para ejecutar la aplicación, compilamos el proyecto con: Clic derecho en el proyecto, "Clean and Build". Esto generará en la carpeta "dist" un ejecutable de Java: `Cafeteria_Konecta.jar`, el cual se ejecuta para iniciar nuestra aplicación

## Ejecución experimento AWS
Para la siguiente versión del programa, se utilizo la base de datos en RDS de AWS, para esto se debe:
1. Se debe cambiar la clase del apuntador en el archivo `conexion.java`
- Class.fornmae (linea 22) = "org.postgresql.Driver"
- jdbcUrl (Linea 23) = "jdbc:driver://hostname:port/dbName?user=userName&password=password"
2. Se debe crear la tabla de productos en la base de datos de RDS con:
```
CREATE TABLE IF NOT EXISTS public.tb_producto
(
    id_producto SERIAL PRIMARY KEY,
	Nombre varchar NOT NULL,
	Referencia varchar NOT NULL,
	Precio real NOT NULL,
	Peso integer NOT NULL,
	Categoria varchar NOT NULL,
	Inventario integer NOT NULL,
	Fecha_creacion varchar NOT NULL,
	Dias_Inventario integer NOT NULL,
	Cantidad_Ventas integer NOT NULL DEFAULT 0
);
```
3. Para ejecutar la aplicación, compilamos el proyecto con: Clic derecho en el proyecto, "Clean and Build". Esto generará en la carpeta "dist" un ejecutable de Java: `Cafeteria_Konecta.jar`, el cual se ejecuta para iniciar nuestra aplicación.
4. La API de la aplicación que se encarga de recibir las solicitudes del Frontend esta alojada en AWS Beanstalk: `http://cafeteriaapi-env.eba-uqgktuci.us-east-1.elasticbeanstalk.com/`
5. El Frontend de la aplicación se encuntra alojada en AWS S3: `http://smartsellweb.s3-website-us-east-1.amazonaws.com/`