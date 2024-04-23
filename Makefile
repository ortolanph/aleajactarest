VERSION="0.0.1"

CONTAINER_PORT="8080"
HOST_PORT="9100"

APP_FILE="AleaJactaREST-0.0.1-SNAPSHOT.jar"

DOCKER_IMAGE_NAME="alea-jacta-rest"
DOCKER_CONTAINER_NAME="alea-jacta-rest-run"

.PHONY: compile

compile:
	@./gradlew clean build
	@./gradlew unpack

docker-build: compile
	@docker build -t ${DOCKER_IMAGE_NAME}:${VERSION} .

docker-run: docker-build
	@docker run --name ${DOCKER_CONTAINER_NAME} -d -p ${HOST_PORT}:${CONTAINER_PORT} ${DOCKER_IMAGE_NAME}:${VERSION}
