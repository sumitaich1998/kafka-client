## Title
Kafka Producer Consumer Example

## Description of the project
Simple app to demonstrate the Kafka Producer and Consumer client library.

## Dependencies:
* JDK 8
* Gradle

## Running the app

```sh
# Clone the repo
$ git clone https://github.com/sumitaich1998/kafka-client

# Build the jar
$ ./gradlew clean build 

# Run the Kafka Client app
$ ./gradlew run --args='<producer/consumer> <properties file path>' 

# To Run the Producer Client with default properties
$ ./gradlew run --args='producer producer.properties' 

# To Run the Consumer Client with default properties
$ ./gradlew run --args='consumer consumer.properties' 

```

## Running tests
```sh
# Running unit tests
$ ./gradlew test
```

## Contributors
* [Sumit Aich](https://github.com/sumitaich1998)