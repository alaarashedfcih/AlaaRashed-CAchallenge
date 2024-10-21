# Crowd Analyzer Rest Assured API Testing Project

This project is a Rest Assured API testing framework designed to test various services for Crowd Analyzer API Task. 

The tests cover different categories, products, services, and stores, organized into a structured project to ensure maintainability and scalability.

## Project Structure

The project follows a standard Maven project layout:
Crowd Analyzer Rest Assured Task 
     
    ├── src
    │   ├── main
    │   │   ├── java 
    │   │   │   └── com.ca 
    │   │   │       ├── controller 
    │   │   │       ├── dataproviderobjects 
    │   │   │       │   ├── excel 
    │   │   │       │   └── json 
    │   │   │       ├── listeners 
    │   │   │       └── utilities  
    │   │   └── resources 
    │   └── test 
    │       ├── java 
    │       │   └── com.ca.tests 
    │       │       ├── categories 
    │       │       ├── products 
    │       │       ├── services 
    │       │       ├── stores 
    │       │       └── BaseTest 
    │       └── resources 
    ├── TestReport 
    ├── pom.xml 
    ├── README.md 
    └── testng.xml

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- An IDE like IntelliJ IDEA or Eclipse
- TestNG framework
- Rest Assured dependency

## Dependencies

The project's dependencies are managed by Maven and are specified in the `pom.xml` file. Some key dependencies include:

- **Rest Assured** for API testing
- **TestNG** for test management and execution
- **Lombok** to reduce boilerplate code
- **Jackson** for JSON parsing
- **Apache POI** for handling Excel files

## Getting Started

### Clone the Repository

    git clone <repository-url>
    cd Ca-api-tests

### Build the Project
#### To build the project and install the required dependencies, run:

    mvn clean install

### Running Tests
#### To run the tests, use the following command:

    mvn clean install

## Test Structure

The test classes are organized into the following packages:

- **categories**: Tests related to API endpoints for categories.
- **products**: Tests for product-related API endpoints.
- **services**: Service-related API tests.
- **stores**: Tests for store endpoints.

### BaseTest Class

The `BaseTest` class, located in the `com.ca.tests` package, acts as the parent class for all test classes. It contains common setup and tearDown methods that are inherited by the individual test classes to ensure consistent test initialization and cleanup.

## Utilities and Data Providers

- **dataProviderObjects**: This package includes classes designed for handling test data, including JSON and Excel file management, to ensure that tests can dynamically pull data as needed.
- **listeners**: Custom TestNG listeners are implemented in this package to facilitate advanced logging and reporting during the test execution.
- **utilities**: This package contains utility classes that provide common functions used across multiple test cases, making the codebase more modular and reusable.

## Test Reports

After each test execution, detailed test results are generated and stored in the `TestReport` directory. These reports provide an HTML summary of the test outcomes, including information about passed, failed, and skipped tests, which helps in analyzing the test results quickly and effectively.


## Contact
For any questions or support, please don't hesitate to reach out