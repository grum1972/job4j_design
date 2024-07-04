package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            String prevStatus = "";
            String currStatus;
            String start = "";
            String end;
            for (String line = input.readLine(); line != null; line = input.readLine()) {
                String[] parts = line.split(" ");
                currStatus = parts[0];
                if (("500".equals(currStatus) || "400".equals(currStatus)) && ("200".equals(prevStatus) || "300".equals(prevStatus))) {
                    start = parts[1];
                } else if (("200".equals(currStatus) || "300".equals(currStatus)) && ("400".equals(prevStatus) || "500".equals(prevStatus))) {
                    end = parts[1];
                    output.println(start + ";" + end + ";");
                }
                prevStatus = currStatus;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}