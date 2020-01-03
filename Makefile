VERSION=$(shell cat VERSION)
NAME=jupyter-germlin-kernel
IMAGE=gremlin/$(NAME)

all: jar docker

jar:
	mvn package

docker:
	cp target/$(NAME)-$(VERSION).jar src/docker/
	docker build -t $(IMAGE) src/docker

push:
	cd src/docker && docker push $(IMAGE)

dist:
	cd src/distribution && ./create.sh
	
clean:
	mvn clean
	if [ -e src/docker/*.jar ] ; then rm src/docker/*.jar ; fi



