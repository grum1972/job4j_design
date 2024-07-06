package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        String[] args1 = new String[]{"-d=c:\\projects\\job4j_design\\", "-e=.java", "-o=project.zip"};
        ArgsName zipArgs = ArgsName.of(args1);
        String[] argsSearch = new String[3];
        argsSearch[0] = zipArgs.get("d");
        argsSearch[1] = zipArgs.get("e");
        argsSearch[2] = zipArgs.get("o");
        Path start = Paths.get(zipArgs.get("d"));
        for (String el : argsSearch) {
            System.out.println(el);
        }
        Search.validate(argsSearch);
        zip.packFiles(Search.search(start, path -> !path.toFile().getName().endsWith(argsSearch[1])), new File("./" + argsSearch[2]));
    }
}