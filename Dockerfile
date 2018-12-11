FROM java:8  
RUN chmod 777 -R /usr/share/tomcat8/.jenkins/workspace/Sendx/target /usr/share/tomcat8/.jenkins/workspace/Sendx/target/sendx-0.0.1-SNAPSHOT.jar
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
