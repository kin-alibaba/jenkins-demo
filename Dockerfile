FROM openjdk:8u181-jre
MAINTAINER Kin <y.kin@test.com>

COPY . /app
WORKDIR /app
EXPOSE 8080

CMD java -jar /app/target/Demo-0.0.1-SNAPSHOT.war -Xmx512m --management.security.enabled=false

