package domain;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private String name;
    private String country;
    private String city;
    LinkedBlockingQueue<Object> operators;

    //constructor
    public Client(String name, String country, String city, LinkedBlockingQueue<Object> operators) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.operators = operators;
    }

    //getters and setters.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //override methods of class Object
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(country, client.country) && Objects.equals(city, client.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, city);
    }

    @Override
    public String toString() {
        return "Client " +
                name +
                " (" + country +
                ", " + city +
                ")";
    }

}
