FROM java:8  
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
