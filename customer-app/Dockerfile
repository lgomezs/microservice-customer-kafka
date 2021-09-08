FROM openjdk:11.0-jre

LABEL maintainer="Miguel Gomez"

ENV TZ=America/Lima \
    APP_HOME=/opt/application

ARG JAR_FILE=target/*.jar
COPY customer-app/target/*.jar $APP_HOME/app.jar

WORKDIR ${APP_HOME}
EXPOSE 8080

ENTRYPOINT [ "sh", "-c" ]
CMD ["exec java -Duser.timezone=America/Lima \
                -Dspring.profiles.active=dev \
                -XshowSettings:vm \
                -jar ${APP_HOME}/app.jar"]