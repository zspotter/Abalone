Abalone
=======

Implementation of the marble board game Abalone for RIT's Data Communication & Networks I course.

Authors:
 + Zachary Potter
 + Channon Price

Compiling
---------
Enter the Abalone root directory.

**Compile the source files:**
```
mkdir bin
javac -d bin src/edu/rit/datacom/abalone/client/* src/edu/rit/datacom/abalone/common/* src/edu/rit/datacom/abalone/server/*
```
**Create the JAR files:**

Client: 
```
jar cfm AbaloneClient.jar client.mf -C bin edu/rit/datacom/abalone/client -C bin edu/rit/datacom/abalone/common
```
Server: 
```
jar cfm AbaloneServer.jar server.mf -C bin edu/rit/datacom/abalone/server -C bin edu/rit/datacom/abalone/common
```
Running
-------

Client:
```
java -jar AbaloneClient.jar <host> <port> <game-name>
```
Server:
```
java -jar AbaloneServer.jar <host> <port>
```