package GUI;

import Application.App;
import BussinessLogic.SelectionPolicy;
import BussinessLogic.SimulationManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class QueueManagementSystemController implements Initializable {
    @FXML
    Button startButton;
    @FXML
    Spinner<Integer> nrOfTasks;
    @FXML
    ComboBox<SelectionPolicy> strategyChoice;
    @FXML
    Spinner<Integer> nrOfServers;
    @FXML
    Spinner<Integer> minSerTime;
    @FXML
    Spinner<Integer> maxSerTime;
    @FXML
    Spinner<Integer> minArrTime;
    @FXML
    Spinner<Integer> maxArrTime;
    @FXML
    Spinner<Integer> simulationTime;
    public int timeLimit;   // maximum processing time - read from UI
    public int minServiceTime;
    public int maxServiceTime;
    public int numberOfServers;
    public int numberOfTasks;
    public int minArrivalTime;
    public int maxArrivalTime;
    public SelectionPolicy selectionPolicy;

    //SimulationManager simulationManager = new SimulationManager();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nrOfTasks.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        nrOfServers.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1));
        minArrTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 300, 1));
        maxArrTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 300, 1));
        minSerTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 300, 1));
        maxSerTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 300, 1));
        simulationTime.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 200, 1));

        strategyChoice.setItems(FXCollections.observableArrayList(
                SelectionPolicy.SHORTEST_TIME,
                SelectionPolicy.SHORTEST_QUEUE
        ));
    }

    private boolean validateInput() {
        numberOfServers = nrOfServers.getValue();
        numberOfTasks = nrOfTasks.getValue();
        maxArrivalTime = maxArrTime.getValue();
        minArrivalTime = minArrTime.getValue();
        minServiceTime = minSerTime.getValue();
        maxServiceTime = maxSerTime.getValue();
        timeLimit = simulationTime.getValue();
        selectionPolicy = strategyChoice.getValue();
        if (minArrivalTime > maxArrivalTime) {
            System.out.println(1 + " " + minArrivalTime + ">" + maxArrivalTime);
            return false;
        }
        if (minServiceTime > maxServiceTime) {
            System.out.println(2 + " " + minServiceTime + ">" + maxServiceTime);
            return false;
        }
        if (selectionPolicy == null) {
            System.out.println(3 + " null");
            return false;
        }
        return true;
    }

    @FXML
    public void onStartClick() {
        if (validateInput()) {
            try {
                Stage stage = App.getPrimaryStage();
                URL url = App.class.getResource("simulationFrame.fxml");
                FXMLLoader fxmlLoader = new FXMLLoader(url);
                Parent root = fxmlLoader.load();
                App.simulationFrameController = fxmlLoader.getController();
                Scene scene = new Scene(root, 698, 492);
                stage.setScene(scene);

                SimulationManager simulationManager = new SimulationManager(
                        timeLimit,
                        minServiceTime, maxServiceTime,
                        numberOfServers, numberOfTasks,
                        minArrivalTime, maxArrivalTime,
                        selectionPolicy
                );
                Thread t = new Thread(simulationManager);
                t.start();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

