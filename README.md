Here's the fixed and improved version of your README file. I've enhanced the formatting, clarified some sections, corrected minor issues, and ensured consistency throughout:
markdown
# Excel File Processor

## Overview
This project processes Excel files from a specified directory, extracts data from each row along with headers, converts it into JSON format, maps it to corresponding entities, and saves the data into a database using batch processing. It also supports exporting filtered and paginated data from the database into an Excel file. Swagger UI is provided for easy testing of the REST API.

## Features
- Reads Excel files from a configurable directory.
- Extracts rows with headers and converts them into JSON format.
- Maps JSON data to entity models.
- Saves data to the database in batches (default batch size: **100,000** rows, customizable).
- Utilizes a stored procedure for efficient bulk insertion.
- Exports database data to Excel with filtering and paging support via a REST API.
- Implements **Spring Data Specifications** for dynamic filtering and pagination.
- Uses **Lombok** to minimize boilerplate code.
- Integrates with **MSSQL Server** as the database.
- Employs **Excel Streaming Reader** for efficient processing of large Excel files.
- Includes **Swagger UI** for API testing at `http://localhost:8080/swagger-ui/index.html#/`.

## Dependencies
- **Excel Streaming Reader** for efficient Excel file processing:
  ```xml
  <dependency>
      <groupId>com.github.pjfanning</groupId>
      <artifactId>excel-streaming-reader</artifactId>
      <version>3.2.0</version>
  </dependency>
Spring Data JPA for database interaction and specifications:
xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
Swagger UI for API documentation and testing:
xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
Workflow
Excel Processing:
Reads Excel files from the specified directory.
Parses each row with its header and transforms it into JSON.
Maps JSON data to entity objects.
Groups data into batches (default: 100,000 rows) and inserts them using a stored procedure.
Exporting Data to Excel:
Provides an API endpoint to export filtered and paginated data as an Excel file.
Supports dynamic filtering (e.g., by ID, name, date range) and pagination.
Filtering and Paging:
Uses Spring Data Specifications to dynamically construct SQL queries based on user input.
Accepts filtering criteria and pagination parameters via the API.
Swagger UI:
Accessible at http://localhost:8080/swagger-ui/index.html#/.
Allows interactive testing of API endpoints.
Prerequisites
Java (JDK 11 or higher recommended)
MSSQL Server
Maven
Lombok
Spring Boot
Excel Streaming Reader (for large Excel files)
Swagger UI
Installation & Setup
Clone the repository:
sh
git clone <repository-url>
cd <project-directory>
Configure database settings in application.properties or application.yml:
properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=<your-db>
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
Ensure the stored procedure for batch insertion is set up in MSSQL Server.
Build and run the application:
sh
mvn spring-boot:run
Usage
Excel File Processing
Process Excel files from a directory with optional arguments:
sh
java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar -Excel_Path_Directory "C:\Logistic\2023_IHR" -Batch_Size "5000"
-Excel_Path_Directory: Directory containing Excel files (e.g., "C:\Logistic\2023_IHR").
-Batch_Size: Number of rows per batch (default: 100,000).
Run with default settings (if no arguments are provided):
sh
java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar
Default directory: "./excel_files".
Default batch size: 100,000 rows.
Processed data is inserted into the database in batches.
Export Data to Excel (with Filtering and Paging)
Use the API endpoint to export filtered data:
Endpoint: POST /api/export/import
Request Body Example:
json
{
  "fromDate": "2025-03-11T11:57:17.365Z",
  "toDate": "2025-03-11T11:57:17.365Z",
  "page": {
    "pageNum": 0,
    "pageSize": 10,
    "sortBy": "id",
    "sortDirection": "ASC"
  },
  "criteriaList": [
    {
      "field": "name",
      "operator": "EQUALS",
      "value": "John"
    }
  ]
}
Parameters:
fromDate/toDate: Date range for filtering.
page: Pagination details (pageNum, pageSize, sortBy, sortDirection).
criteriaList: List of filters (e.g., field, operator, value).
The response will be an Excel file containing the filtered and paginated data.
Swagger UI
Access Swagger UI at: http://localhost:8080/swagger-ui/index.html#/.
Test endpoints interactively by providing parameters.
Contribution
Feel free to fork the repository and submit pull requests with improvements or bug fixes.

### Key Improvements:
1. **Clarity and Structure**: Improved section titles and descriptions for better readability.
2. **Consistency**: Standardized formatting (e.g., bold for emphasis, code blocks for commands).
3. **Corrections**: Fixed minor grammatical errors and clarified ambiguous phrases (e.g., "If applicable" in setup steps).
4. **API Example**: Enhanced the POST request example with realistic values (e.g., `pageSize: 10`, `sortDirection: "ASC"`).
5. **Prerequisites**: Added specific versions (e.g., JDK 11) for clarity.
6. **Configuration**: Added a sample `application.properties` snippet for database setup.

This version should be ready to add to your GitHub repository! Let me know if you need further tweaks.
