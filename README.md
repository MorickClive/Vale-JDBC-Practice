# Vale-JDBC-Practice

The objective of this project is to create a Java application that can take user input and store it into a persistence layer.

The application will allow users to display all stored people, add, update and delete.
The database will have a simple table that stores a list of people.

Review commit tree here: [commit tree](https://github.com/MorickClive/Vale-JDBC-Practice/network)

## Person System:
	
The objective with the Person System is to take user input, storing the following values:
	- first name
	- last name
	- age
	
This system allows users to create, read, update and delete stored entries in a singular SQL database table.

## Note Tracker

The objective for the "Note Tracker" system is to store notes that belong to groups in a similar fashion to folder-file relationship.

This system expresses one-to-many relationships from a java-sql interaction.

This database is built around an in memory database:
`URL=jdbc:h2:mem:devdb`

Connection is closed when application is closed, this is why DBAs only close ResultSets and Statements.

```
Note groups should store the following:
	- Label - should generalise the purpose of notes stored within

Notes should store the following:
	- Header - To summarise the contents
	- Contents - To store all information the note contains
```

Objective List:

- [X] Create a UI menu system
	- [X] User must be able to create a Person Object.
	- [X] Menu must loop until User exits application.

---

- [X] Person System
	- [X] Store and Retrieve Person data from database
		- [X] CREATE
		- [X] READ
		- [X] UPDATE
		- [X] DELETE

---

- [X] Plan Note Tracker System
	- [X] Create ERD for Note Tracker
- [X] Implement Note Tracker System
	- [X] Add Schema for NoteTracker
	- [X] Implement POJOs
		- [X] NoteGroups Model
		- [X] Note Model
	- [X] Implement Controllers
		- [X] NoteGroups - CRUD functionality
		- [X] Notes - CRUD functionality
	- [X] Implement UI Sub-Menu for NoteTracker
	
---

## [Project Vale: Overview](https://github.com/MorickClive/Project-Vale/blob/main/README.md#project-vale)
