# Mobile Test Automation Project

This is a mobile test automation project built with Appium and TestNG. It provides automated test scripts for the API Demos Android application.

## Prerequisites

Before running the tests, ensure that you have the following software installed:

- Appium: A mobile test automation framework. Install instructions can be found [here](https://appium.io/docs/en/about-appium/getting-started/?lang=en). To install Appium globally, run the following command:

  ```shell
  sudo npm install -g appium@next
  ```

- Appium WebDriver for Android (UiAutomator2): Install the Appium driver for Android by running the following command:

  ```shell
  appium driver install uiautomator2
  ```

- Java Development Kit (JDK): Ensure that you have Java 8 or above installed.
- Android SDK: Set up the Android SDK and configure the necessary environment variables.
- Maven: A build and dependency management tool. Install instructions can be found [here](https://maven.apache.org/install.html).

## Getting Started

1. Clone the repository:

   ```shell
   git clone git@github.com:fatih-sukran/Appium-Automation-Cases.git
   ```

2. Open the project in your preferred Java IDE.

3. Update the `config.properties` file located in the `src/main/resources` directory with the appropriate configuration values for your test environment.

4. Run the test suite by executing the following Maven command:

   ```shell
   mvn test
   ```

   This will run all the test cases defined in the project.

## Project Structure

The project has the following structure:

- `src/main/java`: Contains the main Java source code.
    - `com.fatih.config`: Configuration-related classes.
    - `com.fatih.pages`: Page object classes representing different screens of the application.
    - `com.fatih.utility`: Utility classes for driver setup, actions, and other common functionalities.
- `src/test/java`: Contains the test scripts.
    - `com.fatih.tests`: Test classes containing the actual test cases.
- `src/main/resources`: Contains the configuration files.
    - `config.properties`: Configuration properties used in the project.

## Writing Test Cases

Test cases are written using TestNG, a testing framework for Java. TestNG annotations are used to define the test methods and their behavior. You can create new test classes in the `com.fatih.tests` package to define additional test cases.

## Reporting

The project uses Allure for test reporting. After running the tests, an Allure report is generated, which provides detailed information about test results, including screenshots and logs. To view the report, run the following command:

```shell
allure serve allure-results
```

This will open the Allure report in your default web browser.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvement, please feel free to create a pull request or submit an issue.

## License

This project is licensed under the [MIT License](LICENSE).
