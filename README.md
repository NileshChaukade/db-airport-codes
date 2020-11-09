# db-airport-codes

This is a task to create APIs that in a way proxy the below APIs provided by www.air-port-codes.com
1. https://www.air-port-codes.com/api/v1/single?iata=LON
2. https://www.air-port-codes.com/api/v1/countries?field_name=name
3. https://www.air-port-codes.com/api/v1/states?country=IN

These APIs need an AUTH_KEY and an AUTH_SECRET. These credentials will be managed by this system and passed on behalf of the user.
The end user of db-airport-codes application would not need to pass any authentication for accessing the above APIs which are abstracted by this system.
In the future, we would consider moving the credentials to [Vault by HashiCorp] or at least to [Spring Cloud Config Server] or [Consul]

This task demands no other business logic, as of now, apart from acting as a pass-through application for the above APIs. However, considering future enhancements we shall develop it with that consideration. The credentials are as of now mainteined as application properties. However it would be more adequate to maintain the samein a secure system like Vault. It it out of scope for this assignment.
We could have used [Netflix ZUUL] Routes for implementing on pass through.

### Unit Testing
Since as of now the APIs do not have much of business logic, will start with minimal unit test cases.
[SpockFramework] would be used for implementing the same.

### Swagger UI
The abstracted APIs would be accessible via [Swagger-ui].

### Tracing
This system provides a request tracing facility by adding a trace-id to the logs for all the requests.
It also implements AOP based logging to logs control-flow-statements for Controller, Service and DAO classes, when set at debug level. This property is available in application.yml

 

   [SpockFramework]: <http://spockframework.org/>
   [Swagger-ui]: <https://swagger.io/tools/swagger-ui/>
   [Netflix ZUUL]: <https://github.com/Netflix/zuul>
   [Vault by HashiCorp]: <https://www.vaultproject.io/>
   [Spring Cloud Config Server]: <https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html>
   [Consul]: <https://www.consul.io/>