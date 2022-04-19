DROP TABLE IF EXISTS Notes, NOTE_GROUP;

CREATE TABLE IF NOT EXISTS NOTE_GROUP(id int AUTO_INCREMENT, label VARCHAR(255), PRIMARY KEY(id));
CREATE TABLE IF NOT EXISTS Notes(id int AUTO_INCREMENT, groupid int, header VARCHAR(255), contents VARCHAR(255), PRIMARY KEY(id), FOREIGN KEY(groupid) REFERENCES Note_Group(id));

INSERT INTO NOTE_GROUP(label) VALUES ('TEST');
INSERT INTO Notes(groupid, header, contents) VALUES (1, 'Test Entry', 'This is an example of a Note.\n\nIt should even include new lines!!');