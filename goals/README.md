# goals

How to start the goals application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/goals-1.0.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

## Purpose

Become more familiar with Dropwizard. This web application will be a simple goal setting website. A user can login, create a team of multiple users who can view each others goals, but can not edit them. The user can CRUD their own goals though.

## Expectations

- Much of this is building off previous apps (hello-world and temperature)
- Authentication via username/password or JWT for API
- basic authorization around team goals (read only for team, write for user)
- Write basic tests for authorizations
- figure out some basic Guice (since I didn't in temperature app)
- There will be a UI with forms, but the API can be used as well
