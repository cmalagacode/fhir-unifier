FROM amazoncorretto:21.0.8
RUN yum update -y
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw .
COPY pom.xml .
COPY src/ src/
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/app.jar"]