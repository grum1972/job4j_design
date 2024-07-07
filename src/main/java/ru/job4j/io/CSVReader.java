package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {

    private static List<String> getData(ArgsName argsName) {
        Path path = Paths.get(argsName.get("path"));
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileReader(path.toFile()))) {
            while (scanner.hasNextLine()) {
                String res = scanner.nextLine();
                list.add(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static List<Integer> getIndexes(ArgsName argsName, List<String> list) {
        List<String> filter = List.of(argsName.get("filter").split(","));
        List<String> columns = List.of(list.get(0).split(argsName.get("delimiter")));
        List<Integer> indexes = new ArrayList<>();
        for (String flt : filter) {
            int index = columns.indexOf(flt);
            if (index != -1) {
                indexes.add(index);
            }
        }
        return indexes;
    }

    private static List<String> getListOutput(ArgsName argsName, List<String> list, List<Integer> indexes) {
        List<String> listOutput = new ArrayList<>();
        String[] words;
        for (String s : list) {
            words = s.split(argsName.get("delimiter"));
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < indexes.size(); i++) {
                builder.append(words[indexes.get(i)]);
                if (i != indexes.size() - 1) {
                    builder.append(argsName.get("delimiter"));
                }

            }
            listOutput.add(builder.toString());
        }
        return listOutput;
    }

    private static void writeData(ArgsName argsName, List<String> listOutput) {
        Path target = Paths.get(argsName.get("out"));
        try (PrintWriter writer = new PrintWriter(new FileWriter(target.toFile()))) {
            listOutput.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handle(ArgsName argsName) {
        List<String> list = getData(argsName);
        List<Integer> indexes = getIndexes(argsName, list);
        List<String> listOutput = getListOutput(argsName, list, indexes);
        if ("stdout".equals(argsName.get("out"))) {
            listOutput.forEach(System.out::println);
        } else {
            writeData(argsName, listOutput);
        }
    }

    public static void validate(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("The number of arguments is less than 4");
        }
        if (!args[0].contains(".csv")) {
            throw new IllegalArgumentException("Расширение файла должно быть CSV");
        }
    }

    public static void main(String[] args) throws Exception {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}