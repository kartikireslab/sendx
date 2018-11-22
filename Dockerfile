FROM java:8
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
RUN chmod 775 /home/start.sh
CMD ["/home/start.sh"]
EXPOSE 8080

#RUN apt-get update && apt-get -y install sudo
#RUN pwd
#RUN ls -l
#RUN sudo su - ireslab04 <<! >/dev/null 2>&1
#RUN sudo chmod -R 777 .
#RUN ls -l
#CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
