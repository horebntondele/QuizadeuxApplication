Java SDK Portal API 
-------------------

1. Dependencies
	gson - https://github.com/google/gson
	Apache Log4j Core - https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
	Apache Log4j API - https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
	Apache Log4j SLF4j Binding - https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl
	Log4j Implemented Over SLF4J - https://mvnrepository.com/artifact/org.slf4j/log4j-over-slf4j
	
2. Installation
	To install library it is preferable to use a build tool like Maven or similar. This will also resolve the above dependencies.
	
	1. Install maven from https://maven.apache.org/
	2. Import jar to maven: mvn install:install-file -Dfile=java-api-1.0.jar -DpomFile=java-api-1.0.pom -Djavadoc=java-api-1.0-javadoc.jar