Here's the updated **README** file with the new POST request for getting data:

```markdown
# Excel File Processor

## Overview
This project processes Excel files from a specified directory, extracts data from each row along with the headers, converts it into JSON format, maps it to respective entities, and saves the data into a database using batch processing. Additionally, it allows exporting data from the database into an Excel file with support for filtering and paging based on dynamic criteria. Swagger UI is also provided to easily test the REST API.

## Features
- Reads Excel files from a directory.
- Extracts each row along with headers and generates a JSON representation.
- Converts the JSON data to the corresponding entity model.
- Saves data to the database in batches.
- Uses a stored procedure for efficient bulk insertion.
- Batch size is set to **100,000** rows per database call, but can be customized.
- Provides a **controller layer** for exporting data as an Excel file, allowing filtering and paging.
- Implements **Spring Data Specifications** to handle filtering criteria and paging from the database.
- Uses **Lombok** to reduce boilerplate code.
- Utilizes **MSSQL Server** as the database.
- Uses **Excel Streaming Reader** for efficient Excel file processing.
- **Swagger UI** is available to test the REST API at `http://localhost:8080/swagger-ui/index.html#/`.

## Dependencies
The project uses the following dependency for reading large Excel files efficiently:
```xml
<dependency>
    <groupId>com.github.pjfanning</groupId>
    <artifactId>excel-streaming-reader</artifactId>
    <version>3.2.0</version>
</dependency>
```

Additionally, the project uses the following for Spring Data Specifications and paging:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

For Swagger UI:
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Workflow
1. **Excel Processing:**
    - Read Excel files from the target directory.
    - Parse each row along with its header and transform it into JSON format.
    - Map the JSON data to the respective entity.
    - Perform batch processing by grouping data into batches of 100,000 rows, or as specified by the user.
    - Call a stored procedure to insert all batch records in a single operation.

2. **Exporting Data to Excel with Filtering and Paging:**
    - Provides an API endpoint for exporting filtered data from the database to an Excel file.
    - Supports filtering criteria passed as query parameters (e.g., by ID, name, date range).
    - Implements paging for large datasets, allowing export of data in manageable chunks.

3. **Paging and Filtering:**
    - Use Spring Data Specifications to dynamically build SQL queries based on user input.
    - The controller accepts filtering criteria (e.g., specific fields, date ranges, etc.) and supports paging through the result set.

4. **Swagger UI for API Testing:**
    - Swagger UI is enabled and can be accessed at `http://localhost:8080/swagger-ui/index.html#/`.
    - It allows you to easily test the REST API by interacting with the endpoints without writing any code.

## Prerequisites
- Java (or relevant programming language for implementation)
- MSSQL Server as the database
- JDBC or ORM framework for database interaction
- Lombok for reducing boilerplate code
- Apache POI / Excel Streaming Reader for Excel file parsing
- Spring Boot for creating RESTful services
- Swagger UI for API testing

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone <repository-url>
   cd <project-directory>
   ```
2. Configure database connection properties in `application.properties` or `config.yaml`.
3. Ensure the required stored procedure for batch insertion is available in the database.
4. If applicable, set up the database schema for storing the processed data.
5. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage

### Excel File Processing
1. The application can process Excel files in a directory. If you provide the `-Excel_Path_Directory` argument, the application will process the files from the specified path. Additionally, you can specify the batch size using the `-Batch_Size` argument. If no arguments are provided, the application will still work with default values.

2. **Execution Command to Process Excel Files:**
   - To process Excel files from a specified directory with a custom batch size:
   ```sh
   java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar -Excel_Path_Directory "C:\Logistic\2023_IHR" -Batch_Size "5000"
   ```
   - `-Excel_Path_Directory`: The path to the directory containing the Excel files to process.
   - `-Batch_Size`: The number of rows to process in each batch (default is **100,000** rows).

3. If no arguments are provided, the application will use default values:
   ```sh
   java -Xms2g -Xmx2g -jar ExcelHelper-0.0.1-SNAPSHOT.jar
   ```
   In this case, the application will:
   - Look for Excel files in a default directory (e.g., `"./excel_files"`).
   - Use a default batch size (e.g., **100,000** rows).

4. The processed records will be inserted into the database in batches as per the specified or default batch size.

### Export Data to Excel (with Filtering and Paging)
1. Access the API endpoint for exporting data to Excel:
   - **POST /api/export/import**: Exports filtered data as an Excel file.
   - **Request Body Example:**
   ```json
   {
     "fromDate": "2025-03-11T11:57:17.365Z",
     "toDate": "2025-03-11T11:57:17.365Z",
     "page": {
       "pageNum": 0,
       "pageSize": 0,
       "sortBy": "string",
       "sortDirection": "string"
     },
     "criteriaList": [
       {
         "field": "string",
         "operator": "string",
         "value": "string"
       }
     ]
   }
   ```
   - **Request Parameters**:
     - `fromDate`: Start date for filtering.
     - `toDate`: End date for filtering.
     - `page`: Paging object with `pageNum`, `pageSize`, `sortBy`, and `sortDirection` for pagination.
     - `criteriaList`: A list of filtering criteria (field, operator, and value).

2. The application will process the filtered data and return an Excel file for download.

### Swagger UI for Testing the API
- Swagger UI is available at: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).
- You can use Swagger UI to explore the available API endpoints and try them out by providing different filtering and paging parameters.

   

## Contribution
Feel free to fork and contribute by submitting pull requests.
 
