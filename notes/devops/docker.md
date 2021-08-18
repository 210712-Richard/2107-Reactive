# Docker

**"It works on my machine"**

If the code does not work in the target environment what does it matter if it works in you dev environment?

If the code works in the dev environment, what would happen if we could just... ship the environment with the code?

## Virtual Machines

A simulation of a machine on another machine. Virtual machines run off of a machine image that holds a copy of an entire "guest operating system" in addition to installed libraries, configurations, and applications. You can create a VM image that contains everything your application needs, load your application into it and then ship that image to production.

Require a lot of resources of run, require lots of RAM and processing power and that makes it difficult to scale.

Think about Netflix. They have 500 services, each of which needs replication. They currently run ~ 10,000 EC2 instances. EACH of those EC2s is likely running multiple services. If Netflix had each of their services packaged as a VM, they'd likely have to double or more the number of EC2s or start scaling them vertically.

VirtualBox, VMWare, EC2

## Containers

A virtualization of an operating system that allows each image to contain only that which makes the image special. Rather than hosting an entire guest operating system, Docker virtualizes the aspects of the specific OS and takes control over the host hardware using the container engine.

Scales to your system and it's needs.

Requires far fewer resources.

## Docker

Docker is platform as a service that delivers software through containerization.

Each Docker image consists of layers that mutate a *base* image that is generally pulled from a repository such as *Docker Hub*.

**Docker Hub**: A repository in the cloud that contains images we can pull for use on our machines running the Docker engine. We can alos define and push images to Docker Hub, but beware fees.

**ECR**: Amazon's Elastic Container Repository. Like a private Docker Hub in AWS.

**Base Image**: The image you pull down as the first layer of your container. This image generally contains OS libraries and sometimes pre-installed software.

**Layers**: Each Docker image consists of "layers." Each layer is a seperate docker image that consists of a base image plus a single command.

### Docker Commands

* **FROM**: *NB* Specifies the base image. This can be an image from a remote repository or from your own images.
* **CMD**: *NB* Specifies an entry point into the image. The command specified by `CMD` will be executed when the image is run. Allows for default runtime parameters.
* **ENTRYPOINT**: Like CMD but without default runtime parameters.
* **RUN**: *NB* Run a command on the image during the build. Useful for setting up or installing things you need for the image to work.
* **COPY**: *NB* Copy files from your machine to the image.
* **ADD**: Add a file or directory from your machine to the image.
* **EXPOSE**: *NB* Allows the container to listen in on a network port. *NOTE* this is the not the network port of the HOST machine it is the network port of the CONTAINER. You still have to forward host machine traffic to the container. (You do this with the `-p` flag when calling `docker run`.)
* **WORKDIR**: Changes the active directory of the running container. Like a `cd` but it affects the running image. (effects the CMD or ENTRYPOINT command).
* **ARG**: Defines command line arguments that the container can take when building the image. *build-time parameters*



### Some CLI stuff
* `docker build -t {name} .`: Builds from the current directory
* `docker run {name}:latest`: Runs the latest build of the image given 

## Orchestration

Container Orchestration is the process by which we manage the deployment, replication, and maintenance of multiple containers.

Examples:
* Docker Swarm
* Kubernetes
* AWS ECS (Elastic Container Service)
* AWS Fargate (Serverless version of ECS)
* AWS EKS (Elastic Kubernetes Service)

### Fun thing

Docker created the Docker Container and the Docker Swarm for orchestrating containers.

Google created ContainerD Container and Kubernetes for orchestrating containers.

Most people use Docker as the container and Kubernetes for orchestration.

### Kubernetes Definitions
* **Cluster** - A collection of nodes with at least one master (Control Plane) and Worker (Kubelet) process.
* **Pod (In ECS, they call this a Task)** - Smallest unit of execution in K8s. An abstraction of an application managed by K8s.
  * If the application consists of 3 services and all should be replicated evenly, you could declare those three containers as a pod and K8s will replicate that pod across the nodes.
  * You can have a pod that consists of only a single container, or multiple containers.
* **Deployment** - A declaritive solution for managing Pods and ReplicaSets
  * A file that declares what your pods are and how pods should be replicated.
* **Service** - Defines a logical set of pods and a policy for accessing them.
  * The gateway service into your cluster.
* **Control Plane** - The master node. Contains our kube-controller manager which is responsible for ensureing that all nodes are healthy, all nodes are replicated correctly, and knows how to map our endpoints to our pods. (It knows the mappings due to the Service definitions.)
* **Node** - A machine running either the Control Plane or a Kubelet.
* **Kubelet** - A worker node. Ensures that the containers on the pods it contains are running.

### ECS vs Fargate

In ECS (and EKS) you define tasks and let your services deploy those tasks to a cluster you have created using EC2s. For example you could have a cluster of 5 `t2.medium`s to hancle your pods, and then if your deployments gets too large, you'll have to deploy more resources. But if your deployment isn't utilizing very much of your resources you're wasting money.

In Fargate, it dynamically provisions compute power based on the tasks that are running. You don't provision the cluster, it does.