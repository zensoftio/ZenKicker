FROM openjdk:11.0-jre-slim

WORKDIR root/

ADD backend/build/libs/backend-*.jar ./application.jar

CMD java -server -Xmx256M -jar /root/application.jar