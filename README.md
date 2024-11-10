
## Version 6
- By now we would have successfully created our two microservices,
    - [multiplication microservice](./multiplication_service/)
    - [gamification microservice](./gamification_service/)
- And we have employed Event-Driven Strategies to get the two services to communication by sending event using a Topic Exchange, Producer-Subscriber Pattern.
- But, our application's UI is tightly coupled to the multiplication microservice which defeats the purpose of the Microservice Architecture so we will extract the UI from that service and make it send alone, How?
- We will install a web server(this book used [Jetty 11](https://jetty.org/download.html) so we do too but we can also use Tomcat or Nginx)
- We simply add a new folder called [ui](./ui/), which will serve as the base directory for Jetty to use as configuration layer, separated from the binary files you installed in `/opt`
- We do this by calling on this command:
```
    java -jar $JETTY_HOME/start.jar --add-modules=server,http,deploy
```
- Which creates 3 folders:
    - resources
    - start.d
    - webapps
- The `webapps` folder is where we place all our static files(html,css,js) or our *.war/*.jar files. We go to multiplication microservice and copy the static ui files from `/resources` folder to `/webapps/ui` folder.