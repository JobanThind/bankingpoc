# Simple Banking Transactions API

## Running the Application

1. **Clone the Repository**

   ```bash
https://github.com/JobanThind/bankingpoc.git
Build and Run the server.java file


The application will run on http://localhost:8080 by default.

Configuring the Database
In-Memory Database (Default)
No additional setup required.

PostgreSQL Configuration
Install PostgreSQL
Update Configuration
Open src/main/resources/application.properties.
Comment out the  line - spring.profiles.active=local

Create a PostgreSQL database named bankpoc with the credentials:

Username: postgres
Password: connect


Assumptions for apis
Balance must be greater than 0 when creating an account.
Transaction must be of at least 1 unit.
