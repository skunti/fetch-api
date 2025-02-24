
# Location Fetcher API Test Suite

This project contains automated test cases for validating the **Location Fetcher API** functionality. 
The tests are implemented using **TestNG** and **Rest-Assured** and aim to cover various scenarios,
including valid and invalid location inputs, API key validation, and edge cases.

## Dependencies

The project uses the following dependencies:

- **TestNG** - Testing framework used to organize and run the tests
- **Rest-Assured** - HTTP client used for making API requests
- **Log4j** - Logging framework to log test execution details
- **Lombok** - Java library to reduce boilerplate code
- **AssertJ** - Fluent assertion library to write expressive assertions

## Test Cases

The test cases are written in the class `FetchLocationTest.java` and validate the following:

# LocationFetcher API Integration Tests

This repository contains test cases for validating the functionality of the LocationFetcher API integration. The tests are built using **TestNG** and **RestAssured** to ensure that the API performs as expected under various scenarios.

## Test Cases

The tests in this repository cover a variety of scenarios for validating the functionality of the LocationFetcher API integration.

### 1. Fetch Location Data with Valid Input
**Test:** `fetchLocationDataTest`  
**Description:** Verifies that location data can be fetched successfully using valid locations and a valid API key.

### 2. Test with Invalid Location
**Test:** `testFetchDataForInvalidLocationTest`  
**Description:** Verifies the API's response when an invalid location is provided (e.g., non-existing city or malformed input).

### 3. Test with Empty Location List
**Test:** `testFetchDataWithEmptyLocationList`  
**Description:** Tests the API's response when an empty list of locations is provided.

### 4. Test with Null Location List
**Test:** `testFetchDataWithNullLocationList`  
**Description:** Verifies how the API behaves when a null value is passed as the location list.

### 5. Test with Special Characters in Location
**Test:** `testFetchDataWithSpecialCharacters`  
**Description:** Tests the response when locations contain special characters (e.g., @#$%^&*()).

### 6. Test with Large List of Locations
**Test:** `testFetchDataWithLargeLocationList`  
**Description:** Tests the performance of the API when a large number of locations (100+ entries) are provided.

### 7. Test with Whitespace in Location
**Test:** `testFetchDataWithWhitespaceLocation`  
**Description:** Verifies the API's response when locations consist of only whitespace characters.

### 8. Test with Numeric-Only Location
**Test:** `testFetchDataWithNumericLocation`  
**Description:** Tests the API's behavior when the location consists only of numbers.

### 9. Test with Duplicate Locations
**Test:** `testFetchDataWithDuplicateLocations`  
**Description:** Verifies that the API correctly handles lists with duplicate location entries.

### 10. Test with Null API Key
**Test:** `testFetchDataWithNullApiKey`  
**Description:** Verifies the API's behavior when the API key is set to null.

### 11. Test with Invalid API Key
**Test:** `testFetchDataWithInvalidApiKey`  
**Description:** Tests the API's response when an invalid API key is used.

### 12. Test with Empty API Key
**Test:** `testFetchDataWithEmptyApiKey`  
**Description:** Verifies how the API behaves when the API key is left empty.

## API Integration

The tests use the **LocationFetcher** class to fetch location data from the **OpenWeatherMap API**. 
The API interacts with locations by either zip code or city/state, returning geographical data, including latitude, 
longitude, and country information.

The `LocationFetcher` class constructs the appropriate URL for querying the API based on the location format and
uses the **RestAssured** library for making API requests.

### LocationFetcher Util Class

The `LocationFetcher` class handles the fetching of location data from the OpenWeatherMap API. It supports querying by **zip code** or **city/state** and processes API responses accordingly.

#### Key Features:
- **API Key**: The default API key is set to `f897a99d971b5eef57be6fafa0d83239`. You can set a custom key using `setApiKey()`.
- **Base URL**: The API uses `http://api.openweathermap.org/geo/1.0/` as the base URL for geo queries.
- **Location Queries**: You can pass a list of locations (zip code or city/state), and the `LocationFetcher` class will construct the API query URL and fetch the data.

#### Methods:
1. **fetchLocationData(List<String> locations)**: Fetches location data for a list of provided locations.
2. **buildUrl(String location)**: Constructs the API request URL based on whether the location is a zip code or city/state.
3. **performApiRequest(String url)**: Makes the API request using RestAssured and logs the response.
4. **handleResponse(Response response)**: Validates and processes the API response, logging the data or errors.
5. **logLocationData(Location location)**: Logs location data retrieved from a zip code query.
6. **logLocationResponse(LocationResponse loc)**: Logs location data retrieved from a city/state query.


## Setup

### Requirements:

- **Java 17**
- **Maven** to handle dependencies and build the project
- **Log4j Configuration File** (`log4j.properties`) for logging setup

### Running Tests

To run the tests using Maven, use the following command:

```bash
mvn clean test
```

### Sample Log Output

The tests will output logs with details about the execution, including any errors or warnings during the API calls.

## File Structure

```
src/
  ├── main/
  │   └── java/
  │       ├── com/
  │       │   └── fetch/
  │       │       ├── api/
  │       │       │   └── LocationFetcher.java
  │       │       ├── pojos/
  │       │       │   ├── Location.java
  │       │       │   └── LocationResponse.java
  │       │       └── tests/
  │       │           ├── AbstractTest.java
  │       │           └── FetchLocationTest.java
  └── resources/
      └── log4j.properties
```

## License

This project is licensed under the MIT License.
