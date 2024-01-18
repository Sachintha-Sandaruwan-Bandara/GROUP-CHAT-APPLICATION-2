package lk.ijse.GROUP_CHAT_APPLICATION_2.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.lang3.StringEscapeUtils;


import java.io.*;

public class ClientFormController {
    @FXML
    private ImageView imojiImage1;

    @FXML
    private ImageView imojiImage2;

    @FXML
    private ImageView imojiImage3;
    @FXML
    private Pane imojiPane;
    @FXML
    private Label chatterName;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtField;

    @FXML
    private VBox vBox;

    private ChatClient chatClient;
    private boolean isImojiPaneVisible = false;

    String name;

    public void initialize() {
        imojiPane.setVisible(false);
        imojiEvents();
        chatterName.setText(name);
        chatClient = new ChatClient(name, this);
        chatClient.start();
    }

    private void imojiEvents() {
        addSmoothTransition(imojiImage1);
        addSmoothTransition(imojiImage2);
        addSmoothTransition(imojiImage3);

        imojiImage1.setOnMouseClicked(mouseEvent -> {
            String imoji1="\uD83D\uDE0B";
            txtField.appendText(imoji1);
        });
        imojiImage2.setOnMouseClicked(mouseEvent -> {
            String heartEmoji = "\u2764\uFE0F";
            txtField.appendText(heartEmoji);

        });
        imojiImage3.setOnMouseClicked(mouseEvent -> {
            String thumbsUpEmoji = "\uD83D\uDC4D\uD83C\uDFFD";
            txtField.appendText(thumbsUpEmoji);

        });
    }

    private void addSmoothTransition(ImageView imageView) {
        ScaleTransition scaleIn = createScaleTransition(imageView, 1, 1.50);
        ScaleTransition scaleOut = createScaleTransition(imageView, 1.15, 1);

        imageView.setOnMouseEntered(event -> scaleIn.play());
        imageView.setOnMouseExited(event -> scaleOut.play());
    }

    private ScaleTransition createScaleTransition(ImageView imageView, double fromValue, double toValue) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), imageView);
        scaleTransition.setFromX(fromValue);
        scaleTransition.setFromY(fromValue);
        scaleTransition.setToX(toValue);
        scaleTransition.setToY(toValue);
        return scaleTransition;
    }
    private static final Duration ANIMATION_DURATION = Duration.seconds(0.5);
    private static final double ANIMATION_DISTANCE = 50; // Adjust as needed

    public static void animateBottomToTop(Pane imojiPane) {
        TranslateTransition translateTransition = new TranslateTransition(ANIMATION_DURATION, imojiPane);
        translateTransition.setFromY(ANIMATION_DISTANCE);
        translateTransition.setToY(0);

        translateTransition.play();
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
        String msg = breakStringIntoLines(message, 25);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/chatRow.fxml"));
            Node node = fxmlLoader.load();

            // Access the controller and set the message
            ChatRowController chatRowController = fxmlLoader.getController();

            if (message.startsWith("me")){
                chatRowController.setTxtMsg(msg);
            }else {
                chatRowController.setTxtReceivedMsg(msg);
            }


            // Add the new chat row to the VBox
            vBox.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSndImgOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));


        Stage stage = (Stage) imojiPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getPath());

// image convert to byte array
        byte [] imageBytes = readImageToByteArray(file);


    }
    private void displayImage(ImageView imageView, byte[] imageData) {
        if (imageData != null && imageData.length > 0) {
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageData)) {
                Image image = new Image(byteArrayInputStream);
                imageView.setImage(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private byte[] readImageToByteArray(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }




    @FXML
    void btnSndImojiOnAction(ActionEvent event) {
        if (!isImojiPaneVisible) {
            imojiPane.setVisible(true);
            animateBottomToTop(imojiPane);
        } else {
            imojiPane.setVisible(false);
        }

        // Toggle the visibility state
        isImojiPaneVisible = !isImojiPaneVisible;
    }
    private static String breakStringIntoLines(String input, int maxCharacters) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i += maxCharacters) {
            int endIndex = Math.min(i + maxCharacters, input.length());
            result.append(input, i, endIndex).append("\n");
        }

        return result.toString();
    }

}
