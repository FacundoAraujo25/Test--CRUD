Backend
El backend de esta aplicación se desarrolló utilizando las siguientes tecnologías y dependencias:

- Java: El backend está desarrollado en Java, aprovechando todas sus características y beneficios.
- Spring Boot: Utilizamos el framework Spring Boot para facilitar el desarrollo de aplicaciones Java.
- Spring Data JPA: Para la capa de persistencia, utilizamos Spring Data JPA para interactuar con la base de datos de manera eficiente.
- Spring Data REST: Utilizamos Spring Data REST para exponer los servicios RESTful basados en los repositorios JPA.
- Spring Web: Para manejar las solicitudes HTTP y construir la API REST, utilizamos Spring Web.
- Spring Security: Implementamos Spring Security para la autenticación y autorización de usuarios en la aplicación a través de Basic Auth.
- MySQL Connector/J: Para la conexión con la base de datos MySQL, utilizamos el conector Java MySQL Connector/J.
- Lombok: Utilizamos Lombok para generar automáticamente código repetitivo, como getters, setters, constructores, etc.

Las principales entidades utilizadas son: Course, User, Teacher, Student y Admin. 
- Course: cuyos atributos o propiedades son: name (nombre del curso), startDate y finishDate (fechas de comienzo y finalización del curso) y teachers (colección donde se podrá ver a los profesores de cada curso a través de relaciones entre entidades y tablas intermedias).
- User: será la super clase de donde se heredarán los principales atributos para las demás clases como Student, Teacher y Admin. Estos atributos serán: firstName, lastName, email y password.
- Para Teacher y Student se usarán también propiedades de tipo boolean para saber si éstos están activos o no (activeTeacher y activeStudent) y si están registrados en cursos o aún no (hasAnyCourse).
