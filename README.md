# Stock API

Spring Boot RESTful API to handle simple CRUD operations to handle stock data.

# Instructions to Run
 
Application is docker containerized to run and docker compose configuration is provided to run both database and the API

### Prerequisites

To build and run the application environment should satisfy following requirements

- `Java 11.0.13`
- `Gradle 7.4.2`
- `Docker 20.10.12 or above`

### Clone the Source Code from GitHub

> `git clone https://github.com/rathnaviraj/stock.git`

### Build Spring Boot App

> `gradle build`
### Build Docker Image

> `docker build -t stock-api:1.0 .`
### Run Docker Containers
> `docker-compose up -d`

### View/Evaluate API

> http://localhost:8080/openapi/swagger-ui/index.html

# Sample Data

A set of sample stock data is initialized to the database when application starts up. The data set that is initializing can be modified by modifying following SQL file in the source project.

> src/main/resources/data.sql

# Configurations

Page size of the stock listing endpoint could be configurable using environment variable.

To configure the value in docker configuration modify the desired page size in `docker-compose.yml` 

> DEFAULT_PAGE_SIZE=<page-size-value>

# Versions

- `Gradle 7.4.2`
- `Java 11.0.13`
- `Spring Boot 2.6.7`
- `Docker 20.10.12 or above`