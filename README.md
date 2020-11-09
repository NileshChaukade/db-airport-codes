# db-airport-codes

This is a task to create APIS that in a way proxy the below APIs provided by www.air-port-codes.com
1. https://www.air-port-codes.com/api/v1/single?iata=LON
2. https://www.air-port-codes.com/api/v1/countries?field_name=name
3. https://www.air-port-codes.com/api/v1/states?country=IN

These APIs need an AUTH_KEY and an AUTH_SECRET. These creds will be managed by this system and passed on on behalf of the user.
The end user of db-airport-codes application would not need to pass any authentication for accessing the above APIs which are abstracted by this system.

This tasks demands no other business logic apart from acting as a pass-through application for the above APIs. However considering future enhancements we shall develop it with that consideration. The credentials are as of now mainteined as application properties. However it would be more adequate to maintain the samein a secure system like Vault. It it out of scope for this assignment.

### Unit Testing
Since as of now not much business logic is involved this assignment will start with minimal unit test cases.
SpockFramework would be used for implementing the same

### Swagger UI
The abstracted APIs would be accessible via Swagger-ui.