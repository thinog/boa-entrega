# Builder stage
FROM openjdk:18-jdk-alpine as builder
WORKDIR /opt/app
COPY . .
RUN ./gradlew supplier:clean supplier:build -x test

# Supplier app stage
FROM openjdk:18-jdk-alpine as supplier-app
WORKDIR /usr/app/supplier
COPY --from=builder /opt/app/supplier/build/libs/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
