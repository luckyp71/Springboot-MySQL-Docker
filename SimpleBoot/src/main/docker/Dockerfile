FROM java:8
 
MAINTAINER Lucky Pratama
 
ADD SimpleBoot-0.0.1-SNAPSHOT.war /app.jar
 
EXPOSE 8090
 
#RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]