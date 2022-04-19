DROP TABLE IF EXISTS Notes, Note_Group;

CREATE TABLE IF NOT EXISTS Note_Group(id int AUTO_INCREMENT, label VARCHAR(255), PRIMARY KEY(id));
CREATE TABLE IF NOT EXISTS Notes(id int AUTO_INCREMENT, groupid int, header VARCHAR(255), contents VARCHAR(255), PRIMARY KEY(id), FOREIGN KEY(groupid) REFERENCES Note_Group(id));

INSERT INTO Note_Group(label) VALUES ('TEST');
INSERT INTO Notes(groupid, header, contents) VALUES (1, 'Test Entry', 'This is an example of a Note.\n\nIt should even include new lines!!');