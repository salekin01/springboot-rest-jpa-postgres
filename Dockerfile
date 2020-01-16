FROM openjdk:11
WORKDIR /var/lib/docker/
COPY /target/dspringboot.jar  .
#EXPOSE 8080
ENTRYPOINT ["java","-jar","dspringboot.jar", "http://0.0.0.0:8080"]
#CMD ["-start"]

