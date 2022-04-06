package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Data
public class File{

    public static int id = 0;

    private String filename;
    private Integer sizeOfFile;
    private LocalDateTime startWaitingTime;
    private Double priority;

    public void initFile() {
        Random generator = new Random();
        filename = "file_" + id++;
        sizeOfFile = generator.nextInt(2, 100);
        startWaitingTime = LocalDateTime.now().minusHours(generator.nextInt(100));
    }

    @Override
    public String toString() {
        return filename + " size: " + sizeOfFile + "B " + priority;
    }
}
