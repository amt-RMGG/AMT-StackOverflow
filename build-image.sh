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



