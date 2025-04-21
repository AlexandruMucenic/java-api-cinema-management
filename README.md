# Java Web Api for a cinema management app 

## Overview
This project is a Java Spring application that serves as a backend for a cinema management system. It provides CRUD functionality for Actors, Movies, and Reservations, leveraging Spring Framework, Spring Data JPA, Hibernate, and PostgreSQL for persistence, and exposes a RESTful API for interaction.

## Tech Stack
- **Language:** Java 17+
- **Frameworks/Libraries:** Spring Core, Spring Web MVC, Spring Data JPA, Hibernate, HikariCP, Jackson (JSR-310 module), Lombok
- **Database:** PostgreSQL
- **Build Tool:** Gradle
- **Logging:** SLF4J / Logback
- **API Format:** JSON

## Project Structure
```
cinema
├── cinema.core            # Core domain, entities, repositories, services
│   ├── config             # JPA and data source configuration
│   ├── model              # JPA entities: Actor, Movie, Reservation
│   ├── repository         # Spring Data JPA repositories
│   └── service            # Service interfaces and implementations
└── cinema.web             # Web layer: configuration, controllers, DTOs, converters
    ├── config             # Web MVC and application initialization
    ├── controller         # REST controllers for Actors, Movies, Reservations
    ├── converter          # Converters between entities and DTOs
    └── dto                # Data transfer objects (DTOs)
```

## Prerequisites
- Java Development Kit (JDK) 17 or higher
- Gradle 7.x
- PostgreSQL database
- (Optional) Postman or similar tool for API testing

## Configuration
1. **Database Properties**  
   Create `local/db.properties` under `src/main/resources`:
   ```properties
   db.jdbcUrl=jdbc:postgresql://localhost:5432/cinema
   db.username=your_db_username
   db.password=your_db_password
   db.generateDDL=true
   ```
2. **Gradle Dependencies**  
   Ensure your `build.gradle` includes:
   ```groovy
   implementation 'org.springframework:spring-webmvc'
   implementation 'org.springframework.data:spring-data-jpa'
   implementation 'com.zaxxer:HikariCP'
   implementation 'org.postgresql:postgresql'
   implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310'
   implementation 'org.projectlombok:lombok'
   ```

## Building & Running
1. **Build the project:**
   ```bash
   ./gradlew build
   ```
2. **Package as WAR** (if not using embedded server):
   ```bash
   ./gradlew war
   ```
3. **Deploy** the generated WAR (`build/libs/*.war`) to a servlet container (e.g., Apache Tomcat 9+), or configure an embedded container.
4. **Access API** at `http://localhost:8080/api/...`

## API Endpoints

### Actors
- **GET** `/api/actors`  
  Retrieve all actors.

- **POST** `/api/actors`  
  Create a new actor.  
  **Body**:
  ```json
  {
    "ssn": "123-45-6789",
    "name": "John Doe",
    "age": 35,
    "nationality": "American",
    "awardsCount": 3
  }
  ```

### Movies
- **GET** `/api/movies`  
  Retrieve all movies.

- **POST** `/api/movies`  
  Create a new movie.  
  **Body**:
  ```json
  {
    "title": "Inception",
    "genre": "Sci-Fi",
    "duration": 148,
    "actorIds": [1, 2]
  }
  ```

- **PUT** `/api/movies/{id}`  
  Update an existing movie.

- **DELETE** `/api/movies/{id}`  
  Delete a movie.

### Reservations
- **GET** `/api/reservations`  
  Retrieve all reservations.

- **GET** `/api/reservations/{id}`  
  Retrieve a reservation by ID.

- **POST** `/api/reservations`  
  Create a new reservation.  
  **Body**:
  ```json
  {
    "customerName": "Alice Smith",
    "price": 15.00,
    "date": "2025-05-10",
    "hour": "20:00:00",
    "movieId": 1
  }
  ```

- **PUT** `/api/reservations/{id}`  
  Update a reservation.

- **DELETE** `/api/reservations/{id}`  
  Delete a reservation.

## Data Model

### Actor
| Field        | Type    | Description                  |
|--------------|---------|------------------------------|
| id           | Long    | Auto-generated primary key   |
| ssn          | String  | Unique Social Security Number|
| name         | String  | Actor's full name            |
| age          | Integer | Actor's age                  |
| nationality  | String  | Actor's nationality          |
| awardsCount  | Integer | Number of awards won         |

### Movie
| Field    | Type    | Description                   |
|----------|---------|-------------------------------|
| id       | Long    | Auto-generated primary key    |
| title    | String  | Movie title                   |
| genre    | String  | Movie genre                   |
| duration | Integer | Duration in minutes           |
| actors   | Set<Actor> | Associated actors          |

### Reservation
| Field        | Type       | Description                              |
|--------------|------------|------------------------------------------|
| id           | Long       | Auto-generated primary key               |
| customerName | String     | Name of the person making the reservation|
| price        | BigDecimal | Ticket price                             |
| date         | LocalDate  | Reservation date                         |
| hour         | LocalTime  | Reservation time                         |
| movie        | Movie      | Associated movie                         |
