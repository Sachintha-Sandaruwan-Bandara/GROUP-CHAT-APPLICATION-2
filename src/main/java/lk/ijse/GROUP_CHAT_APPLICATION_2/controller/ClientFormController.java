package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    void btnSendOnAction(ActionEvent event) throws IOException {
        String message = txtField.getText().trim();
        if (!message.isEmpty()) {
            // Send the message to the server
            chatClient.sendMessage(message);

            // Optionally, you can also update the local UI immediately
            updateChatArea("me: " + message);

            // Clear the text field
            txtField.clear();
        }
    }

    // Method to update the local UI with received messages
    public void updateChatArea(String message) {
        System.out.println(message);

        // Load a new chat row and add it to the VBox
        loadChatRow(message);
    }

    // Method to load a new chat row
    private void loadChatRow(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/chatRow.fxml"));
            Node node = fxmlLoader.load();

            // Access the controller and set the message
            ChatRowController chatRowController = fxmlLoader.getController();
            chatRowController.setTxtMsg(message);

            // Add the new chat row to the VBox
            vBox.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSndImgOnAction(ActionEvent event) {
        // You can implement image sending logic here
    }

    @FXML
    void btnSndImjiOnAction(ActionEvent event) {
        // You can implement emoji sending logic here
    }
}
