package pl.krzysiek.file.que.simulator.model;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HardDriveGui {

    private Label nameFileLabel;
    private ProgressBar progressBar;
    private Label userIdLabel;
}
