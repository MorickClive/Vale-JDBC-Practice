DROP TABLE IF EXISTS Person;

CREATE TABLE IF NOT EXISTS Person(id int AUTO_INCREMENT, forename varchar(255), surname varchar(255), age int, PRIMARY KEY(id));

INSERT INTO Person(forename, surname, age) VALUES ('Caspian', 'Lovett' , 27);
INSERT INTO Person(forename, surname, age) VALUES ('Morick', 'Clive' , 30);
INSERT INTO Person(forename, surname, age) VALUES ('Safiya' ,'Founder' , 18);
INSERT INTO Person(forename, surname, age) VALUES ('Zone' ,'Clive' , 19);