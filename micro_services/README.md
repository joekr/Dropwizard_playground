# Micro Services

How to start each application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/goals-1.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

or from root dir `make` then `make run`


## Testing

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## Purpose

Become more familiar with Dropwizard. This is a basic micro service setup. It might be a bit convoluted, but on purpose. The plan is to have an Identity service (Auth), DB service and business logic service.

## Expectations

- Much of this is building off previous apps (hello-world, temperature and goals)
