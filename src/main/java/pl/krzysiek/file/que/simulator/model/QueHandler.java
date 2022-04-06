package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QueHandler implements Runnable {

    private UserContainer userContainer;
    private List<HardDrive> hardDrives = new LinkedList<>();


    @Override
    public void run() {
        while (true) {
            var freeHardDrives = hardDrives
                    .stream().filter(HardDrive::getFree).collect(Collectors.toList());
            if (!freeHardDrives.isEmpty()) {
                HardDrive hardDrive = freeHardDrives.get(0);
                LinkedList<File> files = new LinkedList<>();
                userContainer.getUsers().stream()
                        .filter(user -> !user.getFiles().isEmpty())
                        .forEach(user -> files.add(user.getFiles().get(0)));


                for (var f : files) {
                    long l = Duration.between(f.getStartWaitingTime(), LocalDateTime.now()).toHours();
                    var temp = Math.sqrt(l) / userContainer.getUsers().size() + Math.pow(f.getSizeOfFile(), -1)
                            * userContainer.getUsers().size();
                    f.setPriority(temp);
                }
                files.sort(Comparator.comparing(File::getPriority, Comparator.reverseOrder()));
                hardDrive.setActualFile(files.get(0));


                userContainer.getUsers().forEach(user -> user.getFiles().remove(files.get(0)));
                userContainer.setUsers(userContainer.getUsers().stream().filter(user -> !user.getFiles().isEmpty())
                        .collect(Collectors.toList()));
                hardDrive.setFree(false);
                new Thread(hardDrive).start();

            }
            if (userContainer.getUsers().isEmpty()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
