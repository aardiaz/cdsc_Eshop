FROM  openjdk:17-ea-26-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} store.jar
ENTRYPOINT [ "java","-jar","store.jar" ]