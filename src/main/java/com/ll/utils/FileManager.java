package com.ll.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static void write(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String filePath) {
        Path path = Paths.get(filePath);
        try {
            return Files.readString(path);
        } catch (NoSuchFileException | FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void delete(String path) {

        File file = new File(path);

        if (!file.delete()) {
            throw new RuntimeException("파일삭제 실패");
        }

    }


}
