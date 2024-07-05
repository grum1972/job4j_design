package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> files = new HashMap<>();
    private List<Path> paths = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        files.computeIfAbsent(fileProperty, f -> new ArrayList<>()).add(file.toAbsolutePath());
        return super.visitFile(file, attributes);
    }

    public List<Path> getPaths() {
        for (List<Path> value : files.values()) {
            if (value.size() > 1) {
                paths.addAll(value);
            }
        }
        return paths;
    }
}