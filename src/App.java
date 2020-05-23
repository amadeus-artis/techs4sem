
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
        public void start(Stage mainStage) {
            Scene scene = new Scene(initInterface(), 640, 480);
            mainStage.setScene(scene);
            mainStage.show();
    }

    private VBox initInterface() {
        VBox vb = new VBox();
        Button b = new Button("Hello");
        TextField tf = new TextField();
        Label l = new Label("Some text");
        b.addEventHandler(ActionEvent.ACTION, ActionEvent -> {
            l.setText(tf.getText());
        } );
        
        vb.getChildren().addAll(b, tf, l);
        return vb;
    }
}
