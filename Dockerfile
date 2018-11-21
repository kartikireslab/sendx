
FROM java:8  
COPY / /
WORKDIR /
CMD ["java","-jar","chmod 777 /usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.jar"]



