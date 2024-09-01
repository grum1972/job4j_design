package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Client client = new Client(false, 50, new Address("Belgorod, Orlova st., 50"),
                new String[] {"MAN", "DAF"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(client));

        final String clientJson =
                "{"
                        + "\"owner\":true,"
                        + "\"age\":35,"
                        + "\"address\":"
                        + "{"
                        + "\"address\":\"Belgorod, Orlova st., 50\""
                        + "},"
                        + "\"autos\":"
                        + "[\"MAN\",\"DAF\"]"
                        + "}";
        final Client clientMod = gson.fromJson(clientJson, Client.class);
        System.out.println(clientMod);
    }
}