# Rick and Morty Testing
This is an Android app to demonstrate testing. It includes unit tests and integration tests for all the features in the app. This is an XML project with a fragment-first approach and navigation using navigation components.


# Tech Stack Used
The following libraries were used in the development of the app.
- **Glide** - Image loading
- **Retrofit** - API calls
- **Hilt** - Dependency injection
- **Timber** - Logging
- **Navigation Components** - Handling fragment navigation


# Test suite
The testing suite of the app utilized the libraries below.
- **JUnit 4** - Junit for testing
- **Espresso** - Instrumented Tests
- **Mockito** - Unit testing
- **Mock Webserver** - Web response mocking


# Project Folder Structure
This is the folder structure of the project in the main and test directories.
- root folder
    - core - contains classes shared across all features such as dependency injection, etc
    - features - contains features and their constituent classes and fragments

A typical feature folder will look like this;
- Feature name
    - data
        - api - api client for all network classes in the feature
        - repositories - contains repository implementations
    - domain
        - models - contains models used in the app
        - repositories - repository contracts
    - presentation
        - fragments
        - adapters
        - viewmodels



