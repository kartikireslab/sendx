
FROM java:8  
COPY / /
WORKDIR /
RUN cd /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN chmod 777 *
CMD ["java","-jar","/usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.jar"]



