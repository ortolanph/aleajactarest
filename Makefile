SPRING_APP_PORT=9090
APP_FILE=target/aleajactarest.jar

.PHONY: clean

clean:
	@mvn -q clean

compile:
	@mvn clean install

run: compile
	@[ -f /etc/hosts ] && java -Dserver.port=$(SPRING_APP_PORT) -jar $(APP_FILE) || echo "No good, man, no good!"