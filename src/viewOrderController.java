
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class viewOrderController {
    @FXML
    private AnchorPane viewOrderPane;


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

}
