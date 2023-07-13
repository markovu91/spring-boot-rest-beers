
Spring Boot application using JPA and Hibernate implementation.


I've chosen these technologies because it has less configuration and it's easier to develop modern web applications and rest services. 

At the beginning of this assignment, my first step was to sketch the diagram of the application flow, so I could easily take a look at the bigger picture. 
I then broke the project into smaller parts and started to tackle them individually. The first task was to fetch the random beer from API using Rest Template, 
after the successful retrieval, I've used Jacksons object mapper to map the JSON file to a Java POJO. The mapping was a bit of a struggle since I've never used this 
kind of technology before. The problem arose when the API responded with a nested JSON file, so it was necessary to properly map the java object to a database using hibernate.

The reason I chose hibernate implementation is that it's easier to perform basic CRUD operations, unlike JDBC where you have to write much more code to achieve the same results.

After the successful completion of, what could be considered the first part of the project, I exposed basic REST endpoints to a user using Spring REST. 
It allows the user to perform tasks such as fetching all the beers from a database, deleting beer by id, and everything else that was required in the project briefing. 

For the final part of this application, I processed the Exceptions. Such as wrong beer id retrieval, using Spring AOP where it intercepts the false requests, 
and then throws an exception that was later handled by an Exception Handler(BeerRestExceptionHandler using @ControllerAdvice). 
It ensured that the application will not break and the user receives an HTTP Status like Not Found. After that, 
I tested the application using Postman (Rest Client) and Curl command from cmd.

If I had more time, I would probably use Spring Web Client for fetching the JSON data, because the Rest Template would probably become deprecated in the near future.
Given the oppurtunitie to start this application all over again, I would try to familiarise myself with more Spring concepts so that I could apply them in this project.
 
 

 To build application using maven cmd, navigate to the root of the project and execute the command: 

 mvn package

 To run application navigate to the target folder of the project via command line and execute the command

 java -jar challenge-0.0.1-SNAPSHOT.jar

 Fetching all beers in database:
 curl -v http://localhost:8080//listBeers

 Get one beer by id:
 curl -v http://localhost:8080//beers/{--insert beer id--}

 Delete beer by id:
 curl -X DELETE http://localhost:8080//beers/{--insert beer id--}

 Save beer
 curl -X POST -H "Content-Type: application/json"^
 -d "{--beer data to insert--}"^
 http://localhost:8080/beers

 Update beer
 curl -X PUT -H "Content-Type: application/json"^
 -d "{--beer data--}"^
 http://localhost:8080/beers/{--beer id for update--}