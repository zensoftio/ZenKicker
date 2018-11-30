FROM openjdk:8-jre-alpine

WORKDIR root/

ADD backend/jar/backend-*.jar ./application.jar

EXPOSE 8080

CMD java -server -Xmx256M -jar /root/application.jar