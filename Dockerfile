FROM openjdk:8
COPY "./target/filmoteca-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 9012
ENTRYPOINT ["java", "-jar", "app.jar"]

