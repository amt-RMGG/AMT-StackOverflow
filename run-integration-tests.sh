mvn clean package
mvn liberty:create liberty:install-feature liberty:deploy
mvn liberty:start

mvn verify
