package Indodax;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage Stage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("IndodaxPagination.fxml"));
        Scene scene = new Scene(loader.load());
        Stage.setScene(scene);
        Stage.setTitle("Indodax");
        Stage.show();
        
    }
    public static void main(String[] args){
        launch(args);
    }
}


