# Pablo Rendon java with sprint framework project example

## Requirements ##

 - Java JDK 17
 - Gradle 3.2.0 o superior

## Compile

```
gradle clean build
```

## Run app

to run server first set profile `dev` due is required to start h2 database

## Documentation

swagger ui documentation 

`http://localhost:8080/swagger-ui/index.html`

## Further info

Some frameworks/libs/best practices used:

 - spring boot
 - Domain-driven design (DDD) hexagonal architecture is implemented
 - JPA
 - units tests
 - jacoco reports for code coverage
 - spring AOP (to run aspect oriented programing)
 - swagger for API documentation

**NOTE:** jacoco report can be found in `/build/jacoco/test/html/index.html` after compile project