package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/17/2024 - 7:29 PM 
*/

import com.vdurmont.emoji.EmojiParser;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChatRowController {
    @FXML
    private Text txtReceivedMsg;
    @FXML
    private AnchorPane RowPane;

    @FXML
    private Text txtMsg;

    public void setTxtMsg(String msg) {


        this.txtMsg.setText(msg);

    }

    public void setTxtReceivedMsg(String txtReceivedMsg) {
        this.txtReceivedMsg.setText(txtReceivedMsg);
    }
}
