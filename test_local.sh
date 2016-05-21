#!/usr/bin/env bash

PORT=9090
JAVA_DEBUG_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=4000, suspend=n"
JAVA_OPTS=""

JAR_FILE="target/aleajactarest.jar"
YML_FILE="src/main/resources/aleajactarest.yml"

mvn clean install

java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar $JAR_FILE server $YML_FILE