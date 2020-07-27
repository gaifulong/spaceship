FROM java:11
VOLUME /tmp
ADD ranger.jar ranger-image.jar
RUN bash -c 'touch /ranger-image.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","ranger-image.jar"]