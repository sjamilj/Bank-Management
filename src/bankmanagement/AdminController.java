package bankmanagement;

import DB.DBConnection;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AdminController implements Initializable {

    @FXML
    private Button loginBtn;

    @FXML
    private Button regbtn;

    @FXML
    private AnchorPane pane_login;

    @FXML
    private TextField lname;

    @FXML
    private Button SignInbtn;

    @FXML
    private PasswordField lpass;

    @FXML
    private AnchorPane pane_signup;

    @FXML
    private TextField sname;

    @FXML
    private TextField suname;

    @FXML
    private PasswordField spass;

    @FXML
    private ComboBox GenderType;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField nid;

    @FXML
    private TextField saddress;

    @FXML
    private Button SignupBtn;
    @FXML
    private TextField lCap;

    @FXML
    private TextField lCaptCha;

    @FXML
    private Button Reloadbtn;

    Connection c = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    @FXML
    private TextField sCap;
    @FXML
    private TextField sCaptCha;
    @FXML
    private Button Reloadbtn1;

    @FXML
    public void LoginPaneShow() {
        pane_login.setVisible(true);
        pane_signup.setVisible(false);
    }

    @FXML
    public void SignUpPaneShow() {
        pane_login.setVisible(false);
        pane_signup.setVisible(true);
    }

    @FXML
    private void Reload(ActionEvent event) {

        java.util.Random r = new java.util.Random();
        int start = 1000;
        int end = 10000;
        int result = r.nextInt(end - start) + start + 59;
        lCap.setText(String.valueOf(result));
        sCap.setText(String.valueOf(result));
    }

    @FXML
    void SignIn(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        c = (Connection) DBConnection.getConnection();
        String sql = "SELECT * FROM admin WHERE Username = ? AND Password = ? ";
        try {
            pst = (PreparedStatement) c.prepareStatement(sql);
            pst.setString(1, lname.getText());
            pst.setString(2, lpass.getText());
            rs = pst.executeQuery();
            if (lCaptCha.getText() == null ? lCap.getText() == null : lCaptCha.getText().equals(lCap.getText())) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username and Password Correct");
                    SignInbtn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource("BankMain.fxml"));
                    Stage mainStage = new Stage();
                    Scene scene = new Scene(root);
                    mainStage.setScene(scene);
                    mainStage.show();

                } else {
                    JOptionPane.showMessageDialog(null, "UserName and Password Not Correct");
                }
            } else {
                JOptionPane.showMessageDialog(null, "CaptCha isn't Correct. Try Again");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    LocalDate date = null;

    @FXML
    void SignUp(ActionEvent event) throws SQLException, ClassNotFoundException {
        //sCaptCha.getTxt() == sCap.getText() ;
        if (sCaptCha.getText() == null ? sCap.getText() == null : sCaptCha.getText().equals(sCap.getText())) {
            c = (Connection) DBConnection.getConnection();
            String sql;
            sql = "INSERT INTO admin(Name,Username,Password,Gender,DOB,Nid,Address) VALUES (?,?,?,?,?,?,?)";
            try {
                pst = (PreparedStatement) c.prepareStatement(sql);
                pst.setString(1, sname.getText());
                pst.setString(2, suname.getText());
                pst.setString(3, spass.getText());
                pst.setString(4, GenderType.getValue().toString());
                pst.setString(5, dob.getValue().format(DateTimeFormatter.ISO_ORDINAL_DATE));
                pst.setString(6, nid.getText());
                pst.setString(7, saddress.getText());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Account Created ");
                clear();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "CaptCha isn't Correct. Try Again");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GenderType.getItems().addAll("Male", "Female", "Other");

    }

    private void clear() {
        sname.clear();
        suname.clear();
        spass.clear();
        GenderType.setValue("");
        dob.setValue(LocalDate.now());
        nid.clear();
        saddress.clear();
        lCap.clear();
        sCap.clear();
        lCaptCha.clear();
        sCaptCha.clear();
    }

}
