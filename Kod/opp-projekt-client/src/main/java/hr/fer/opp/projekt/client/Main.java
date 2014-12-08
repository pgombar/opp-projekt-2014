package hr.fer.opp.projekt.client;

import com.lloseng.ocsf.client.ObservableClient;
import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;
import hr.fer.opp.projekt.common.odgovor.LoginOdgovor;
import hr.fer.opp.projekt.common.zahtjev.LoginZahtjev;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
        client.openConnection();

        final EventChannel channel = new OcsfEventChannel(client);

        stage.setTitle("Umjetnine");

        Button button = new Button();
        button.setText("Hello world!");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello!");
                LoginOdgovor odgovor = channel.sendAndWait(new LoginZahtjev("pero", "peric"));

                System.out.println("U konacnici, dobio:");
                System.out.println(odgovor);
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        stage.setScene(new Scene(root, 300, 250));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
