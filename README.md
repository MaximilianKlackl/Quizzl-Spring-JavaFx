# Quizzl-Spring-JavaFx

## Descripton

Quizzl is a Single User Desktop Application with a local SQLite Database designed for Learning with Flashcards. 

### Features 

- create and manage Flashcard
- export/ import Flashcards to CSV Format
- create Learn-Sessions 
- different settings for Learn-Sessions
- view Statistics

## To-Do's

- refactor code to components to reduce code duplication
- [x] create Folder Structure
- [x] create Entities + attributes
- [x] refactor Entity inheritance
- [x] implement Entity associations
- [x] implement Repositories
- [x] test CRUD Operations
- [x] create CSV Utility Class
    - [x] Import
    - [x] Export
    - [x] fix Saving to DB
- [x] implement Manage Flashcard Page
- [ ] implement Learn Session Page 

## Technologies

- JavaFx
- Spring Boot
- SQLite
- JPA
- Lombok

## Folder Structure
```
Quizzl
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── quizzl
│   │   │           └── app
│   │   │               ├── configuration
│   │   │               │   └── SQLDialect.java
│   │   │               ├── controller
│   │   │               ├── error
│   │   │               ├── model
│   │   │               ├── repository
│   │   │               ├── util
│   │   │               ├── service 
│   │   │               ├── BootifulFxApplication.java
│   │   │               ├── JavafxApplication.java
│   │   │               └── StageListener.java
│   │   └── resources
│   │       ├── META-INF
│   │       ├── view
│   │       ├── application.properties
│   │       └── quizzler.db
│   └── test
├── Quizzl.iml
├── README.md
├── pom.xml
```
 
