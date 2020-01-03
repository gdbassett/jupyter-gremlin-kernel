# Jupyter Gremlin Kernel

The Lappsgrid Serivces DSL (LSD) is a Groovy based DSL (Domain Specific Language) that can be used to invoke LAPPS Grid web services from Groovy scripts. 

The [Jupyter](https://jupyter.org) gremlin kernel is a Jupyter kernel based on the [Jupyter Groovy Kernel](https://github.com/lappsgrid-incubator/jupyter-groovy-kernel) and [Jupyter LSD Kernel](https://github.com/lappsgrid-incubator/jupyter-lsd-kernel) that allows the user to use jupyter as a webUI for a gremlin console.

## Table of contents
1. [Installation](#installation)
1. [Functions](#notebook-functions)
1. [Docker](#docker)

## Installation

### From Source

Building the Jupyter Gremlin Kernel project requires Maven 3.x or higher.
TODO: Do we really need maven?

```bash
$> git clone https://github.com/gdbassett/jupyter-gremlin-kernel.git 
$> cd jupyter-gdbassett-kernel
$> mvn package
$> ./install.sh <kernel directory>
```

Where *&lt;kernel directory&gt;* is a directory where the kernel jar file will be copied and can be any directory on your system.

### From Pre-compiled Binaries

Download and expand the [Gremlin Kernel archive TODO](TODO) and then run the *install.sh* script.
TODO: pre-compile kernels

```bash
$> wget https://TODO
$> tar xzf jupyter-germlin-kernel-latest.tgz
$> cd jupyter-gremlin-kernel-x.y.z
$> ./install.sh <kernel directory>
```

Where *&lt;kernel directory&gt;* is a directory where the kernel jar file will be copied and can be any directory on your system. Replace *x.y.z* with the current version number.

### Notes

By default the *install.sh* script will install the Jupyter kernel to the system kernel directory. This is typically */usr/local/share/juptyer* on Linux/MacOS systems and %PROGRAMDATA%\jupyter\kernels on Windows systems.  To install the Jupyter kernel to the User's directory you must either:

* Edit the *install.script* and add the *--user* option to the `kernelspec` command, or
* Edit the kernel.json file to set the *argv* paramater to the location of the Jupyter Groovy kernel and then run the `jupyter kernelspec install` command manually.

You can view the default directories that Jupyter uses by running the command `jupyter --paths`.


## Notebook Functions

The Jupyter Gremlin kernel provides serveral functions to simplify interacting with a Galaxy instance.

TODO: Document functions or point to gremlin documentation.

## Docker

A Docker image containing the gremlin kernel is available from the [Docker Hub](https://hub.docker.com/TBD).  

Inside the container Jupyter uses the directory */home/jovyan* to save and load notebooks.  To persists notebooks created inside the container mount a local directory as */home/jovyan*.

```bash
$> VOLUME="-v $HOME/notebooks:/home/jovyan"
$> PORTS="-p 8888:8888"
$> docker run -d $PORTS $ENV $VOLUME gdbassett/jupyter-gremlin-kernel 
```
