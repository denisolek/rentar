FROM openjdk:11.0.3-jre-slim-stretch
COPY . /home/gradle/project
WORKDIR /home/gradle/project
ENV DEBIAN_FRONTEND noninteractive
RUN echo 'debconf debconf/frontend select Noninteractive' | debconf-set-selections
RUN apt-get update && echo y | apt-get install curl
HEALTHCHECK --interval=5s --timeout=3s --start-period=10m CMD curl --fail http://localhost:9001/cars/healthcheck || exit 1
COPY ./build/libs/fleet-1.0.8.jar /app.jar
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -Dfile.encoding=UTF-8 -jar $JAVA_OPTS /app.jar