// Name: Tim Yang
// Course: CNT 4714-Spring 2022
// Assignment title: Project 1- Event-driven Enterprise Simulation
// Date: Sunday january 30, 2022

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class viewOrderController implements Initializable {
    @FXML
    private AnchorPane viewOrderPane;
    @FXML
    private TextArea invoiceArea;


    @FXML
    void backToMain(ActionEvent event) throws IOException {
        // Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        // Stage viewOrderStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // Scene viewOrderScene = new Scene(root);
        // viewOrderStage.setScene(viewOrderScene);
        // viewOrderStage.show();

        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        viewOrderPane.getChildren().setAll(pane);
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        invoiceArea.setEditable(false);

        int j = 1;
        if (!Orderdata.invoice.isEmpty()) {
            for (String I : Orderdata.invoice) {
                invoiceArea.appendText(j++ +". "+ I + "\n");
            }
        }
        
    }

}
