
## Version 6
- By now we would have successfully created our two microservices,
    - [multiplication microservice](./multiplication_service/)
    - [gamification microservice](./gamification_service/)
- And we have employed Event-Driven Strategies to get the two services to communication by sending event using a Topic Exchange, Producer-Subscriber Pattern.
- But, our application's UI is tightly coupled to the multiplication microservice which defeats the purpose of the Microservice Architecture so we will extract the UI from that service and make it send alone, How?
- We will install a web server(this book used [Jetty](https://jetty.org/docs/jetty/12/operations-guide/begin/index.html#download) so we do too but we can also use Tomcat or Nginx)
- We simply add a new folder called [ui](./ui/), which will serve as the base directory for Jetty to use as configuration layer, separated from the binary files you installed in `/opt`
- Within