package pl.krzysiek.file.que.simulator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.krzysiek.file.que.simulator.model.HardDrive;
import pl.krzysiek.file.que.simulator.model.QueHandler;
import pl.krzysiek.file.que.simulator.model.UserContainer;

import java.net.URL;
import java.util.ArrayList;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        Thread threadSimulatedFuel = new Thread(this::refreshUserList);
        threadSimulatedFuel.setDaemon(true);
        threadSimulatedFuel.start();

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

//            new Thread(queHandler).start();
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
                if (hardDrives.get(0).getActualFile().getFilename() != null) {
                    nameFile1.setText(hardDrives.get(0).getActualFile().getFilename());
                    progressBar1.setProgress(hardDrives.get(0).getActualProgress());
                } else {
                    nameFile1.setText("-");
                    progressBar1.setProgress(0);
                }
                if (hardDrives.get(1) != null && hardDrives.get(1).getActualFile() != null && hardDrives.get(1).getActualFile().getFilename() != null) {
                    nameFile2.setText(hardDrives.get(1).getActualFile().getFilename());
                    progressBar2.setProgress(hardDrives.get(1).getActualProgress());
                } else {
                    nameFile2.setText("-");
                    progressBar2.setProgress(0);
                }
                if (hardDrives.get(2).getActualFile().getFilename() != null) {
                    nameFile3.setText(hardDrives.get(2).getActualFile().getFilename());
                    progressBar3.setProgress(hardDrives.get(2).getActualProgress());
                } else {
                    nameFile3.setText("-");
                    progressBar3.setProgress(0);
                }
                if (hardDrives.get(3).getActualFile().getFilename() != null) {
                    nameFile4.setText(hardDrives.get(3).getActualFile().getFilename());
                    progressBar4.setProgress(hardDrives.get(3).getActualProgress());
                } else {
                    nameFile4.setText("-");
                    progressBar4.setProgress(0);
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
