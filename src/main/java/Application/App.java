package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import Controller.Controller;
public class App extends Application {
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) {
        System.out.println(new Timestamp(System.currentTimeMillis()));
        primaryStage = stage;
        primaryStage.setTitle("Models.Polynomial Calculator");
        App.jumpToView("interface.fxml",552,663);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void jumpToView(String view){
        jumpToView(view,400,500);
    }
    public static void jumpToView(String view, int xSize, int ySize){
        try {
            URL url = App.class.getResource(view);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load(), xSize, ySize);
            primaryStage.setScene(scene);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
