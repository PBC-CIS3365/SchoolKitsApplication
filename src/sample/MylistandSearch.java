package sample;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MylistandSearch implements Initializable {


    public JFXButton b1;
    public JFXButton b2;
    public AnchorPane one_pane;
    public AnchorPane two_pane;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }



    public void list_action(MouseEvent mouseEvent) {
       if(one_pane.isVisible() && !(two_pane.isVisible())){
           System.out.println("good");
       }else{
           one_pane.setVisible(true);
           two_pane.setVisible(false);
       }

    }

    public void searchAction(MouseEvent mouseEvent) {
        if(two_pane.isVisible() && !(one_pane.isVisible())){
            System.out.println("good2");
        }else{
            one_pane.setVisible(false);
            two_pane.setVisible(true);
        }

    }
}
