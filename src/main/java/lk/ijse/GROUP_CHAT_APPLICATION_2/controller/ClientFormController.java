package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/17/2024 - 4:53 PM 
*/
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
public class ClientFormController {
    @FXML
    private Label chatterName;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtField;

    @FXML
    private VBox vBox;

    @FXML
    void btnSendOnAction(ActionEvent event) {

    }
}