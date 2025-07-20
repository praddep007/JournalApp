package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse {

    //private Location location;
    private Current current;

    @Data
    public class Current{

        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;

    }

//    public class Location{
//        private String name;
//        private String country;
//        private String region;
//        private String lat;
//        private String lon;
//        private String timezone_id;
//        private String localtime;
//        private int localtime_epoch;
//        private String utc_offset;
//    }



}



// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */


//private class Root{
//    private Request request;
//    private Location location;
//    private Current current;
//}


