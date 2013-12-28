
java  -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 ../target/in-queue-1.0-SNAPSHOT.jar server ../src/main/resources/conf/in-queue.json