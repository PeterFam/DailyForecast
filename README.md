# Daily Weather Forecast Android Application 🌤️  
This Android app provides daily weather forecasts by fetching data from the OpenWeatherMap API. The project follows **Clean Architecture** principles, leverages **Jetpack Compose** for building modern UI components, and integrates key libraries such as **Retrofit** for networking, **Room** for local caching, and **Hilt/Dagger** for dependency injection. The app also includes **unit and UI testing** to ensure quality and reliability.

---

## Table of Contents  
- [Architecture Overview](#architecture-overview)  
- [Layers Explanation](#layers-explanation)  
- [Libraries and Dependencies](#libraries-and-dependencies)  
- [Project Features](#project-features)  
- [Testing](#testing)  
- [Setup and Usage](#setup-and-usage)  
- [Conclusion](#conclusion)  

---

## Architecture Overview  
The project follows the **Clean Architecture** pattern, ensuring:  

- **Separation of concerns** across layers.  
- **Testability** by isolating components.  
- **Scalability** for future updates and enhancements.  

### Clean Architecture Layers:  
- **Presentation Layer**: Handles the UI logic using Jetpack Compose and interacts with the ViewModel.  
- **Domain Layer**: Contains the business logic, including UseCases and interfaces, to ensure data flow between the repository and the presentation layer.  
- **Data Layer**: Manages data sources (API and Room DB). The **Repository** acts as a mediator, deciding whether to fetch data from the network or the local database.  

---

## Layers Explanation  

### 1. Presentation Layer  
- Uses **Jetpack Compose** for building reactive, declarative UI components.  
- **ViewModel** manages the UI state and interacts with the domain layer to get data.  
- The main screen displays a **city dropdown**, **weather forecast list**, and handles **loading and error states**.  

### 2. Domain Layer  
- Contains **interfaces** and **UseCases** that the data layer implements.  
- The Repository interface exposes functions to retrieve **weather** and **city** data.  

### 3. Data Layer  
- Uses **Retrofit** to fetch data from the OpenWeatherMap API.  
- Caches data locally using **Room DB**.  
- The **Repository implementation** decides whether to use cached data or make a network call based on **network availability**.  

---

## Libraries and Dependencies  
- **Retrofit** – Handles network operations, allowing seamless communication with the weather API.  
- **Room** – Provides a local SQLite database to cache city and weather data.  
- **Hilt/Dagger** – Simplifies dependency injection throughout the app, enabling modular and testable code.  
- **Jetpack Compose** – Builds modern, reactive UI components.  
- **Coroutines** – Manages background tasks and asynchronous programming.  
- **JUnit and Truth** – Used for unit testing to ensure the correctness of the business logic.  
- **Compose UI Testing** – Verifies the behavior of UI components with Compose testing APIs.  

---

## Project Features  
- **City Selection**: A dropdown menu lets the user select a city to view the weather forecast.  
- **Weather Forecast**: Displays a list of weather data for the selected city.  
- **Error Handling**: Shows an error message and provides a retry button if data loading fails.  
- **Offline Caching**: Caches data using Room DB to ensure availability even without an internet connection.  

---

## Testing  

### Unit Testing  
- The **ViewModel** is tested using JUnit to ensure it correctly manages state changes.  
- **Mockito** and **MockWebServer** are used to mock dependencies and network responses during testing.  

### UI Testing  
- **Compose UI testing** ensures that UI components behave as expected.  
- Verifies whether the **loading indicator**, **error message**, and other elements appear based on the app state.

---
Feel free to reach out if you have any questions or need further assistance. 😊
  
