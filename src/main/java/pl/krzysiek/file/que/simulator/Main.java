package pl.krzysiek.file.que.simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.krzysiek.file.que.simulator.model.UserContainer;

public class Main extends Application {

    private static final Logger logger = LogManager.getLogger(Main.class);


    @Override
    public void start(Stage stage) throws Exception {

        VBox mainPane = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        UserContainer userContainer = new UserContainer();
        userContainer.generateUsers();
        logger.info("generated users");
        logger.info(userContainer.getUsers());
        launch(args);
    }
}
