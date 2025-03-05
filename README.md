# Excel File Reader

## Overview
This project reads a large Excel file from the file system and writes its contents to an SQL database using a stored procedure. Each row in the Excel file is converted into an entity class before being passed to the stored procedure for insertion.

## Features
- Reads large Excel files efficiently.
- Converts each row into an entity class.
- Invokes a stored procedure to insert data into the SQL database.
- Ensures duplicate entries are not inserted by checking `rowNumber` before inserting.

## How It Works
1. The application loads an Excel file from the file system.
2. Each row is parsed and mapped to an entity class.
3. The stored procedure is called with the entity data.
4. The stored procedure checks whether the `rowNumber` already exists in the database.
   - If `rowNumber` exists, the row is skipped.
   - If `rowNumber` does not exist, the row is inserted into the database.

## Database Stored Procedure
The stored procedure uses an `IF` condition and an `INSERT` SQL statement to handle the data insertion logic.

## Requirements
- Java (or the programming language used for this project)
- SQL database (e.g., MySQL, PostgreSQL, MSSQL, etc.)
- Apache POI (if using Java for Excel parsing)
- JDBC (for database connectivity)

## Installation & Usage
1. Clone the repository:
   ```sh
   git clone <repository-url>
   ```
2. Configure the database connection in the application.
3. Place the Excel file in the designated file path.
4. Run the application.
5. Monitor the database to verify data insertion.


## Contact
For any inquiries or issues, please create an issue in the repository or contact the project maintainer.

