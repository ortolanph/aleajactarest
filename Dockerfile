FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

VOLUME /tmp

ARG DEPENDENCY=build/dependency
ARG APPCLASS=hello.Application
ENV APPCLASS_ENV=$APPCLASS

COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT java -cp app:app/lib/* $APPCLASS_ENV
HEALTHCHECK --interval=10s --timeout=3s --start-period=5s --retries=3 CMD ["curl", "-f", "localhost:8080/actuator/health"]
