docker build --build-arg JAR_FILE=build/libs/heroes-0.0.1-SNAPSHOT.jar -t w2m/heroes .
docker run -p 8080:8080 w2m/heroes

http://localhost:8080/swagger-ui.html#/ping-controller
