# spring-cloud-config-sandbox

On research of another topic I stumbled across Spring Cloud Config and decided to have a look at it. It can be useful in several situations and environments. I wanted to learn how exactly it works and where its pros and cons are to use it in future projects. So I created this sandbox project.

Do not use this configuration in production since its completely configured for learning purposes.

## How this sandbox works

I've created only 2 services to keep it as simple as possible.

### config-server

The config-server is a central place where the configuration of the whole application is managed. For this project I've used git to store the configuration files on github. They can be found in the `config` directory of this repository. But its also possible to use another configuration storage. (e.g. a database)

The configuration I've used enables also the possibility to push configuration changes to the available services. This is done by Spring Cloud Bus and RabbitMQ as message broker.
Use the following x-www-form-urlencoded POST request to trigger the configuration change broadcast:

```
POST http://localhost:8888/monitor
path=text-service
```

With path=text-service only the text-service gets the command to refresh its configuration. With the use of `path=*`, any service gets the command to refresh its configuration. 

A usual way to make use of this is to trigger this POST request by an event. This can be a git push event (e.g. github actions) if you use git as storage backend. 

### text-service

The text-service just show a text on `http://localhost:8080`. This text is loaded from the configuration via the Spring Cloud Config Server. If text couldn't be loaded "DEFAULTTEXT" is shown.

## Usage

1. Start RabbitMQ `docker run -d --hostname rabbitmq -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:management`
2. `cd` into `config-server` and run it with `mvn spring-boot:run`
3. `cd` into `text-service` and run it with `mvn spring-boot:run`
4. Open `http://localhost:8080` to see the current configured text

The `config` dir of this repo is used by default to get the configuration. You can configure your own repo in the config-server and run the mentioned POST request to trigger the config update. You don't have to restart any server to refresh the configuration.

Its also possible to see what happens behind the scenes by using the RabbitMQ manager on http://localhost:15672/