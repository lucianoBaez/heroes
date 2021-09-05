# Prueba técnica spring boot: W2m

## Consigna

Desarrollar, utilizando Spring Boot 2 y Java 11, una API que permita hacer un mantenimiento de súper
héroes.

Este mantenimiento debe permitir:
* Consultar todos los súper héroes.
* Consultar un único súper héroe por id.
* Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro
enviado en la petición. Por ejemplo, si enviamos “man” devolverá “Spiderman”, “Superman”,
“Manolito el fuerte”, etc.
* Modificar un súper héroe.
* Eliminar un súper héroe.
* Test unitarios de algún servicio.


## Tecnologías utilizadas para implementar la solución.

* Lenguaje: java versión 11
* Framework: spring boot.
* Librerias: Jacoco para code coverage, mockito para los tests, swagger para documentar los servicios rests, lombok.
* Docker


## Instalación  del proyecto
1. **Descargar código fuente**

```console
git clone https://github.com/lucianoBaez/heroes.git
```

2. **Compilar**

```console
./gradlew clean build
```

3. **Correr el proyecto localmente**

```console
./gradlew bootRun
```

4. **Generar imagen de docker**

```console
docker build --build-arg JAR_FILE=build/libs/heroes-0.0.1-SNAPSHOT.jar -t w2m/heroes .
```

5. **Levantar imagen de docker**

```console
docker run -p 8080:8080 w2m/heroes
```

6. **Urls**

    [Documentación de la api: Swagger](http://localhost:8080/swagger-ui.html)

![](/swagger.png)

7. **Invocación a servicios**

* Listar todos los súper heroes

```console
curl --location --request GET 'localhost:8080/api/hero' 
```
* Consultar súper héroe por id

```console
curl --location --request GET 'localhost:8080/api/hero/id/1' 
```
* Consultar súper héroe por nombre

```console
curl --location --request GET 'localhost:8080/api/hero/name/man' 
```

* Crear súper héroe

```console

curl --location --request POST 'localhost:8080/api/hero' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "aaaaaa"
}'

```

* Actualizar súper héroe

```console
curl --location --request PUT 'localhost:8080/api/hero' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
    "name": "aaaaaa"
}'
```
* Borrar súper héroe

```console
curl --location --request DELETE 'localhost:8080/api/hero/id/3' 
```



## Tests

**Ejecución de tests:**
```console
 ./gradlew clean test
```

**Para revisar la cobertura de código se uso jacoco:**
```console
 ./gradlew test jacocoTestReport
```

![](/code_coverage.png)

El resultado queda en:

```console
 {ruta del proyecto}/heroes/build/jacocoHtml/index.html
```