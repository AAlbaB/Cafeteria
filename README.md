# Cafeteria Konecta
Pasos para ejecución de programa localmente

Esta aplicación necesita de `Apache Netbeans IDE` y como gestor de base de base de datos MySQL a `XAMPP`, despúes de descargar y configurar por defecto estos dos programas, procedemos con los siguiente pasos:
1. Crear una base de datos en phpMyAdmin con el nombre de `bDCafeteria` y despúes importar la base de datos que se encuentra en la ruta `librerias_usadas/bdcafeteria.sql`, esto con el fin de tener una base de datos de prueba.
2. Verificar que la base de datos este correindo desde el localhost:3306 para poder conectar adecuamente.
3. Clonar el proyecto y abrirlo con Netbeans, este proceso puede demorar un momento, ya que el IDE realiza unas compilaciones iniciales.
4. Las librerias usadas para el proyecto, ya se encuentran importadas en el mismo, de caso contrario y de tener incovenientes con estas, importarlas como JAR.
5. Las librerías son:
 * mysql-connector-java-8.0.26.jar
 * jcommon-1.0.23.jar
 * jfreechar-1.0.19.jar
Estas fueron adjuntas en la carpeta "librerias_usadas"
5. Por último para ejecutar la aplicación, se utiliza el botón de `Run Project` dentro de Netbeans y este automaticamente debería mostrar la ventana principal de la aplicación.
