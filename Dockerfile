FROM openjdk:8-jdk-alpine

LABEL maintainer="dev.lucasmartins@gmail.com"

VOLUME /tmp

ARG JAR_FILE=target/depara-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} artifact.jar

EXPOSE 9476

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/artifact.jar","-Xms2g","-Xmx2g","-Xmn150m","-XX:GCTimeRatio=2","-XX:ParallelGCThreads=10","-XX:+UseParNewGC","-XX:+DisableExplicitGC","-XX:TieredStopAtLevel=1"]