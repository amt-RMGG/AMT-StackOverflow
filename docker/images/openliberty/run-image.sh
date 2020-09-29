#legacy way of starting the container
#docker run -p 8080:8080 -p 4848:4848 amt/stackoverclone

#better way of starting the container
cd ../../topologies/openliberty
docker-compose up