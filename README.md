# N26 Code Challenge Solution



## Strategy
With the help of Spring Boot and Rest Controller the requests will be served to the implementation of the TransactionService.
The requests will be validated, and the corresponding responses will be created.
For creating Response, with the help of ***@ResponseStatus**** and ***@ControllerAdvice*** the responses in case of failure will be created.
Service layer is only responsible for validation and putting some logs, and saving transactions, remove, and calculating statistics.
For achieving thread safety with the help of ***ConcurrentHashMap*** and ***synchronized*** key word, we can sure the service is thread safe.

## Concerns
The main concerns are : writing clean code based on SOLID principles with the function testable.
For achieving that the service is defined in the interface, and interface and implementation has been separated.
With the help of JUnit 5, unit tests and integration tests have been done.
After running the tests with the ***code coverage***, the classes,methods, and lines covered more than 90 percents.



