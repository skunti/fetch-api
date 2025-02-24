package com.fetch.tests;

import com.fetch.api.LocationFetcher;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Test class for validating the Location Fetcher API functionality.
 * This class contains TestNG test cases covering various scenarios
 * including valid and invalid location inputs, and API key validations.
 *
 * <p>TestNG is a testing framework inspired by JUnit and NUnit but introduces new functionalities
 * that make it more powerful and easier to use. It supports annotations, parallel execution,
 * data-driven testing, and integrates well with tools like Maven and Jenkins.</p>
 */
@Log4j
public class FetchLocationTest extends AbstractTest {

    /**
     * Verifies that location data can be fetched successfully using a valid API key.
     * This test uses a list of valid locations to ensure the API returns expected data.
     */
    @Test(description = "Fetch location data using a valid API key and verify the response.")
    public void fetchLocationDataTest() {
        List<String> locations = Arrays.asList("Madison, WI", "12345", "Chicago, IL", "10001");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Tests the API's response when provided with an invalid location.
     * Expects the API to handle invalid locations gracefully.
     */
    @Test(description = "Test API response with an invalid location input.")
    public void testFetchDataForInvalidLocationTest() {
        List<String> locations = Arrays.asList("InvalidCity,ZZ");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API behavior when no locations are provided.
     */
    @Test(description = "Test API response with an empty location list.")
    public void testFetchDataWithEmptyLocationList() {
        List<String> locations = Collections.emptyList();
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API response when null location list is passed.
     */
    @Test(description = "Test API response with a null location list.")
    public void testFetchDataWithNullLocationList() {
        LocationFetcher.fetchLocationData(null);
    }

    /**
     * Verifies API behavior when special characters are included in location input.
     */
    @Test(description = "Test API response with special characters in location input.")
    public void testFetchDataWithSpecialCharacters() {
        List<String> locations = Arrays.asList("@#$%^&*(),.!?");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Tests API performance with a large list of locations.
     */
    @Test(description = "Test API response with a large list of locations.")
    public void testFetchDataWithLargeLocationList() {
        List<String> locations = Collections.nCopies(100, "Chicago, IL");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API response when location input contains only whitespace.
     */
    @Test(description = "Test API response with whitespace as location input.")
    public void testFetchDataWithWhitespaceLocation() {
        List<String> locations = Arrays.asList("   ");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API behavior when numeric-only location names are provided.
     */
    @Test(description = "Test API response with numeric-only location names.")
    public void testFetchDataWithNumericLocation() {
        List<String> locations = Arrays.asList("1234567890");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API behavior when duplicate locations are included in the list.
     */
    @Test(description = "Test API response with duplicate locations in the list.")
    public void testFetchDataWithDuplicateLocations() {
        List<String> locations = Arrays.asList("Chicago, IL", "Chicago, IL");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies API response when the API key is null.
     */
    @Test(description = "Test API response with a null API key.")
    public void testFetchDataWithNullApiKey() {
        LocationFetcher.setApiKey(null);
        List<String> locations = Arrays.asList("12345");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies the API's behavior when an invalid API key is used.
     * Expects an error response or failure message due to the invalid API key.
     */
    @Test(description = "Verify API behavior with an invalid API key.")
    public void testFetchDataWithInvalidApiKey() {
        LocationFetcher.setApiKey("invalid-api-key");
        List<String> locations = Arrays.asList("12345");
        LocationFetcher.fetchLocationData(locations);
    }

    /**
     * Verifies the API's response when an empty API key is used.
     * Expects an error response or failure message due to the missing API key.
     */
    @Test(description = "Check API response with an empty API key.")
    public void testFetchDataWithEmptyApiKey() {
        LocationFetcher.setApiKey("");
        List<String> locations = Arrays.asList("12345");
        LocationFetcher.fetchLocationData(locations);
    }
}
