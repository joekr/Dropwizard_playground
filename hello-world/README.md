# hello-world

How to start the hello-world application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/hello-world-2.0.1.jar server hello-world.yml`
1. To check that your application is running enter url `http://localhost:8080/hello-world?name=Successful+Dropwizard+User`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## Questions

- Difference between `dropwizard-hibernate` and `dropwizard-jdbi`

## Extras

While learning I've gotten the API to do the basic CRUD I wanted it to do. A few things are missing that I will learn while building other apps.

- how PATCH works
- return data object on create/update
- better mapping from JSON into DAO. I feel like passing params on all calls is isn't great
- Guice/Guava/Lombok
- input validation
