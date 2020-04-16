package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
          Parent root = FXMLLoader.load(getClass().getResource("Teacher_Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("listView.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("MylistandSearch.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("viewInventorytems.fxml"));

        primaryStage.setTitle("School Kits");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
