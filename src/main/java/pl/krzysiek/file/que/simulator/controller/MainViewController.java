package pl.krzysiek.file.que.simulator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.krzysiek.file.que.simulator.model.HardDrive;
import pl.krzysiek.file.que.simulator.model.HardDriveGui;
import pl.krzysiek.file.que.simulator.model.QueHandler;
import pl.krzysiek.file.que.simulator.model.UserContainer;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private static final Logger logger = LogManager.getLogger(MainViewController.class);

    @FXML
    private Button btnGenerate;

    @FXML
    private ListView<String> listOfUsers;

    @FXML
    private Label nameFile1;

    @FXML
    private Label nameFile2;

    @FXML
    private Label nameFile3;

    @FXML
    private Label nameFile4;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;

    @FXML
    private ProgressBar progressBar3;

    @FXML
    private ProgressBar progressBar4;

    @FXML
    private Label userId1;

    @FXML
    private Label userId2;

    @FXML
    private Label userId3;

    @FXML
    private Label userId4;

    private UserContainer userContainer;
    private List<HardDrive> hardDrives = new LinkedList<>();


    private List<HardDriveGui> hardDriveGuis = new LinkedList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hardDriveGuis.add(new HardDriveGui(nameFile1, progressBar1, userId1));
        hardDriveGuis.add(new HardDriveGui(nameFile2, progressBar2, userId2));
        hardDriveGuis.add(new HardDriveGui(nameFile3, progressBar3, userId3));
        hardDriveGuis.add(new HardDriveGui(nameFile4, progressBar4, userId4));

        userContainer = new UserContainer();
        userContainer.generateUsers();

        QueHandler queHandler = new QueHandler();
        queHandler.setUserContainer(userContainer);

        for (int i = 0; i < 4; i++) {
            hardDrives.add(new HardDrive());
            hardDrives.get(i).setFree(true);
            hardDrives.get(i).setName("dysk" + i);
        }

        queHandler.setHardDrives(hardDrives);

        logger.info("generate button");
        listOfUsers.getItems().clear();
        userContainer.getUsers().forEach(user -> listOfUsers.getItems().add(user.toString()));

        Thread threadRefreshUserList = new Thread(this::refreshUserList);
        threadRefreshUserList.setDaemon(true);
        threadRefreshUserList.start();

        Thread threadHardDrive1 = new Thread(this::refreshHardDisk);
        threadHardDrive1.setDaemon(true);
        threadHardDrive1.start();

        Thread queThread = new Thread(queHandler);
        queThread.setDaemon(true);
        queThread.start();


        btnGenerate.setOnAction(actionEvent -> {
            logger.info("click generate button");
            listOfUsers.getItems().clear();
            userContainer.getUsers().clear();
            userContainer.generateUsers();
            userContainer.getUsers().forEach(user -> listOfUsers.getItems().add(user.toString()));

        });
    }

    private void refreshUserList() {
        while (true) {
            Platform.runLater(() -> {
                if (listOfUsers != null && userContainer != null && userContainer.getUsers() != null) {
                    listOfUsers.getItems().clear();
                    userContainer.getUsers().forEach(user -> listOfUsers.getItems().add(user.toString()));
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshHardDisk() {


        while (true) {
            Platform.runLater(() -> {
                for (int i = 0; i < 4; i++) {
                    if (hardDrives.get(i) != null
                            && hardDrives.get(i).getActualFile() != null
                            && hardDrives.get(i).getActualFile().getFilename() != null) {
                        hardDriveGuis.get(i).getNameFileLabel().setText(hardDrives.get(i).getActualFile().getFilename());
                        hardDriveGuis.get(i).getProgressBar().setProgress(hardDrives.get(i).getActualProgress());
                    } else {
                        hardDriveGuis.get(i).getNameFileLabel().setText("-");
                        hardDriveGuis.get(i).getProgressBar().setProgress(0);
                    }
                }
            });
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
