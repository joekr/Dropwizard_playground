# temperature

How to start the temperature application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/temperature-1.0.jar server config.yml`
1. DB migration `java -jar target/temperature-1.0.jar db migrate config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## Questions


## Expectations

- ~~use lombok to replace getters/setters~~
- ~~API validation~~
- ~~DB migrations~~
- figure out some basic Guice
- Build a rough UI using Dropwizard Views
- add logger
- Figure out how the JDBI3 DAO interface provides the implementation, and how to get DI working with it
- return created object from API instead of just "created" status
