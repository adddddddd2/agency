FROM maven:3.9.0
WORKDIR /app
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run