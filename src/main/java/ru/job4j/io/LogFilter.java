package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] parts = line.split(" ");
                if (parts[8].equals("404")) {
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        var data = filter();
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(out)
                ))) {
            data.forEach(output::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");

    }
}