# Microservice with kafka events

Spring Boot application with a Kafka producer to publish messages to your Kafka topic, as well as with a Kafka consumer to read those messages.

![Screenshot](img/microservice architecture.png?raw=true "services")

## structure

    - customer-app
        main microservice
    - customer-config
        repository properies local.
    - customer-core
    - customer-producer
        generate data for customer and publish to topic kafka.
    - customer-consumer
        subscriber to topic kafka and save data customer to mongo
    - customer-services
        services rest customer

## compile

    mvn clean install

## Execute in docker local

    in local, add into host.conf: 
        127.0.0.1 zookeeper-server
        127.0.0.1 kafka-server
        127.0.0.1 mongodb

     docker-compose up 

## examples commands kafka

 -- create topic customer-events

        ./bin/kafka-topics.sh --bootstrap-server kafka-server:9092 --create --topic customer-events --partitions 1 --replication-factor 1

-- validate topic created:

        ./bin/kafka-topics.sh --list --bootstrap-server kafka-server:9092
      
--delete topic

        ./bin/kafka-topics.sh --bootstrap-server kafka-server:9092 --delete --topic customerDto-events

-- publish

     ./bin/kafka-console-producer.sh --topic customerDto-events --bootstrap-server kafka-server:9092

--subscribe to topic

    ./bin/kafka-console-consumer.sh --topic customerDto-events  --bootstrap-server kafka-server:9092

## Service endpoint get customers

 GET http://localhost:8080/api/v1/customer

## Deploy to AKS (minikube local)

-- install minikube and kubectl

[https://minikube.sigs.k8s.io/docs/start/](https://minikube.sigs.k8s.io/docs/start/)

-- login to account dockerhub and deploy image
       
        docker login -u "<YOUR_USER>>" -p "<YOU_PASSWORD>" docker.io
        docker-compose build
        docker images

        docker tag bitnami/zookeeper:latest "<YOUR_USER>>"/zookeeper:1.0
        docker push "<YOUR_USER>>"/zookeeper:1.0

        docker tag bitnami/kafka:latest "<YOUR_USER>>"/kafka:1.0
        docker push "<YOUR_USER>>"/kafka:1.0

        docker tag customereventkafka_customer-microservice:latest "<YOUR_USER>>"/customer-microservice:1.0
        docker push "<YOUR_USER>>"/customer-microservice:1.0

        docker tag mongo:latest "<YOUR_USER>>"/mongodb:1.0
        docker push "<YOUR_USER>>"/mongodb:1.0

-- change your image name into files AKS-deploy.yaml, AKS-deploy-kafka.yaml

        containers:
            image: "<YOUR_USER>>"/customer-microservice:1.0
            image: "<YOUR_USER>>"/zookeeper:1.0
            image: "<YOUR_USER>>"/mongodb:1.0

-- then execute:

            kubectl apply -f devops/AKS-deploy.yaml              
            kubectl apply -f devops/AKS-service-yaml

-- kubectl get pods

![Screenshot](img/pods.png?raw=true "pods")

-- kubectl get services

![Screenshot](img/services.png?raw=true "services")


![Screenshot](img/log-microservice.png?raw=true "services")

## port-forward local

    kubectl port-forward --address 0.0.0.0 service/mongodb 27017:27017
    kubectl port-forward --address 0.0.0.0 service/zookeeper-server 2181:2181
    kubectl port-forward --address 0.0.0.0 service/kafka-server 9092:9092
    kubectl port-forward --address 0.0.0.0 service/microservice-customer-event 8080:8080

# istio install and config

   [https://istio.io/latest/docs/examples/microservices-istio/enable-istio-all-microservices/](https://istio.io/latest/docs/examples/microservices-istio/enable-istio-all-microservices/)


 istio dashboard

  ![Screenshot](img/istio.png?raw=true "services")

 logs 

 ![Screenshot](img/istio-log.png?raw=true "services")

  metrics dashboard

 ![Screenshot](img/istio-metrics.png?raw=true "services")

  traffic animation

![Screenshot](img/istio-trafic-animation.png?raw=true "services")





