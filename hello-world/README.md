# hello-world

How to start the hello-world application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/hello-world-2.0.1.jar server hello-world.yml`
1. To check that your application is running enter url `http://localhost:8080/hello-world?name=Successful+Dropwizard+User`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
