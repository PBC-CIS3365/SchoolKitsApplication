package sample.classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Loader {

    public String fxmlFIle;
    public ActionEvent actionEvent;

    public Loader() {
    }

    public void openNewScene(String fxmlFile, ActionEvent actionEvent) throws IOException {
        this.fxmlFIle= fxmlFile;
        this.actionEvent=actionEvent;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlFile));

        Parent GUI = loader.load();
        Scene scene = new Scene(GUI);

        Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
}
