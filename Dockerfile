FROM java:8  
COPY / /
WORKDIR server/target
CMD ["java","-jar","/target/sendx-api-0.0.1-SNAPSHOT.war"]
