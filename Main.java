package javafxapplication10;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
        Parent parent  = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        
        Scene scene = new Scene(parent);
        primaryStage.setTitle("Home Page");        
        primaryStage.setScene(scene);
        primaryStage.show();
    }   catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }}
   
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
