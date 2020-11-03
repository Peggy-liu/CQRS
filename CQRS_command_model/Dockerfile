#multi-stage build
####REMEMBER All the docker keyword need to be followed by a blank space
FROM maven:3.6.3-jdk-11-slim AS MAVEN_BUILD
COPY ./lib ./lib
#install jar file to local m2 repository in the container
RUN mvn install:install-file \
-Dfile=$PWD/lib/CQRS_common_thing-0.0.1-SNAPSHOT.jar \
-DgroupId=com.cqrs.example \
-DartifactId=CQRS_common_thing \
-Dversion=0.0.1-SNAPSHOT \
-Dpackaging=jar
COPY ./pom.xml .
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

FROM openjdk:11-jre-slim
COPY --from=MAVEN_BUILD /target/CQRS_command_model-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "CQRS_command_model-0.0.1-SNAPSHOT.jar" ]