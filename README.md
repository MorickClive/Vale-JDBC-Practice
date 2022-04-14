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
- [ ] Implement Note Tracker System
	- [ ] Add Schema for NoteTracker
	- [ ] Implement POJOs
		- [ ] NoteGroups Model
		- [ ] Note Model
	- [ ] Implement Controllers
		- [ ] NoteGroups - CRUD functionality
		- [ ] Notes - CRUD functionality
	- [ ] Implement UI Sub-Menu for NoteTracker
	
---

## [Project Vale: Overview](https://github.com/MorickClive/Project-Vale/blob/main/README.md#project-vale)
