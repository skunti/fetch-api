package com.fetch.api;

import com.fetch.pojos.Location;
import com.fetch.pojos.LocationResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * LocationFetcher class handles fetching location data from the OpenWeatherMap API.
 * It supports querying by zip code or city/state and processes API responses.
 */
@Setter
@Getter
@Log4j
public class LocationFetcher {

    /**
     * Default valid API key for the OpenWeatherMap API.
     */
    private static String API_KEY = "f897a99d971b5eef57be6fafa0d83239";

    /**
     * Base URL for the OpenWeatherMap Geo API.
     */
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";

    /**
     * Sets the API key for accessing the OpenWeatherMap API.
     *
     * @param apiKey the API key to set
     */
    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    /**
     * Fetches location data for a list of provided locations.
     *
     * @param locations list of locations (zip codes or city/state) to query
     */
    public static void fetchLocationData(List<String> locations) {
        if (Optional.ofNullable(API_KEY).orElse("").isEmpty()) {
            log.warn("❌ API Key is missing or empty. Please set the API Key.");
            return;
        }

        if (locations == null || locations.isEmpty()) {
            log.error("Location list is null or empty");
            return;
        }

        locations.stream()
                .map(LocationFetcher::buildUrl)
                .forEach(urlOpt -> urlOpt.ifPresentOrElse(
                        url -> performApiRequest(url),
                        () -> log.warn("⚠️ Invalid location format detected.")
                ));
    }

    /**
     * Builds the API request URL based on the location input.
     *
     * @param location location string (zip code or city/state)
     * @return Optional containing the constructed URL or empty if invalid
     */
    private static Optional<String> buildUrl(String location) {
        if (location.matches("\\d{5}")) {
            return Optional.of(String.format("%szip?zip=%s,US&appid=%s", BASE_URL, location, API_KEY));
        } else if (location.matches("^[a-zA-Z]+(?:,\\s?[a-zA-Z]+)*$")) {
            String sanitizedLocation = location.replace(" ", "");
            return Optional.of(String.format("%sdirect?q=%s,US&appid=%s", BASE_URL, sanitizedLocation, API_KEY));
        }
        return Optional.empty();
    }

    /**
     * Performs the API request and logs the response.
     *
     * @param url the API request URL
     */
    private static void performApiRequest(String url) {
        try {
            Response response = RestAssured.given().log().uri().get(url);
            response.prettyPrint();
            handleResponse(response);
        } catch (Exception e) {
            log.error(String.format("❌ Failed to fetch data: %s", e.getMessage()));
        }
        log.info("--------------------------------------------------");
    }

    /**
     * Handles the API response, validating and logging the data.
     *
     * @param response the API response
     */
    private static void handleResponse(Response response) {
        if (response.statusCode() == 200) {
            String responseBody = response.body().asString();
            if ("[]".equals(responseBody)) {
                log.info("⚠️ No location data found.");
                return;
            }

            try {
                if (responseBody.contains("zip")) {
                    Location locationData = response.as(Location.class);
                    logLocationData(locationData);
                } else {
                    LocationResponse[] res = response.as(LocationResponse[].class);
                    Arrays.stream(res).findFirst().ifPresentOrElse(
                            loc -> logLocationResponse(loc),
                            () -> log.warn("⚠️ No valid location data in response.")
                    );
                }
            } catch (Exception e) {
                log.warn(String.format("⚠️ Error processing response: %s", e.getMessage()));
            }
        } else {
            log.warn(String.format("⚠️ Error fetching data: HTTP %d", response.statusCode()));
        }
    }

    /**
     * Logs the location data retrieved from a zip code query.
     *
     * @param location the Location object
     */
    private static void logLocationData(Location location) {
        log.info(String.format("✅ Location data for zip %s:", location.getZip()));
        log.info("Country: " + location.getCountry());
        Assert.assertNotNull(location.getLat(), "Lat data is not Null");
        Assert.assertNotNull(location.getLon(), "Lon data is not Null");
        Assert.assertNotNull(location.getZip(), "Zip is not Null");
    }

    /**
     * Logs the location data retrieved from a city/state query.
     *
     * @param loc the LocationResponse object
     */
    private static void logLocationResponse(LocationResponse loc) {
        log.info("✅ Location data:");
        log.info("Longitude: " + loc.getLon());
        log.info("Latitude: " + loc.getLat());
        Assert.assertNotNull(loc.getLat(), "Lat data is not Null");
        Assert.assertNotNull(loc.getLon(), "Lon data is not Null");
    }
}
