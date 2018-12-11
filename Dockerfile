FROM java:8  
RUN chmod r+x /usr/share/tomcat8/.jenkins/workspace/Sendx/target
CMD ["java","-jar","/usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar"]
