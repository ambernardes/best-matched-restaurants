# Introduction 
Based on data from nearby restaurants, the objective of this application is to provide a basic search that allows people to find a restaurant where they can have lunch.

# Building and Testing
In order to avoid the need to install and configure Java, it is possible to run the application using Docker. To do this, just go to the root directory and execute the command:
```
docker compose up
```

The process downloads the java image, builds and tests the application, so it is important to keep in mind that it may take a few moments.

If java is properly installed and configured, it is possible to build and test the application by running the `maven` script. To do this, run the commands in the backend directory:

```
./mvnw package
```
Then execute the `WAR` file

```
java -jar .\target\bestmatchedreataurants-0.0.1-SNAPSHOT.jar
```

To execute the test script

```
./mvnw test
```
The application uses Java version 17. And it was developed using Springboot version 3.2.4.

# Implementation

The approach used in the implementation was to save the csv data in an H2 database during application initialization. The data is read and saved in specific tables.

For the search and sorting, the Criteria API was used.

When searching for string fields, the comparison is case insensitive.

# Endpoints

Once the application is running, there will be two endpoints:

- GET /api/cuisine/getAll - used to check complete list of registered cuisines

- GET /api/restaurant/filterRestaurants - the endpoint that gets the best matched restaurants based on the given criteria

The REST API server will run in [localhost:8080](http://localhost:8080/), with [Swagger enabled](http://localhost:8080/swagger-ui.html).