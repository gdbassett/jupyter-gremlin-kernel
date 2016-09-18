# lappsgrid/jupyter-lsd-kernel

FROM jupyter/notebook
MAINTAINER Keith Suderman (suderman@cs.vassar.edu)

USER root

RUN apt-get update && apt-get install -y openjdk-7-jdk
RUN mkdir -p /jupyter/lsd
COPY ./jupyter-lsd-kernel-1.0.0-SNAPSHOT.jar /jupyter
COPY kernel.json /jupyter/lsd
COPY startup.sh /usr/bin/startup
RUN chmod a+x /usr/bin/startup
RUN jupyter kernelspec install --name=lsd /jupyter/lsd

USER jovyan
WORKDIR /home/jovyan
CMD ["/usr/bin/startup"]



