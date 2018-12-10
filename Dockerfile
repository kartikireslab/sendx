FROM java:8  
#RUN chmod 777 /usr/share/tomcat8/.jenkins/workspace/Sendx/*
WORKDIR /usr/share/tomcat8/.jenkins/workspace/Sendx/target
#RUN chmod 775 sendx-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
