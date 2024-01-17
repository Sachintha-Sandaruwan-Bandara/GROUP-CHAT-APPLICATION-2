package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/17/2024 - 7:29 PM 
*/

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ChatRowController {
    @FXML
    private AnchorPane RowPane;

    @FXML
    private Text txtMsg;

    public void setTxtMsg(String msg) {
        this.txtMsg.setText(msg);
    }
}
