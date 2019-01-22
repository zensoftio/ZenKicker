FROM openjdk:8-jre-alpine

WORKDIR root/

ADD build/libs/backend-*.jar ./application.jar

CMD java -server -Xmx256M -jar /root/application.jar