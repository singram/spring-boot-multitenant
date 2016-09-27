# spring-boot-multitenant
Multi tenancy example using spring boot and the Hibernate SCHEMA strategy.

## Getting started

Pre-requisites
- Java 1.8
- Docker
- Docker-compose

### Installation (debian base)

#### Install Docker

    apt-get install apparmor lxc cgroup-lite
    wget -qO- https://get.docker.com/ | sh
    sudo usermod -aG docker YourUserNameHere
    sudo service docker restart

#### Install Docker-compose  (1.6+)

*MAKE SURE YOU HAVE AN UP TO DATE VERSION OF DOCKER COMPOSE*

To check the version:

    docker-compose --version

To install the 1.6.0:

    sudo su
    curl -L https://github.com/docker/compose/releases/download/1.6.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
    chmod +x /usr/local/bin/docker-compose
    exit

## To run

    docker-compose up

### To run docker mysql while running app locally

    docker-compose up mysql
    ./gradlew bootRun

## Usage

### Prime the database (only needs to be done once)

    docker-compose up mysql
    mysql -u root   -p1234   -h 127.0.0.1 -e "GRANT ALL ON *.* TO 'myuser'@'%'; FLUSH PRIVILEGES;"
    mysql -u myuser -pmypass -h 127.0.0.1 < db_migration.sql

At this point there should be two identical databases `tenant_a` and `tenant_b` with privileges such that the `myuser` account can access both

### Start the app

    ./gradlew clean build buildDocker
    docker-compose up api

### API interactions

    curl -H "X-TenantID:tenant_a" localhost:8080/person/1
    curl -i -X POST -H "X-TenantID:tenant_a" -H "Content-Type:application/json" -d '{ "firstName" : "Frodo", "lastName" : "Baggins" }' http://localhost:8080/person/
