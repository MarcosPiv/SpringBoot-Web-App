# Gestión de Usuarios

Este proyecto es una aplicación web básica para la gestión de usuarios que incluye funcionalidades de registro, inicio de sesión y administración de usuarios. La aplicación está construida utilizando Spring Boot en el backend y un frontend que permite a los usuarios interactuar con el sistema. Se establece una API RESTful utilizando Spring Boot que permite operaciones CRUD (Crear, Leer, Actualizar, Eliminar) básicas para la gestión de usuarios.

## Funcionalidades

### 1. Registro de Usuarios
Permite a los nuevos usuarios registrarse en el sistema proporcionando un nombre, apellido, correo electrónico, y una contraseña. La contraseña se almacena de manera segura utilizando el algoritmo de hashing **Argon2**.

### 2. Inicio de Sesión
Permite a los usuarios registrados iniciar sesión en el sistema utilizando su correo electrónico y contraseña. Si las credenciales son correctas, se genera un token JWT que se utiliza para autenticar futuras solicitudes.

### 3. Listar Usuarios
Permite a los usuarios autenticados visualizar una lista de todos los usuarios registrados en el sistema.

### 4. Eliminar Usuarios
Permite a los usuarios autenticados eliminar otros usuarios del sistema mediante su identificador único (ID).

## Estructura del Proyecto

- **Backend**: Desarrollado en Java utilizando Spring Boot. Contiene controladores para manejar las solicitudes HTTP, servicios para la lógica de negocio y DAOs para interactuar con la base de datos.
- **Frontend**: Interfaz de usuario básica que permite registrar, iniciar sesión, y gestionar usuarios. Está construida en HTML, CSS y JavaScript.
- **Base de Datos**: Utiliza JPA para la persistencia de datos en una base de datos SQL.

## Endpoints de la API

### Autenticación
- **POST `/api/login`**: Autentica a un usuario y devuelve un token JWT.

### Usuarios
- **GET `/api/usuarios`**: Devuelve la lista de todos los usuarios. Requiere un token de autorización.
- **POST `/api/usuarios`**: Registra un nuevo usuario.
- **DELETE `/api/usuarios/{id}`**: Elimina un usuario por su ID. Requiere un token de autorización.

## Seguridad

La seguridad en la aplicación está manejada a través de JSON Web Tokens (JWT). Los tokens se generan al iniciar sesión y deben ser incluidos en el encabezado de autorización de todas las solicitudes protegidas.

## Instalación y Ejecución

### Requisitos Previos

- Java 17 o superior
- Maven 3.6.3 o superior
- Una base de datos SQL compatible (como MySQL o PostgreSQL)

### Instrucciones

1. Clona el repositorio:
   ```bash
   git clone https://github.com/MarcosPiv/SpringBoot-Web-App.git


2. Configurar la base de datos: 
Crear una tabla usuarios con la siguiente estructura
![image](https://github.com/user-attachments/assets/fa9580cf-db69-4372-b258-0cc861d2d590)

Configurar segun tu base de datos los parametros en
`src/main/resources/application.properties`
```
# GENERAL
server.port=8080
#
# DATABASE
spring.datasource.url=jdbc:mysql://localhost/<nombre_de_tu_base_de_datos>?useSSL=false
spring.datasource.dbname=cursojava
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_contraseña>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
*Nota*:
Asegúrate de que tu servidor MySQL esté configurado para permitir conexiones desde la dirección IP donde se ejecuta tu aplicación, y que el puerto predeterminado (3306) esté abierto y accesible.

La configuración useSSL=false es adecuada para un entorno de desarrollo local. Para entornos de producción, considera configurar SSL para conexiones seguras.

3. Ejecuta el proyecto con Maven:
```bash
mvn spring-boot:run
```
4. Abre tu navegador y navega a http://localhost:8080/registrar.html para inicar interactuardo con la aplicación.
