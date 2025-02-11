FROM maven:3.8.5-openjdk-17 as maven
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine as build
ARG JAR_FILE=build/target/*.jar
COPY --from=maven ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:17-jdk-alpine
ARG DEPENDENCY=target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.ua.teamchallenge.handmadestore.HandmadeStoreApplication"]
EXPOSE 8080