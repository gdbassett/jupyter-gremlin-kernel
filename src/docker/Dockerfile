# gdbassett/jupyter-gremlin-kernel

ARG BASE_CONTAINER=jupyter/minimal-notebook
FROM $BASE_CONTAINER

# MAINTAINER Keith Suderman (suderman@cs.vassar.edu)

USER root

# TODO: is this the right version of openjdk? 7 sounds a bit old
RUN apt-get update && apt-get install -y openjdk-11-jdk
RUN mkdir -p /jupyter/gremlin
COPY ./jupyter-gremlin-kernel-0.1.0.9000.jar /jupyter
COPY ./src/docker/kernel.json /jupyter/gremlin
COPY ./src/docker/startup.sh /usr/bin/startup
RUN chmod a+x /usr/bin/startup
RUN jupyter kernelspec install --name=gremlin /jupyter/gremlin

RUN chown -R $NB_UID:$NB_UID /home/jovyan/
USER $NB_UID
WORKDIR /home/jovyan
CMD ["/usr/bin/startup"]



