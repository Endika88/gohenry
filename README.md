## Synopsis

This projct provides some RESTFul API services to create and update panrets and childrens entities stored in a H2 database


## Installation

The application can be started by running the following command:
        
mvn spring-boot:run

## API Reference

The application provide the following 4 services:

CREATE PARENT: (Childrens are optional)

curl -d '{"name": "parentName","surname": "parentSurname","age" : 32 , "childrens": [{"name": "childName1","surname": "childSurname1","age" : 15 },{"name": "childName2","surname": "childSurname2","age" : 16 }]}' -H "Content-Type: application/json" -X POST http://127.0.0.1:8080/parents

GET PARENTS

curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://127.0.0.1:8080/parent/1

UPDATE PARENT

curl -X PUT -d '{"name": "parentNameUpdated","surname": "parentSurnameUpdated","age" : 32 }' -H "Content-Type: application/json"  http://127.0.0.1:8080/parents/1

UPDATE CHILDREN

curl -X PUT -d '{"name": "childNameUpdated","surname": "childSurnameUpdated","age" : 21 }' -H "Content-Type: application/json"  http://127.0.0.1:8080/children/1

The database can be accessed by login into --> http://127.0.0.1:8080/h2-console

JDBC Url : jdbc:h2:mem:testdb

User: sa

Password: blank

## Tests

Tests can be executed by running mvn clean test command

## Contributors

Endika Larrea


