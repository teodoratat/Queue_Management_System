package Application;

import GUI.SimulationFrameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static javafx.application.Application.launch;

public class App extends Application {
    private static Stage primaryStage;
    public static SimulationFrameController simulationFrameController;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Queue Management System");
        App.jumpToView("queue.fxml", 756, 406);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void jumpToView(String view) {
        jumpToView(view, 400, 500);
    }

    public static void jumpToView(String view, int xSize, int ySize) {
        try {
            URL url = App.class.getResource(view);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), xSize, ySize);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message){
        simulationFrameController.put(message);

    }
}