VERSION=$(shell cat VERSION)
NAME=jupyter-lsd-kernel
IMAGE=lappsgrid/$(NAME)

all: jar docker

jar:
	mvn package

docker:
	cp target/$(NAME)-$(VERSION).jar src/docker/
	docker build -t $IMAGE src/docker

push:
	cd src/docker && docker push $IMAGE

dist:
	cd src/distribution && ./create.sh
	
clean:
	mvn clean
	rm src/docker/*.jar



