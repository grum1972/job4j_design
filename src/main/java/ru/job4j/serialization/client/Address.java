package ru.job4j.serialization.client;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "address")
public class Address {
    @XmlAttribute
    private String address;

    public Address() {

    }

    public Address(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{"
                + "address='" + address + '\''
                + '}';
    }
}
