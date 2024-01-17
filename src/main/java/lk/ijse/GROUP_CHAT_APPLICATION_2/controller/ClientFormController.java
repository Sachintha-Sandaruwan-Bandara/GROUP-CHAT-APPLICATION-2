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

    private ChatClient chatClient;

    String name;

    public void initialize() {
        chatterName.setText(name);
        chatClient = new ChatClient(name, this);
        chatClient.start();
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        String message = txtField.getText().trim();
        if (!message.isEmpty()) {
            // Send the message to the server
            chatClient.sendMessage(message);

            // Optionally, you can also update the local UI immediately
            updateChatArea(name + ": " + message);

            // Clear the text field
            txtField.clear();
        }
    }

    // Method to update the local UI with received messages
    public void updateChatArea(String message) {

        System.out.println(message);
        // You need to implement this method based on your UI structure
        // For example, you might add a new Text node to the VBox or update a TextArea
        // This method will be called by the ChatClient when a new message is received
        // and you want to update the UI.
    }
}
