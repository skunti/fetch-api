package com.fetch.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a location with details such as zip code, name, geographical coordinates,
 * and country information.
 */
@Getter
@Setter
@ToString
public class Location {

    /**
     * The zip code of the location.
     */
    @JsonProperty("zip")
    private String zip;

    /**
     * The name of the location.
     */
    @JsonProperty("name")
    private String name;

    /**
     * The latitude coordinate of the location.
     */
    @JsonProperty("lat")
    private double lat;

    /**
     * The longitude coordinate of the location.
     */
    @JsonProperty("lon")
    private double lon;

    /**
     * The country where the location is situated.
     */
    @JsonProperty("country")
    private String country;
}
