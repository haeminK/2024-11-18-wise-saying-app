package com.ll.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private final String basePath;
    private final String fileExtension;
    private final String idPath;


    public FileManager(String basePath, String fileExtension) {
        this.basePath = basePath;
        this.fileExtension = fileExtension;
        this.idPath = basePath + "/lastId.txt";
    }

    public String getBasePath() {
        return basePath;
    }


    private String getDbPathById(String subPath, int id) {
        return "%s/%s/%s%s".formatted(basePath, subPath, id, fileExtension);
    }

    public int updateAndGetCurrentId() {
        String strId = read(idPath);
        int id = (strId == null) ? 0 : Integer.parseInt(strId);
        updateId(id+1);

        return id;
    }

    private void updateId(int id) {
        write(idPath, String.valueOf(id));
    }


    public void writeById(String subPath, int id, String content) {
        write(getDbPathById(subPath, id), content);
    }


    public String readById(String subPath, int id) {
        return read(getDbPathById(subPath, id));
    }


    public void deleteById(String subPath, int id) {
        delete(getDbPathById(subPath, id));
    }


    public void writeBySubPath(String subPath, String content) {
        write(basePath+"/"+subPath+fileExtension, content);
    }

    public String readBySubPath(String subPath) {
        return read(basePath+"/"+subPath+fileExtension);
    }

    public void write(String filePath, String content) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String read(String filePath) {
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


    public void delete(String path) {

        File file = new File(path);

        if (!file.delete()) {
            throw new RuntimeException("파일삭제 실패");
        }

    }


}
