# Play by Play Developer (Unscripted lessons)
# Docker for Web Developers with John Papa and Dan Wahlin

Role of Docker and Benefits
Installing and Using Docker Toolbox
Docker Images and Containers
Getting a Development Environment Up and RUnning

## Introduction
"Works on my machine" philosophy
once u have dev env setup, ur happy.
diff to emulate env
	e.g. emulate prod env locally

how do u manage diff versions when working with a team? 
we want to be able to bring up environment consistentlly
new hire, can get setup within the day

just need one person to setup docker image, whole team can use it later

Docker for Windows is like more slim version
	Docker Toolbox ran on Oracle VirtualBox, Docker doesn't need all the things VBox does (extra things there)

machines
	where is the image running
		windows
			linux VM
		linux
			the development machine; linux can run the containers natively

images
	docker hub
	layered file system
	set of files layered on top of each other
	previously would have VMs, install everything and take snapshots
		could be very large like 30GB
		now it will check if we already have an image; then doesnt redownload, simply builds on top of it
	e.g. Ubuntu > Node > applciation code

containers
	created using images
	runs the applications
	the instance of the image
	all separate
		can run any version of the app at the same time

most cloud providers support docker containers as well
	can ensure same environment on cloud as well

## Docker Benefits
	accelerate developer onboarding
	eliminate app conflicts
	environment consistency
		b/t dev, staging, production
	ship software faster

## Kitematic
gui tool for docker, instead of cli

## Pulling Images
docker pull
	pulls images from docker hub
	can have private repos for corpoate usage
	auto checks if u already have any layers pulled onto ur machine
		also verifies versions, will only download required images/layers

## custom formatting
docker ps
	custom format can put it inside of ~/.docker/config.json
		similar to ~/.bashrc

## Exposing ports
docker run -d -p 80:80 kitematic/hello-world-nginx
	external port is on left hand side
	internal port is on the right hand side

## Exploring docker-compose and the .dockerfile
to run using daemon (headless)
	docker-compose up -d
	docker-compose logs
		to see logs
docker-compose down
	stops and removes containers
docker-compose stop
	stops containers
docker-compose build [--no-cache]
	downloads images required
		based on dockerfiles
e.g. dockerfile
	```
	FROM node:latest
	MAINTAINER Kevin Ma
	ENV CONTAINER_PATH /var/www/angular2restfulservice
	[COPY . $CONTAINER_PATH]
	WORKDIR $CONTAINER_PATH
	RUN npm install supervisor -g
	EXPOSE 3000
	ENTRYPOINT ["supervisor","server.js"]
	```
docker build -f node.dockerfile -t kevinma/node .

can link source code to a container
	this lets us do real development, as code updates locally can see changes in the docker container
	COPY . $CONTAINER_PATH
		this is only for staging/production usage normally

# Bridging Containers and docker-compose.yml
## Legacy Linking
docker run -d --name mongodb mongo
docker run -d -p 3000:3000 --link mongodb --name nodeapp kevinma/node

## Create isolated networks
docker network create --driver bridge isolated_network
docker run -d --net=isolated_network --name mongodb mongo
docker run -d --net=isolated_network --name nodeapp -p 3000:3000 nodeapp kevinma/node
	previously any container could talk to any container, this lets us set a restriction only containers on same network can talk to each other

e.g. docker-compose.yml
```
version: '2'
services:
	node:
		container_name: nodeapp
		image: nodeapp
		build:
			context: .
			dockerfile: .docker/node.development.dockerfile
		volumes:
			- .:/var/www/angular2restfulservice
		environment:
			- NODE_ENV=development
		ports:
			- "3000:3000"
		networks:
			- nodeapp-network

	mongodb:
		container_name: mongodb
		image: mongo
		networks:
			- nodeapp-network
	
	networks:
		nodeapp-network:
			driver: bridge
```
docker-compose -f docker-compose.[development|production].yml build
docker-compose -f docker-compose.[development|production].yml up

context defines  where docker-compose starts to look at
	.
		dir of docker-compose.yml


volumes:
	- .:/var/www/angular2restfulservice

^these two lines lets us do live development in our local host machine and see changes in the docker container
	since we are running supervisor to watch for file changes

To run things, simply docker-compose build, then docker-compose up
