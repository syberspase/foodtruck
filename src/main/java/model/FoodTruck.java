package model;

import java.util.List;

import javax.ws.rs.core.GenericType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Mapping class of JSON result
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruck {
    public static final GenericType<List<FoodTruck>> LIST_TYPE = new GenericType<List<FoodTruck>>() {
    };
    private final String applicant;
    private final String location;

    @JsonCreator
    public FoodTruck(@JsonProperty("applicant") String applicant, @JsonProperty("locationdesc") String location) {
        this.applicant = applicant;
        this.location = location;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "FoodTruck [applicant=" + applicant + ", location=" + location + "]";
    }
}
