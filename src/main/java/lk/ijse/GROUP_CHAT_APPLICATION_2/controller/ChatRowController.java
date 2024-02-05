package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;
/* 
    @author Sachi_S_Bandara
    @created 1/17/2024 - 7:29 PM 
*/

import com.vdurmont.emoji.EmojiParser;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ChatRowController {
    @FXML
    private Text txtReceivedMsg;
    @FXML
    private AnchorPane RowPane;
    @FXML
    private ImageView recImg;

    @FXML
    private ImageView sndImg;
    @FXML
    private Text txtMsg;

    public void setTxtMsg(String msg) {


        this.txtMsg.setText(msg);

    }

    public void setTxtReceivedMsg(String txtReceivedMsg) {
        this.txtReceivedMsg.setText(txtReceivedMsg);
    }
    public void setRecImg(String imageAsString) throws IOException {
        Image image = convertStringToImage(imageAsString);
        recImg.setImage(image);
        recImg.setFitHeight(300);
        recImg.setFitWidth(200);

    }
    public ImageView getSndImg() throws IOException {
        return sndImg;
    }
    private Image convertStringToImage(String imageAsString) throws IOException {
        byte[] imageBytes = Base64.getDecoder().decode(imageAsString);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        return new Image(inputStream);
    }
}
