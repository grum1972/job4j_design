package ru.job4j.serialization.json;

import java.util.Arrays;

public class Client {
    private final boolean owner;
    private final int age;
    private final Address address;
    private final String[] autos;

    public Client(boolean owner, int age, Address address, String[] autos) {
        this.owner = owner;
        this.age = age;
        this.address = address;
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "Client{"
                + "owner=" + owner
                + ", age=" + age
                + ", address=" + address
                + ", autos=" + Arrays.toString(autos)
                + '}';
    }
}
