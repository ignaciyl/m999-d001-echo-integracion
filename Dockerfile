# Build stage
FROM bin.alm01.itbatera.euskadi.eus:5000/01m01-ambulancias/01m01-maven-3.6.3-jdk-11:latest AS build
ENV JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
COPY echoIntegracion /home/app/src/echoIntegracion
RUN rm /home/app/src/echoIntegracion/src/main/resources/*
RUN mvn -f /home/app/src/echoIntegracion/pom.xml clean install -DskipTests

# Package stage
FROM tomcat:9.0.65-jre11
ENV TZ="Europe/Madrid"
COPY --from=build /home/app/src/echoIntegracion/target/echoIntegracion.war /usr/local/tomcat/webapps
