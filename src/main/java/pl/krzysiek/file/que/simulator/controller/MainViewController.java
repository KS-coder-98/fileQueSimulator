package pl.krzysiek.file.que.simulator.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private Button btnGenerate;

    @FXML
    private ListView<?> listOfUsers;

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

    }
}
