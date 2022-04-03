package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.util.Objects;
import java.util.Random;

@Data
public class File {

    public static int id = 0;

    private String filename;
    private Integer sizeOfFile;

    public void initFile() {
        Random generator = new Random();
        filename = "file_" + id++;
        sizeOfFile = generator.nextInt(2, 100);
    }

    @Override
    public String toString() {
        return filename + " size: " + sizeOfFile + "B ";
    }
}
