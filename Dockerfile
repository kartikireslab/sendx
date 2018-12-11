FROM java:8  
ENTRYPOINT chmod 777 /usr/share/tomcat8/.jenkins/workspace/Sendx/target /usr/share/tomcat8/.jenkins/workspace/Sendx/target/
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
