FROM java:8  
RUN chmod a+x /usr/share/tomcat8/.jenkins/workspace/Sendx/target/
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
