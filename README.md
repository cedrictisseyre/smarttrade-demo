# SmartTrade demo


### Business content
A very small project to demo a project with overload on Bean and interface to multiple trading plateformes.

It has a single endpoint to /demo in GET to generate a random BuyOrder toward BinanceSdp or CoinbaseSdp depending on a strategy.
The core implementation has a callback url to notify order completion.
The metalor implementation overload the core implementation and provides a callback by sms instead.

### Project structure
Some comments have been added directly into the codebase to facilitate exploration.

#### application
Contains only the main and a demo controller to interact with the librairies

#### core
A lib for all clients and contains here just a CoreBuyOrderPlacer to place buy order

#### metalor
A lib specific for metalor client.
It has been added to show how customization can be made for a specific client.
Here a very basic customization in the callback mecanism has been made.

### Prerequisites
- SDKman: https://sdkman.io/install
- Java 17:
```shell
sdk install java 17.0.2-tem
sdk use java 17.0.2-tem
java --version
```

- Gradle: https://gradle.org/install/
- Docker: https://docs.docker.com/engine/install/


### How to run

- Start the local database
```shell
docker compose up
```

- Run project with the IntelliJ the target `SmarttradeDemoApplication [local]`

- The demo endpoint is here: http://localhost:8080/demo
- API documentation is here: http://localhost:8080/swagger-ui.html

- Look at the logs after running demo endpoint
