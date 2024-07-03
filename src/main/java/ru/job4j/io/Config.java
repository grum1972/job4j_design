package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {

        try (BufferedReader input = new BufferedReader(new FileReader(this.path))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                if (!line.contains("#") && line.length() != 0) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 0 || parts[0].length() == 0 || parts[1].length() == 0) {
                        throw new IllegalArgumentException("Invalid argument");
                    }
                    values.put(parts[0], parts[1]);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int size() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }

}