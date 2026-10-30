# Bug and Issue Tracking System

## Project Overview
A web-based Bug and Issue Tracking System developed to manage, track, and monitor software bugs efficiently.

## Features
- Create, view, update, and delete bugs.
- Dashboard with bug summary cards.
- Search bugs by title.
- Filter bugs by status and priority.
- Sort bugs using different criteria.
- View detailed bug information.
- Track bug status and priority.
- Unit testing using JUnit 5 and Mockito.

## Technologies Used

**Backend:** Java, Spring Boot, Spring Data JPA

**Frontend:** HTML, CSS, JavaScript

**Database:** MySQL

**Testing:** JUnit 5, Mockito

**Build Tool:** Maven

**IDE:** Eclipse

## Database Configuration
1. Create a MySQL database named `bug_tracker`.
2. Configure your MySQL username and password in `application.properties`.
3. Update the database connection URL as required.

## How to Run
1. Clone or download this repository.
2. Import the project into Eclipse as an existing Maven project.
3. Configure the MySQL database.
4. Run the main Spring Boot application class.
5. Open your browser and visit:

`http://localhost:8080`

## Testing
The project includes 8 JUnit test cases covering:
- Bug creation
- Retrieving all bugs
- Retrieving a bug by ID
- Updating bugs
- Deleting bugs
- Handling nonexistent bug IDs

All 8 tests passed during testing.

## Author
Athmakuri Tejaswini