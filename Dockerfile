FROM java:8  
RUN sudo chmod 777 /usr/share/tomcat8/.jenkins/workspace/Sendx/target
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
RUN sudo chmod 775 sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
