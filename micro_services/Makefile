build:
	@echo "--- build ---"
	mvn -f ./ident/pom.xml clean install
	mvn -f ./logic/pom.xml clean install
	mvn -f ./datalayer/pom.xml clean install

run:
	@echo "--- run ---"
	java -jar ./ident/target/ident-1.0-SNAPSHOT.jar server ./ident/config.yml &
	java -jar ./datalayer/target/datalayer-1.0-SNAPSHOT.jar server ./datalayer/config.yml &
	java -jar ./logic/target/logic-1.0-SNAPSHOT.jar server ./logic/config.yml &
