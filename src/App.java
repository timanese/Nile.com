// Name: Tim Yang
// Course: CNT 4714-Spring 2022
// Assignment title: Project 1- Event-driven Enterprise Simulation
// Date: Sunday january 30, 2022


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.*;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // to create additional stages do this :
        // Stage stage = new Stage();

        // Have a group and set the group to our scene.

        // Group root = new Group();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
            Scene scene = new Scene(root, Color.LIGHTBLUE);

            // linking the style sheet to our scene / the inside of our window
            String css = this.getClass().getResource("stylesheet.css").toExternalForm();
            scene.getStylesheets().add(css);

            // Set the icon of the stage 
            Image icon = new Image("P_icon.png");
            stage.getIcons().add(icon);
    
            stage.setResizable(false);
    
            stage.setTitle("Nile Dot Com - Spring 2022");

            
            stage.setScene(scene); 

            stage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

}
