package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QueHandler implements Runnable {

    private UserContainer userContainer;
    //    List<Thread> hardThreads  = new LinkedList<>();
    private List<HardDrive> hardDrives = new LinkedList<>();


    @Override
    public void run() {
        while (true) {
            var freeHardDrives = hardDrives
                    .stream().filter(HardDrive::getFree).collect(Collectors.toList());
//            System.out.println("wolne dyski: " + freeHardDrives);
            if (!freeHardDrives.isEmpty()) {
                HardDrive hardDrive = freeHardDrives.get(0);
                if (!userContainer.getUsers().get(0).getFiles().isEmpty()) {
                    LinkedList<File> files = new LinkedList<>();
                    for ( var user : userContainer.getUsers() ){
                        if ( !user.getFiles().isEmpty() ){
                            files.add(user.getFiles().get(0));
                        }
                    }
                    files.sort(Comparator.comparingInt(File::getSizeOfFile));
                    hardDrive.setActualFile(files.get(0));
                    System.out.println(files.get(0));
                    hardDrive.setActualFile(files.get(0));

                    userContainer.getUsers().forEach(user -> user.getFiles().remove(files.get(0)));
                    hardDrive.setFree(false);
                    new Thread(hardDrive).start();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
