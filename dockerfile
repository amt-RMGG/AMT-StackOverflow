FROM openliberty/open-liberty:kernel-java11-openj9-ubi

# Add webapp and config
COPY --chown=1001:0  config/server.xml /config/
COPY --chown=1001:0  config/*.jar /config/
COPY --chown=1001:0  stackunderflow.war /config/apps/


#RUN configure.sh