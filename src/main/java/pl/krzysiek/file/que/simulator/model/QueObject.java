package pl.krzysiek.file.que.simulator.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueObject {

    private File file;
    private LocalDateTime startWaitingTime;
}
