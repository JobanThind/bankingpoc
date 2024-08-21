# Simple Banking Transactions API

## Running the Application

**Clone the Repository**

   ```bash
   git clone https://github.com/JobanThind/bankingpoc.git
   cd bankingpoc

Build and run the server.java file. The application will run on http://localhost:8080 by default.

Configuring the Database
In-Memory Database (Default)
No additional setup is required.

PostgreSQL Configuration
1.Install PostgreSQL
2.Update Configuration
3.Open src/main/resources/application.properties.
4.Comment out the first line
spring.profiles.active=local
5.Create a PostgreSQL database named bankpoc with the following credentials:
Username: postgres
Password: connect

**Assumptions for APIs**
Balance must be greater than 0 when creating an account.
Transactions must be of at least 1 unit.
