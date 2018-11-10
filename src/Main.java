
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller=loader.getController();
        Controller.hash=new Hash();
        VBox fistBlock=FXMLLoader.load(getClass().getResource("block.fxml"));
        ((HBox)((ScrollPane)(((BorderPane)root).getCenter())).getContent()).getChildren().add(fistBlock);
        controller.initApplication();
        primaryStage.setTitle("Assignment 3");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
