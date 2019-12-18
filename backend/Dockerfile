FROM gradle:6.0-jdk8

RUN mkdir /backend
WORKDIR /backend

#COPY gradle gradle
#COPY gradlew ./
COPY wait-for-it.sh /

COPY build.gradle ./
COPY src src

RUN gradle build -x test

EXPOSE 8080
