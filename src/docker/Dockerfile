# gdbassett/jupyter-gremlin-kernel

FROM jupyter/notebook
# MAINTAINER Keith Suderman (suderman@cs.vassar.edu)

USER root

# TODO: is this the right version of openjdk? 7 sounds a bit old
RUN apt-get update && apt-get install -y openjdk-7-jdk
RUN mkdir -p /jupyter/gremlin
COPY ./jupyter-gremlin-kernel-0.1.0.9000.jar /jupyter
COPY kernel.json /jupyter/gremlin
COPY startup.sh /usr/bin/startup
RUN chmod a+x /usr/bin/startup
RUN jupyter kernelspec install --name=gremlin /jupyter/gremlin

WORKDIR /home/jovyan
CMD ["/usr/bin/startup"]



