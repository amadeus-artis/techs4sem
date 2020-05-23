import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class MessageBox extends Application {
    @Override
    public void start(Stage mainStage) {
        Scene scene = new Scene(initInterface(), 640, 480);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private HBox initInterface() {
        //------ Creating ------//
        VBox messagesBox = new VBox();
        VBox contactsBox = new VBox();
        HBox sendBox = new HBox();
        HBox messenger = new HBox();

        Button sendButton = new Button("Send");
        TextArea area = new TextArea();
        TextField field = new TextField();
        Label contactsHead = new Label("Contacts");
        ListView<String> contactsLabel = new ListView<>();

        //------ Events ------//
        EventHandler<ActionEvent> h = ActionEvent -> {
            area.appendText(field.getText() + "\n\n");
        };
        sendButton.addEventHandler(ActionEvent.ACTION, h);
        field.addEventHandler(ActionEvent.ACTION, h);

        //------ Settings ------//
        contactsLabel.setMinWidth(400);

        contactsBox.getChildren().addAll(contactsHead, contactsLabel);
        sendBox.getChildren().addAll(field, sendButton);
        messagesBox.getChildren().addAll(area, sendBox);

        HBox.setHgrow(field, Priority.ALWAYS);
        VBox.setVgrow(contactsLabel, Priority.ALWAYS);
        VBox.setVgrow(area, Priority.ALWAYS);

        messenger.getChildren().addAll(messagesBox, contactsBox);
        HBox.setHgrow(messagesBox, Priority.ALWAYS);

        return messenger;
    }

}
