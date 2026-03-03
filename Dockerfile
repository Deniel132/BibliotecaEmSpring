FROM eclipse-temurin:21
LABEL maintainer="teste"
WORKDIR /biblioteca
COPY target/Biblioteca-0.0.1-SNAPSHOT.jar /biblioteca/biblioteca.jar
ENTRYPOINT ["java","-jar","biblioteca.jar"]