# Documentación del Proyecto: Sistema de Gestión de Tickets con GraphQL
# Se adjunta coleccion de postman para pruebas
### Descripción General
Este proyecto es una API desarrollada en Spring Boot 3.4.2 que permite la gestión de tickets mediante GraphQL y Spring Data JPA, con almacenamiento en PostgreSQL corriendo en Docker. La API proporciona funcionalidades para crear, actualizar, eliminar y consultar tickets, además de permitir la interacción con la API de GitHub.

La API proporciona funcionalidades para **crear, actualizar, eliminar y consultar tickets**, además de permitir la interacción con la API de **GitHub**.

## Tecnologías Utilizadas
- **Spring Boot 3.4.2**
- **Spring Data JPA**
- **Spring for GraphQL**
- **Spring Web**
- **Spring Boot DevTools**
- **Docker & Docker Compose**
- **PostgreSQL**
- **Maven**
- **GraphQL**
- **RestTemplate** (Para consumir API externa de GitHub)
- **Lombok**

## 🛠 Configuración
Instalación y Configuración

``` Instalación y Configuración
Clonar el Repositorio

git clone https://github.com/tu-usuario/prueba-tecnica.git
cd prueba-tecnica
```
## 🛠 Configuración de la Base de Datos (Docker & PostgreSQL)
El proyecto usa **Docker** para la base de datos. Para ejecutarla, usa el siguiente **docker-compose**:

``` 
docker-compose up -d
```
##  Configurar el archivo application.properties
Edita el archivo src/main/resources/application.properties para configurar la conexión a PostgreSQL:
```
# Configuración de GraphQL en Spring Boot
spring.graphql.path=/graphql

# Habilitar Altair GraphQL UI (Interfaz visual)
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.endpoint=/graphql

# Configuración de PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/ticketdb
spring.datasource.username=admin
spring.datasource.password=admin
spring.datasource.driver-class-name=org.postgresql.Driver

# Configuración de Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

github.api.url=https://api.github.com/search/users?q=YOUR_NAME
```
## Ejecutar la Aplicación
Para iniciar el servicio de Spring Boot, ejecuta:
```
mvn spring-boot:run
```

##   Endpoints de GraphQL 🚀
La API está expuesta en:
```
http://localhost:8080/graphql
```

## Tipos de Datos en GraphQL

```
type Ticket {
id: ID!
usuario: String
fechaCreacion: ```String
fechaActualizacion: String
estatus: String
}

type Query {
getTicketById(id: ID!): Ticket
getAllTickets: [Ticket]
getTicketsByStatus(estatus: String!): [Ticket]
}

type Mutation {
createTicket(usuario: String!, estatus: String!): Ticket
updateTicket(id: ID!, usuario: String, estatus: String): Ticket
deleteTicket(id: ID!): String
}
```
## Consultas Disponibles en GraphQL
 Obtener un Ticket por ID
``` Query

query {
getTicketById(id: 1) {
id
usuario
estatus
}
}

 Respuesta

{
"data": {
"getTicketById": {
"id": 1,
"usuario": "Juan Pérez",
"estatus": "ABIERTO"
}
}
}
```
```
##  Obtener Todos los Tickets

query {
getAllTickets {
id
usuario
estatus
}
}
## Filtrar Tickets por Estado

query {
getTicketsByStatus(estatus: "CERRADO") {
id
usuario
}
}
```

##  Mutaciones en GraphQL (Crear, Actualizar y Eliminar)
```
## Crear un Ticket

mutation {
createTicket(usuario: "Camila Rojas", estatus: "ABIERTO") {
id
usuario
estatus
}
}

## Actualizar un Ticket

mutation {
updateTicket(id: 1, usuario: "Luis González", estatus: "CERRADO") {
id
usuario
estatus
}
}
##  Eliminar un Ticket

mutation {
deleteTicket(id: 1)
}
```
## Consumo de API Externa: GitHub API 
Esta API permite buscar usuarios en GitHub.

##  Endpoint de la API:

```
http://localhost:8080/api/github/users
```

## Cómo Funciona
Se hace una petición a https://api.github.com/search/users?q=nombre
La API devuelve un listado de usuarios con su información básica.

## Ejemplo de Respuesta en JSON:

```
{
"total_count": 8,
"items": [
{
  "total_count": 8,
  "incomplete_results": false,
  "items": [
    {
      "login": "Mateus-Brito",
      "id": 13570164,
      "node_id": "MDQ6VXNlcjEzNTcwMTY0",
      "avatar_url": "https://avatars.githubusercontent.com/u/13570164?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/Mateus-Brito",
      "html_url": "https://github.com/Mateus-Brito",
      "followers_url": "https://api.github.com/users/Mateus-Brito/followers",
      "following_url": "https://api.github.com/users/Mateus-Brito/following{/other_user}",
      "gists_url": "https://api.github.com/users/Mateus-Brito/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/Mateus-Brito/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/Mateus-Brito/subscriptions",
      "organizations_url": "https://api.github.com/users/Mateus-Brito/orgs",
      "repos_url": "https://api.github.com/users/Mateus-Brito/repos",
      "events_url": "https://api.github.com/users/Mateus-Brito/events{/privacy}",
      "received_events_url": "https://api.github.com/users/Mateus-Brito/received_events",
      "type": "User",
      "user_view_type": "public",
      "site_admin": false,
      "score": 1.0
    },..
```
