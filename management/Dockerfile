FROM openjdk:11.0.4-jre-slim-stretch
COPY . /home/gradle/project
WORKDIR /home/gradle/project
ENV DEBIAN_FRONTEND noninteractive
RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections
RUN apt-get update && echo y | apt-get install curl
HEALTHCHECK --interval=5s --timeout=3s --start-period=10m CMD curl --fail http://localhost:9000/customers/healthcheck || exit 1
COPY ./build/libs/management-1.0.4.jar /app.jar
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar $JAVA_OPTS /app.jar