
FROM java:8  
COPY    
WORKDIR server/target
CMD ["java","-jar","/usr/share/tomcat8/.jenkins/workspace/sendx/target/sendx-0.0.1-SNAPSHOT.war"]


