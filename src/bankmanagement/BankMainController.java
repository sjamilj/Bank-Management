package bankmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class BankMainController implements Initializable {
    @FXML
    private ToggleButton acntHoldSceneBtn;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleButton brchEmpSceneBtn;
    @FXML
    private ToggleButton tranSceneBtn;
    @FXML
    private BorderPane rootLayout;
    @FXML
    private ToggleButton seviceBtn;
   
   
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         changeScene("AccountHolderScene.fxml");
    } 
    @FXML
    private void setAcntHoldScene(ActionEvent event) {
         changeScene("AccountHolderScene.fxml");
    }

    @FXML
    private void setBrchEmpScene(ActionEvent event) {
         changeScene("BranchEmployeeScene.fxml");
    }

    @FXML
    private void setTranScene(ActionEvent event) {
         changeScene("TransactoinScene.fxml");
    }
    
     public  void changeScene(String scenePath){
        
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource(scenePath));
        AnchorPane pane = new AnchorPane();
    try{
            pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
        }
        catch(Exception e){
        }
     
    }

    @FXML
    private void setServiceScene(ActionEvent event) {
         changeScene("ServiceScene.fxml");
        
    }

    
}
