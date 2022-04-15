# Builder stage
FROM openjdk:18-jdk-alpine as builder
WORKDIR /opt/app
COPY . .
RUN ./gradlew customer:clean customer:build -x test

# Customer app stage
FROM openjdk:18-jdk-alpine as customer-app
WORKDIR /usr/app/customer
COPY --from=builder /opt/app/customer/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
