/*
    @author Sachi_S_Bandara
    @created 1/17/2024 - 1:56 PM 
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(Launcher.class.getResource("/view/clientForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //Image icon= new Image("images/logo.png");
        //stage.getIcons().add(icon);
        stage.setTitle("");
        stage.show();
    }
}
