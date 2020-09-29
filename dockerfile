FROM openliberty/open-liberty:kernel-java11-openj9-ubi

# Add webapp and config
COPY --chown=1001:0  stackunderflow.war /config/dropins/
COPY --chown=1001:0  src/main/liberty/config/server.xml /config

#RUN configure.sh