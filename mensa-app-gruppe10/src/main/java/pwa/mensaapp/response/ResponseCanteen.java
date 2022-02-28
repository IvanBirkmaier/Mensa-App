package pwa.mensaapp.response;

import java.util.*;

public class ResponseCanteen {

private int id;
private String name;
private String city;
private String address;
private List coordinates;


    public ResponseCanteen() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List coordinates) {
        this.coordinates = coordinates;
    }
}
