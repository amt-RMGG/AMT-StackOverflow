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

