package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.util.*;

@Data
public class User {

    public static Long id = 0L;
    private Long userId;
    private List<File> files = new LinkedList<>();

    public void initUser(){
        userId = id++;
        generateFilesForUser();
    }

    private void generateFilesForUser() {
        Random random = new Random();
        int numbersOfFiles = random.nextInt(1,10);
        for (int i = 0; i < numbersOfFiles; i++) {
            File file = new File();
            file.initFile();
            files.add(file);
        }
        files.sort(Comparator.comparingInt(File::getSizeOfFile));
    }

    @Override
    public String toString() {
        return "User" +
                + userId +
                ", files: " + files;
    }
}
