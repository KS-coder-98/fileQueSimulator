module pl.krzysiek {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;
    requires log4j;

    exports pl.krzysiek.file.que.simulator to javafx.graphics, javafx.fxml, javafx.controls;
    opens pl.krzysiek.file.que.simulator.controller to javafx.fxml;
}