#maven build
cd ../../..
mvn clean install

#vérifie la présence du .war dans le dossier target
FILE=target/LandingPageMVCApp-1.0-SNAPSHOT.war
if [ -f "$FILE" ]; then
    echo "$FILE found, copying..."
else 
    >&2 echo "Error : $FILE not found"
    exit N
fi

#copie le .war au même niveau que le dockerfile
cp $FILE .

#construit l'image
docker build -t amt/stackoverclone .