# StackUnderFlow

## Workflow with OpenLiberty

### Working in dev mode
```mvn liberty:dev``` </br>
This command will generate and launch the .war file and let you access to the application in local. When the process is running, you can modify the code and refresh the page to see modifications. In order to use the database-related features, you will need to configure your personal database in an environnemnt file at `src/main/liberty/config/server.env` , and ensure that the specified database is up and running. An example of this file is given at `src/main/liberty/config/server.env.example`. All you need to do is fill in your values and delete the `.example` extension.

### Generate the .war file (for docker use)
```mvn clean package``` </br>
This command will be used in our docker deployement. It generate the 'target' folder with the .war file in it.
#### Nota bene
You can also generate a .jar file with the application + the application server, by uncomment the "\<executions\>" lines in the pom.xml file. Once you have the .jar file, you can run it with ```java -jar <file>.jar``` 

### Docker deployement
To deploy the solution with docker, you will first need to build the webapp image using the `build-image.sh` script, found at the root of the project. Then, use the ```run-image.sh``` script to launch the webapp and the database. Alternatively, you can go to `docker/topologies/with_db` and run a ```docker-compose up```.


#### Run images based on the current release from the Github Conainer Registry

In the root directory, execute the bash file `run-release.sh`.  
This will pull the latest image from the registry and run it paired with a mysql container hosting the database, all inside a topology.  


#### Run images based on the current state of the code

In the root directory, you can execute the bash file `build-image.sh`
```
#maven build
mvn clean package

#vérifie la présence du .war dans le dossier target
FILE=target/stackunderflow.war
if [ -f "$FILE" ]; then
    #copie le .war au même niveau que le dockerfile
    echo "$FILE found, transfering..."
    mv $FILE docker/images/openliberty
    echo "$FILE transfer finished"
    #construit l'image
    cd docker/images/openliberty
    docker build -t amt/stackunderflow .
else 
    >&2 echo "Error : $FILE not found"
    exit N
fi

```

It will create an docker image called `amt/stackunderflow` based on the official `openliberty/open-liberty:kernel-java11-openj9-ubi` image and include the .war application file.
Then you need run the `run-image.sh` file to run the container (which simply calls a `docker-compose up` in the `docker/topologies/with_db` folder.) <br/>
You can specify the port mapping and the environment variables in the `docker/topologies/with_db/docker-compose.yml` file. <br/>
The default values are :
```
version: '3.7'
services:
  db_stackunderflow:
    image: mysql
    container_name: db_stackunderflow
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: amt_stackunderflow
      MYSQL_USER: user
      MYSQL_PASSWORD: pass
    ports:
      - "3306:3306"
    expose:
      - '3306'
    volumes:
      - ./initdb:/docker-entrypoint-initdb.d

  webapp_stackunderflow:
    image: amt/stackunderflow
    container_name: webapp_stackunderflow
    environment:
     - ADMIN_PASSWORD=admin
    ports:
     - "8080:8080"
     - "8443:8443"


```

#### Nota bene
In Linux the server can be access on `localhost:8080` , but don't forget that in Windows (with docker-machine), you'll need to access it from `http://192.168.99.100:8080/` !

### Database

The application is using an MySQL 8.0 database. <br/>
In the `liberty\config\` folder, you'll find the mysql connector jar file : `mysql-connector-java-8.0.21.jar` <br />
In the same folder, we specifie the database information in the `server.xml` file.
**You'll have to specifie the environement variables in the `server.env` (in the same folder)**
```
    ... 
    <library id="MySqlLib">
        <fileset dir="${server.config.dir}" includes="*.jar" />
    </library>

    <dataSource jndiName="jdbc/StackUnderFlowDS">
        <jdbcDriver libraryRef="MySqlLib"/>
        <properties
            databaseName="amt_stackunderflow"
                serverName="${DB_SERVER_HOSTNAME}"
                portnumber="${DB_PORT_NUMBER}"
                user="${DB_USER}"
                password="${DB_USER_PASSWORD}"
                serverTimezone = "UTC"
        />
    </dataSource>
 ```

At this point, you have two choice : 
- Running a local MySql server on your machine on `<adress>:<port>`
- Using a Docker Container, using MySql Server image from dockerhub.

If you want to deploy the application via the docker images, you'll need to use the docker-compose (down) command with our .yml file 
`topologie\with-db\initdb\docker-compose.yml` :
```
version: '3.7'
services:
  db_stackunderflow:
    image: mysql
    container_name: db_stackunderflow
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: amt_stackunderflow
      MYSQL_USER: stackunderflow_user
      MYSQL_PASSWORD: stackunderflow_secret
    ports:
      - "3306:3306"
    expose:
      - '3306'
    volumes:
      - ./initdb:/docker-entrypoint-initdb.d

  webapp_stackunderflow:
    image: amt/stackunderflow
    container_name: webapp_stackunderflow
    environment:
     - ADMIN_PASSWORD=admin
    ports:
     - "8080:8080"
     - "8443:8443"
```
