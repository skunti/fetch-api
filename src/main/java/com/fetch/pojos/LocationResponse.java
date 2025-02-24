package com.fetch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

/**
 * Represents the response containing location information.
 * This class holds the details such as the name of the location, local names in different languages,
 * geographical coordinates, and the country and state information.
 */
@Data
public class LocationResponse {

    /**
     * The name of the location.
     */
    private String name;

    /**
     * A map that holds local names of the location in different languages.
     * The key is the language code, and the value is the name in that language.
     */
    @JsonProperty("local_names")
    private Map<String, String> localNames;

    /**
     * The latitude coordinate of the location.
     */
    private double lat;

    /**
     * The longitude coordinate of the location.
     */
    private double lon;

    /**
     * The country where the location is situated.
     */
    private String country;

    /**
     * The state or region of the location.
     */
    private String state;
}
