package javafxapplication10;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sukhm
 */
public class AppointmentCancellationMessageController implements Initializable {

    @FXML
    private Button CloseBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
   public void CloseBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("User_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("User_Options");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
       
   }
}
