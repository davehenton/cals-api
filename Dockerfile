FROM cwds/javajdk
RUN mkdir /opt/cals-api
RUN mkdir /opt/cals-api/logs
ADD config/cals-api.yml /opt/cals-api/cals-api.yml
#ADD config/shiro.ini /opt/cals-api/config/shiro.ini
ADD build/libs/cals-api-dist.jar /opt/cals-api/cals-api.jar
EXPOSE 8080
WORKDIR /opt/cals-api
CMD ["java", "-jar", "cals-api.jar","server","cals-api.yml"]