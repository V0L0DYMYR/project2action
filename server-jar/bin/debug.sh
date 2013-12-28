
java  -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 ../target/project2action-1.0-SNAPSHOT.jar server ../src/main/resources/conf/project2action.json