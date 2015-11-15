package com.server.datatype;


import java.util.List;

/**
 * Created by jp on 15.11.2015.
 */
public class CityData {
    private CityData cityData;
    private List<Location> locations;

    public CityData(CityData cityData, List<Location> locations) {
        this.cityData = cityData;
        this.locations = locations;
    }

    public CityData getCityData() {
        return cityData;
    }

    public void setCityData(CityData cityData) {
        this.cityData = cityData;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
