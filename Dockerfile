FROM java:8  
RUN chmod 777 -R /usr/share/tomcat8/.jenkins/workspace/Sendx/target
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
RUN chmod 777 -R sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
