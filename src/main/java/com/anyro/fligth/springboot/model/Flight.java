package com.anyro.fligth.springboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Flight {

    @Id
    private Long id;  // flight number
    private String dateString; //flight departure date
    private String timeString; //flight departure time
    private String airline; //fligt airline name
    private String departure; //flight departure place
    private String arrival; //flight destination


    //getters and setters; lines 22 - 66

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getTime() {
        return timeString;
    }

    public void setTime(String timeString) {
        this.timeString = timeString;
    }
}
