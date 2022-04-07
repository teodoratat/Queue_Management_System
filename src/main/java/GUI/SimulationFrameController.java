package GUI;


import BussinessLogic.SimulationManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationFrameController implements Initializable {
    @FXML
    private TextArea log;

    public void initialize(URL url, ResourceBundle rb){

    }

    public void put(String message) {
        try {
            SimulationManager.fileSimulator.append(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(message != null){
            Platform.runLater(() -> {
                log.appendText(message);
            });
        }
    }

    public String getLog() {
        return log.getText();
    }
}

