compile:
	mvn compile

test: clean compile
	mvn test

clean:
	mvn clean
