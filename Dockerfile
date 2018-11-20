
FROM java:8  
COPY /target/sendx-api-0.0.1-SNAPSHOT.war /target/sendx-api-0.0.1-SNAPSHOT.war
WORKDIR server/target
CMD ["java","-jar","/target/sendx-api-0.0.1-SNAPSHOT.war"]