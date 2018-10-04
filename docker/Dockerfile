FROM openjdk:8-jre-alpine

WORKDIR root/

ADD build/libs/kicker-*.jar ./application.jar

EXPOSE 8080

CMD ["java", "-server", "-jar", "/root/application.jar"]