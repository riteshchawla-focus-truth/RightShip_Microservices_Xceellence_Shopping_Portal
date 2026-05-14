FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY ../pom.xml ./pom.xml
COPY ../shared ./shared
COPY . ./product-service
RUN mvn -q -DskipTests -pl product-service -am package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/product-service/target/product-service-1.0.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
