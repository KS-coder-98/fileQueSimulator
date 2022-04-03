package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

@Data
public class HardDrive implements Runnable{

    private String name;
    private Double actualProgress;
    private Boolean free;
    private File actualFile;

    public void saveFile() {
        free = false;
        actualProgress = 0.0;
        for (double i = 1; i < actualFile.getSizeOfFile(); i++) {
            actualProgress = (i / actualFile.getSizeOfFile());
            System.out.println(name + " "+ actualFile + " postew w procentach" +  " ::::" +  actualProgress);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        free = true;
    }

    @Override
    public void run() {
        saveFile();
    }
}
