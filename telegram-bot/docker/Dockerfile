FROM openjdk:8-jre-alpine

WORKDIR root/

ADD build/libs/telegram-*.jar ./application.jar

EXPOSE 8080

CMD java -server -Xmx256M -jar -Dspring.profiles.active=deploy /root/application.jar