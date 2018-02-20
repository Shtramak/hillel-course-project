package com.courses.tellus.route;

import com.courses.tellus.plane.Plane;

public class Flight {
    private final Plane plane;
    private final String originAirport;
    private final String destinationAirport;

    public Flight (Plane plane, String originAirport, String destinationAirport){
        this.plane = plane;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
    }

    @Override
    public String toString(){
        return this.plane + " (" + this.originAirport + "-" + this.destinationAirport + ")";
    }
}
