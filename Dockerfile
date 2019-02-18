FROM openjdk:11-jre-slim

WORKDIR root/

ADD backend/build/libs/backend-*.jar ./application.jar

CMD java -server -Xmx256M -jar /root/application.jar