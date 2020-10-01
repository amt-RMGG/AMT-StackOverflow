# StackUnderFlow

## Workflow with OpenLiberty

### Working in dev mode
```mvn liberty:dev``` </br>
This command will generate and launch the .war file and let you access to the application in local. When the process is running, you can modify the code and refresh the page to see modifications.

### Generate the .war file (for docker use)
```mvn clean package``` </br>
This command will be used in our docker deployement. It generate the 'target' folder with the .war file in it.
#### Nota bene
You can also generate a .jar file with the application + the application server, by uncomment the "\<executions\>" lines in the pom.xml file. Once you have the .jar file, you can run it with ```java -jar <file>.jar``` 

### Docker deployement

#### Docker-compose

In the root directory, you can execute the bash file `build-image.sh`
```
#maven build
mvn clean package

#vérifie la présence du .war dans le dossier target
FILE=target/stackunderflow.war
if [ -f "$FILE" ]; then
    #copie le .war au même niveau que le dockerfile
    echo "$FILE found, copying..."
    cp $FILE .
    #construit l'image
    docker build -t amt/stackunderflow .
else 
    >&2 echo "Error : $FILE not found"
    exit N
fi
```

It will create an docker image called `amt/stackunderflow` based on the official `openliberty/open-liberty:kernel-java11-openj9-ubi` image and include the .war application file.
Then you need run the `run-image.sh` file to run the container (who simply call a `docker-compose` in the toplogies/ folder.) <br/>
You can specify the port mapping and the environment variables in the `topologies/docker-compose.yml` file. <br/>
The default values are :
```
version: '3'
services:
  openliberty:
    image: amt/stackunderflow
    environment:
     - ADMIN_PASSWORD=<your password>
    ports:
     - "8080:8080"
     - "8443:8443"
```

#### Nota bene
In Linux the server can be access on `localhost:8080` , but don't forget that in Windows (with docker-machine), you'll need to access it from `http://192.168.99.100:8080/` !


