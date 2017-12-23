FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD rk-cloud-centralServer-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]