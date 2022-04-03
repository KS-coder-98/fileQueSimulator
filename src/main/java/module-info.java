module pl.krzysiek {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires lombok;

    exports pl.krzysiek.file.que.simulator to javafx.graphics, javafx.fxml, javafx.controls;
    opens pl.krzysiek.file.que.simulator.controller to javafx.fxml;
}