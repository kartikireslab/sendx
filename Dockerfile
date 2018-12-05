#<<<<<<< HEAD
#FROM java:8
#WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
#RUN chmod 775 /home/start.sh
#CMD ["/home/start.sh"]
#=======
FROM java:8
WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
#RUN chmod 777 middle.sh
#RUN chmod +x middle.sh
#CMD ["middle.sh"]
#>>>>>>> 1a6ab6aedd85ac8e97efb7a3d697a3dff74aa709
#EXPOSE 8080

RUN apt-get update && apt-get -y install sudo
RUN pwd
RUN ls -l
RUN sudo su - ireslab04 <<! >/dev/null 2>&1
RUN sudo chmod -R 777 .
RUN ls -l
CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
#<<<<<<< HEAD





#FROM java:8
#RUN pip install Flask==0.11.1 
#RUN useradd -ms /bin/bash admin
#WORKDIR /usr/share/tomcat8/.jenkins/workspace/sendx/target/
#RUN chown -R root:root sendx-0.0.1-SNAPSHOT
#RUN chmod 755 sendx-0.0.1-SNAPSHOT
#USER root
#CMD ["java","-jar","sendx-0.0.1-SNAPSHOT.jar"]
#=======
#>>>>>>> 1a6ab6aedd85ac8e97efb7a3d697a3dff74aa709
