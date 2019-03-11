## Needed stacks
    + maven 3
    + java8
    + postgresql
    
## Needed stacks
    + maven 3
    + java8
    + postgresql
    + jwt
    
## To get started follow this checklist:
    + install : brew install postgresql
    + start postgre : pg_ctl -D /usr/local/var/postgres -l logfile start
    + run cli : psql -U israjhaliri -d postgres
    + create database : CREATE DATABASE footnote;
    + import dummy from sql type text plain: psql -U israjhaliri -d footnote -a -f footnote.sql
    + build project : mvn clean install
    + run project in target: java -jar app-service/target/app-service-1.0-SNAPSHOT.jar
    + open at http://localhost:9393/
    
    
## Common Usage
### regenerate postgre :
    + rm -rf /usr/local/var/postgres
    + initdb /usr/local/var/postgres -E utf8