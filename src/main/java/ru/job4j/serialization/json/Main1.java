package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {

        JSONObject jsonAddress = new JSONObject("{\"address\":\"Belgorod, Orlova st., 50\"}");
        List<String> list = new ArrayList<>();
        list.add("MAN");
        list.add("DAF");
        JSONArray jsonAutos = new JSONArray(list);
        final Client client = new Client(false, 50, new Address("Belgorod, Orlova st., 50"),
                new String[] {"MAN", "DAF"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("owner", client.isOwner());
        jsonObject.put("age", client.getAge());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("autos", jsonAutos);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(client).toString());
    }
}
