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



