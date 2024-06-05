# ScaArchitecture - Microservices

## How microservices communicate?
Basically there are Sync/Async/Single Receiver/Multiple Receiver, I used Sync which involves WebClient and OpenFeign, you can pick any, what happens is basically there should be a unique id to call the service, Like getDepartmentByCode() already in department service and then we can use webclient to call it using its host name and port.

## What if there are thousands of microservices, it is hectic task to find host name and port number?
This is where Service Registry comes into place achieved via Spring Netflix Eureka Server keeps track of each microservices and their instances, here we annotate every service as Eureka client so it gets registered as an ID.

## How will client access thousands of microservices, also what If one microservice gets down?
1. This is where API gateway comes in play. It routes the request coming from the client to the microservice, like if emp service is on port 8082, then instead of localhost:8082/employees/1, client can use api gateway as localhost:9191/employees/1. Here client can replace the employee/.. with the microservice we want to interact with.
2. Spring Netflix Eureka provides load balancing so if any service it down, the request will re route to the running instance.

## If a configuration change is made in a microservice, it needs to be restarted, what if there are thousands, will you restart everyone of it? 
 Here comes Config Server, basically we can create git repo and push the application.prop file in there and can setup the route in the microservice, this will help centralize all the microservice, plus we can use Spring Cloud Bus to achieve auto restart.

## How will we monitor the microservices, why/which microservice is taking longer to run/load ?
It can be achieved by distributed tracing, with the help of Sleuth we can trace each microservice. Also if we want to visualize it, we can use Zipkin. 

## What if one microservice is down completely with its every instance, it will be costly to just keep calling it ?
Here comes Circuit Breaker Pattern, basically we can set the threshold rate, if it fails more than it then the circuit breaker moves into open state where it will wait for x seconds then it will move to Half-Open state, there it will make limited number of calls , then it will decide whether to move to open or closed state.
