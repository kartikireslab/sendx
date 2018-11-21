
FROM java:8  
COPY /usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.jar /usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.jar
WORKDIR /
CMD ["java","-jar","/usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.jar"]



