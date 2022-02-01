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
        // File directory = new File("./");
        // System.out.println("Will this print\n");
        // System.out.println(directory.getAbsolutePath());
        // or even this 
        // Application.launch(args) because it comes from the application class

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
    
            // set the size of the stage 
            //stage.setWidth(700);
            //stage.setHeight(500);
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
