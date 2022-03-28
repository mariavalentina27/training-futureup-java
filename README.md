# future-up-java
# Prerequisites
Make sure you installed the following programs:

IntelliJ IDEA - https://www.jetbrains.com/idea/download/#section=windows

JDK 11 -  https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html

Postman - https://www.postman.com/downloads/

#How to start

After you installed everything clone this project and open Intellij Idea.

Switch the branch to iteration/1.

In IntelliJ go to project structure (File -> Project Structure).

In the project tab -> Project SDK select JDK 11. If it is not in the list, press on Add -> SDK... and select the path where you installed JDK 11. Should be a folder that contains "bin", "lib" and other folders.

After choosing JDK 11, wait for application to get indexed.

Run the FutureUpApplication configuration.

You can access the  h2 database on url: http://localhost:8080/h2-console.
In the web interfact change the JDBC URL in jdbc:h2:mem:futureup to be like your application config spring.datasource.url.

