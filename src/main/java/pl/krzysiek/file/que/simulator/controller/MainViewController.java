package pl.krzysiek.file.que.simulator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.krzysiek.file.que.simulator.model.UserContainer;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        UserContainer userContainer = new UserContainer();
        userContainer.generateUsers();



        btnGenerate.setOnAction(actionEvent -> {
            logger.info("click generate button");
            listOfUsers.getItems().clear();
            userContainer.getUsers().clear();
            userContainer.generateUsers();
            userContainer.getUsers().forEach(user -> listOfUsers.getItems().add(user.toString()));
        });
    }
}
