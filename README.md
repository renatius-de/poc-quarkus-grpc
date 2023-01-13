# Quarkus POC Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: [Quarkus](https://quarkus.io/).

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only
> at [Dev UI](http://localhost:8080/q/dev/).

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with:

* `./build/quarkus-1.0-SNAPSHOT-runner`
* `./library/build/library-1.0-SNAPSHOT-runner`
* `./rest/build/rest-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please
consult [Gradle Tooling](https://quarkus.io/guides/gradle-tooling).

## Related Guides

- [Kotlin](https://quarkus.io/guides/kotlin): Write your services in Kotlin
- [Flyway](https://quarkus.io/guides/flyway): Handle your database schema migrations
- [Hibernate ORM](https://quarkus.io/guides/hibernate-orm): Define your persistent model with Hibernate ORM and JPA
- [Hibernate Validator](https://quarkus.io/guides/validation): Validate object properties (field, getter) and method
  parameters for your beans (REST, CDI, JPA)
- [Jacoco - Code Coverage](https://quarkus.io/guides/tests-with-coverage): Jacoco test coverage support
- [JDBC Driver - PostgreSQL](https://quarkus.io/guides/datasource): Connect to the PostgreSQL database via JDBC
- [Reactive Routes](https://quarkus.io/guides/reactive-routes): REST framework offering the route model to define
  non-blocking endpoints
- [SmallRye OpenAPI](https://quarkus.io/guides/openapi-swaggerui): Document your REST APIs with OpenAPI - comes with
  Swagger UI
- [YAML Configuration](https://quarkus.io/guides/config#yaml): Use YAML to configure your Quarkus application

## Provided Code

### YAML Config

Configure your application with YAML

[Related guide section...](https://quarkus.io/guides/config-reference#configuration-examples)

The Quarkus application configuration is located in `src/main/resources/application.yml`.

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
