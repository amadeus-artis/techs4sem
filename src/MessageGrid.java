import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MessageGrid extends Application {
    @Override
    public void start(Stage mainStage) {
        Scene scene = new Scene(initInterface(), 640, 480);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private GridPane initInterface() {
        GridPane bgPane = new GridPane();

        Button sendButton = new Button("Send");
        TextArea area = new TextArea();
        TextField field = new TextField();
        Label contactsHead = new Label("Contacts");
        ListView<String> contactList = new ListView<>();

        bgPane.add(area, 0, 0, 2, 2);
        bgPane.add(field, 0, 2);
        bgPane.add(sendButton, 1, 2);
        bgPane.add(contactsHead, 2, 0);
        bgPane.add(contactList, 2, 1, 1, 2);

        ColumnConstraints column1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints();
        bgPane.getColumnConstraints().addAll(column1, column2, column2);

        RowConstraints row1 = new RowConstraints(0, 100, Double.MAX_VALUE);
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints();
        bgPane.getRowConstraints().addAll(row2, row1, row2);

        contactList.setPrefHeight(0);

        return bgPane;
    }
}
