# CA UI Tests Project

## Overview

### The CA UI Tests project is an automated testing suite designed to validate the user interface of the PetFinder web application, specifically focused on the Login and Register process.

### The project utilizes Selenium WebDriver, TestNG for test management, and ExtentReports for reporting, providing a robust framework for UI testing.

### This project is structured as a data-driven framework, allowing for flexible and reusable test execution with multiple data sets.

## - Table of Contents

    - Prerequisites
    - Installation
    - Project Structure
    - Configuration
    - Running Tests
    - Utilities
    - Prerequisites

### Before running the project, ensure you have the following installed:

    ### - Java Development Kit (JDK) 21 or later
    ### - Apache Maven for dependency management
    ### - An IDE (e.g., IntelliJ IDEA) for development
    ### - A web browser (e.g., Google Chrome) with the appropriate WebDriver

## Installation

### Clone the repository:

    git clone git clone <repository-url>
    cd ca-ui-tests

### Navigate to the project directory and build the project:

    mvn clean install

### Install dependencies:

    All dependencies are defined in the pom.xml file. 
    Running the mvn install command will automatically download the required libraries.

## Project Structure

    src
    └── main
    │   ├── java
    │   │   └── com.ca
    │   │       ├── datapproviderobjects
    │   │       ├── listeners
    │   │       ├── pages
    │   │       └── utilities
    │   └── resources
    │       ├── testdata
    │       ├── environment.properties
    │       └── logback.xml
    ├── test
    │   └── java
    │       └── com.ca.tests
    │           ├── login
    │           ├── register
    │           └── BaseTest                          
    ├── TestReport
    ├── pom.xml
    ├── README.md
    └── testng.xml

## Project documentation

### Configuration

#### The project uses a pom.xml file for managing dependencies and build configurations. Key dependencies include:

- Selenium WebDriver: For browser automation.
- TestNG: For test management and reporting.
- ExtentReports: For generating test execution reports.
- Apache POI: For handling Excel files (if required for test data).

#### Profiles: The project is configured with a test profile by default, which sets the environment name and application host URL.

### Running Tests

#### Run a single test class: In your IDE, right-click on the test class you wish to execute and select Run.

#### Run all tests: Execute the following command in your terminal from the project root:

    mvn test

#### Generate Reports: After running tests, ExtentReports will generate reports in the TestReport directory, which can be viewed in your browser.

### Utilities Directory

- src/main/java/com.ca/utilities: Contains utility classes that provide various helper functions used throughout the
  project.

    - Constants: A class that defines constant values used in the project, ensuring consistency and easy management of
      static values.

    - CustomAnnotations: Contains custom annotations that can be used for special functionalities within tests, such as
      grouping tests or marking them for specific execution conditions.

    - DataProviderSource: A utility class that provides data sources for parameterized tests, enabling the execution of
      the same test with multiple sets of data.

    - EncodeToBase64Utils: Provides methods for encoding strings into Base64 format, useful for handling sensitive data
      or API requests that require such encoding.

    - ExcelUtils: A utility class for handling Excel files, allowing for reading and writing data to Excel spreadsheets,
      often used for data-driven testing.

    - ExtentManager: Manages the configuration and setup of ExtentReports, allowing for customized reporting of test
      results and execution details.

    - Utilities: A collection of general-purpose methods that assist with common tasks like waiting for elements, taking
      screenshots, and performing actions on web elements.

### Data-Driven Testing

#### This project implements a data-driven testing approach, allowing tests to run with multiple sets of input data. This capability enhances test coverage and reliability by verifying that the application behaves correctly under various conditions. The DataProviderSource class serves as a central hub for sourcing test data, which can be pulled from various formats, such as Excel files or static data sources.