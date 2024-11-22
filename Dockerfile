FROM gradle:8.10-jdk17-jammy AS build
RUN mkdir -p /linkkistl
COPY --chown=gradle:gradle . /linkkistl/src
WORKDIR /linkkistl/src
RUN gradle bootJar --no-daemon -x processAot

FROM eclipse-temurin:21-alpine
LABEL authors="Niklas Haiden"

RUN mkdir /linkkistl
WORKDIR /linkkistl
COPY --from=build /linkkistl/src/build/libs/*.jar /linkkistl/linkkistl.jar

EXPOSE 8080
CMD ["java", "-jar", "/linkkistl/linkkistl.jar"]