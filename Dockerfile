FROM 192.168.0.90:8080/jdk21:21
ARG JAR_FILE
COPY target/${JAR_FILE} /data/api/application.jar
WORKDIR /data/api
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","application.jar"]
