# CoinDispenser
CoinDispenser

To start the project, set up the db in my sql and configure the connection details in the applicaiton.properties 

Run the src\main\resources\DB_SCRIPTS.sql for creating the table and seeding the required data.

The test case set up in the H2 in memory db. It will read the schema and data from src\test\resources sql files.

The application access url will be 

http://localhost:8080/dispense/1?leastNumberOfCoins=false
http://localhost:8080/dispense/1?leastNumberOfCoins=true
