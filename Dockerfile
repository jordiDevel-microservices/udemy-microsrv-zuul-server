FROM openjdk:8-slim
VOLUME /tmp
EXPOSE 8080
COPY ./target/springboot-servicio-zuul-server-0.0.1-SNAPSHOT.jar zuul-server.jar
COPY ./wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ./wait-for-it.sh -t 0 eureka-server:8761 -- java -jar -Dspring.profiles.active=docker zuul-server.jar
