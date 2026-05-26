**Bug Tracker Console Application**

A console-based bug tracking system developed as a Day 6 integrated assessment for Java Backend Training. The application demonstrates core Java concepts including Object-Oriented Programming, custom exception handling, the DAO design pattern, and JDBC connectivity with PostgreSQL — all unified into a single production-style project.



**Table of Contents**

\- Overview

\- Tech Stack

\- Project Structure

\- Features

\- Database Setup

\- Configuration

\- How to Run

\- Exception Handling

\- Concepts Demonstrated



**Overview**

The Bug Tracker is a menu-driven Java console application that allows users to create, view, update, and delete bugs and tasks. It maintains an in-memory list of issues using a service layer and persists bug data to a PostgreSQL database using the DAO (Data Access Object) pattern. All validation errors are caught gracefully and logged to an external `error.log` file.



**Tech Stack**

| Technology   | Purpose                            |

|--------------|------------------------------------|

| Java 17      | Core application language          |

| PostgreSQL   | Relational database                |

| JDBC         | Database connectivity              |

| Maven        | Dependency management \& build tool |





**Project Structure**

src/main/java/com/day6ass/

│

├── App.java                           

│

├── models/

│   ├── Issue.java                    

│   ├── Bug.java                       

│   ├── Task.java                      

│   └── User.java                      

│

├── exceptions/

│   ├── EmptyTitleException.java       

│   └── InvalidSeverityException.java  

│

├── services/

│   └── IssueService.java              

│

├── dao/

│   └── BugDAO.java                    

│

└── util/

└── DBUtil.java                    



**Features**

\- Add a bug with title, assignee, and severity validation

\- Add a task with title, assignee, and estimated hours

\- View all issues — in-memory list and live database fetch combined

\- Update a bug's status in the database by ID

\- Delete a bug from the database by ID

\- Input validation with descriptive error messages

\- All exceptions automatically logged to `error.log` with timestamp



**Database Setup**

Connect to PostgreSQL and execute the following:

sql

CREATE DATABASE bugtracker;

bugtracker



CREATE TABLE bugs (

&#x20;   id          SERIAL PRIMARY KEY,

&#x20;   title       VARCHAR(100),

&#x20;   severity    VARCHAR(50),

&#x20;   assigned\_to VARCHAR(100),

&#x20;   status      VARCHAR(50)

);



**How to Run**

Step 1 — Clone the repository

bash

git clone https://github.com/achu08sathia/Day6-Assignment-Integrated-Bug-Tracker-Console-App.git

cd Day6-Assignment-Integrated-Bug-Tracker-Console-App

Step 2 — Compile the project

bash

mvn compile

Step 3 — Run the application

bash

mvn exec:java -Dexec.mainClass="com.day6ass.App"

Step 4 — Interact with the menu

===== Bug Tracker Menu =====

Add Bug

Add Task

View All Issues

Update Bug Status

Delete Bug

Exit

Choose:



**Exception Handling**

All exceptions are handled at the service layer and logged to error.log at the project root.

| Scenario                          | Exception                    | Logged |

|-----------------------------------|------------------------------|--------|

| Title is blank or null            | EmptyTitleException        | Yes    |

| Severity is not Low/Medium/High   | InvalidSeverityException   | Yes    |

| Database connection failure       | SQLException               | Yes    |





**Concepts Demonstrated**

| Java Concept             | Implementation                                      |

|--------------------------|-----------------------------------------------------|

| Abstract class           | Issue.java — base class with abstract methods s    |

| Inheritance              | Bug and Task extend Issue                     |

| Polymorphism             | displayDetails() overridden in each subclass      |

| Encapsulation            | All fields private with public getters              |

| Custom exceptions        | EmptyTitleException, InvalidSeverityException   |

| DAO design pattern       | BugDAO.java separates DB logic from business logic|

| JDBC                     | PreparedStatement, ResultSet, Connection      |

| Collections              | ArrayList<Issue> managed in IssueService        |

| File I/O                 | FileWriter appends errors to error.log         |

| Package organisation     | Layered architecture — models, services, dao, util  |



