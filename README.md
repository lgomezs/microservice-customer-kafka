# Microservice customerDto with kafka events

microservices events with kafka.

# structure

    - customer-app
        main microservice
    - customer-config
        repository properies local.
    - customer-core
    - customer-producer
        generate data for customer and publish to topic kafka.
    - customer-consumer
        subscriber to topic kafka and save date customer to mondoDB
    - customer-services
        spring rest and publish endpoint the data customer.

# compile

    mvn clean install

# Microservice execute docker

 docker-compose up 

    start zookeeper-server,  kafka-server1 , kafka-server2, mongodb

       in local run, add into host.conf: 
        127.0.0.1 zookeeper-server
        127.0.0.1 kafka-server1   

# create topic for local

 create topic customer-events for application local
    
         ./bin/kafka-topics.sh --bootstrap-server kafka-server1:9092 --create --topic customer-events --partitions 1 --replication-factor 1

# create topic for docker

      ./bin/kafka-topics.sh --bootstrap-server kafka-server1:9092 --create --topic customer-events --partitions 2 --replication-factor 2

# commands

    -- validate topic created:

        ./bin/kafka-topics.sh --list --bootstrap-server kafka-server1:29092
      
    --delete topic
    ./bin/kafka-topics.sh --bootstrap-server kafka-server1:9092 --delete --topic customerDto-events

    -- publish
     ./bin/kafka-console-producer.sh --topic customerDto-events --bootstrap-server kafka-server1:9092
    --subcribe to topic
    ./bin/kafka-console-consumer.sh --topic customerDto-events  --bootstrap-server kafka-server1:9092


# endpoint get customers

 GET http://localhost:8080/api/v1/customer




