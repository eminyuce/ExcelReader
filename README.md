# Excel File Processor

## Overview
This project processes Excel files from a specified directory, extracts data from each row along with the headers, converts it into JSON format, maps it to respective entities, and saves the data into a database using batch processing.

## Features
- Reads Excel files from a directory.
- Extracts each row along with headers and generates a JSON representation.
- Converts the JSON data to the corresponding entity model.
- Saves data to the database in batches.
- Uses a stored procedure for efficient bulk insertion.
- Batch size is set to **100,000** rows per database call.
- Uses **Lombok** to reduce boilerplate code.
- Utilizes **MSSQL Server** as the database.
- Uses **Excel Streaming Reader** for efficient Excel file processing.

## Dependencies
The project uses the following dependency for reading large Excel files efficiently:
```xml
<dependency>
    <groupId>com.github.pjfanning</groupId>
    <artifactId>excel-streaming-reader</artifactId>
    <version>3.2.0</version>
</dependency>
```

## Workflow
1. Read Excel files from the target directory.
2. Parse each row along with its header and transform it into JSON format.
3. Map the JSON data to the respective entity.
4. Perform batch processing by grouping data into batches of 100,000 rows.
5. Call a stored procedure to insert all batch records in a single operation.

## Prerequisites
- Java (or relevant programming language for implementation)
- MSSQL Server as the database
- JDBC or ORM framework for database interaction
- Lombok for reducing boilerplate code
- Apache POI / Excel Streaming Reader for Excel file parsing

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd <project-directory>
   ```
2. Configure database connection properties in `application.properties` or `config.yaml`.
3. Ensure the required stored procedure for batch insertion is available in the database.
4. Run the application to process Excel files.

## Usage
1. Place Excel files in the designated directory.
2. Run the application.
3. Processed records will be inserted into the database in batches.

## Contribution
Feel free to fork and contribute by submitting pull requests.

## License
This project is licensed under the MIT License.


