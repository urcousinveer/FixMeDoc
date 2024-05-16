package javafxapplication10;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


public class Doctor_Option_CheckMessagesController implements Initializable {

    @FXML
    private Button BackBtn;
    @FXML
    private TextArea displaytext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showmessages();
    }    
    
        
    @FXML
   public void BackBtnClicked(ActionEvent ev) throws SQLException{
     
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Doctor_Options.fxml"));
            Scene Dashboard = new Scene(parent);
            Stage dashboardStage = (Stage)((Node)ev.getSource()).getScene().getWindow();
            dashboardStage.setScene(Dashboard);
            dashboardStage.setTitle("Options");
        } catch (IOException ex) {
            Logger.getLogger(User_RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
           
       
   }
    
   
   @FXML
   public void showmessages(){
       
        try{
           Class.forName("com.mysql.jdbc.Driver");
       Connection con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/appointmentsysytem", "root", "");
       
       
         Statement  stmt = con.createStatement();
         
          int id = 0;
        
         String basicquery = "Select * from currentdoctor";
         
         ResultSet rsbasic = stmt.executeQuery(basicquery);
        
         while(rsbasic.next()){
             
             id = rsbasic.getInt(1);
             
         }
         
         String sql = "Select * from doctorinfo WHERE doctorID =" + id;
        
        ResultSet rs = stmt.executeQuery(sql);
        
        String x ="";
        
        while(rs.next()){
            
            x += rs.getString(9);
            
        }
   
         this.displaytext.setText(x);
             
           con.close();
            
       } catch (ClassNotFoundException | SQLException e){
           System.out.println(e);
       }
       
   }
}
