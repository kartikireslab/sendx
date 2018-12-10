FROM java:8  
COPY / /
WORKDIR server/target
RUN chmod 775 sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/target/sendx-0.0.1-SNAPSHOT.jar"]
