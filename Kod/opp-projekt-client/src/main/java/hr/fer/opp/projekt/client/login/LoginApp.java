package hr.fer.opp.projekt.client.login;

import hr.fer.opp.projekt.client.communication.EventChannel;
import hr.fer.opp.projekt.client.communication.OcsfEventChannel;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.lloseng.ocsf.client.ObservableClient;

public class LoginApp extends Application {

	private Stage stage;
	private Parent root;
	private EventChannel channel;
	
	@Override
	public void start(Stage stage) throws Exception {
        final ObservableClient client = new ObservableClient("0.0.0.0", 5000);
        client.openConnection();

        this.channel = new OcsfEventChannel(client);

		this.stage = stage;
		
		this.stage.setTitle("Login");
		initRootLayout();
	}
	
	private void initRootLayout() {
        try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(LoginApp.class.getResource("/fxml/welcome/WelcomeLayout.fxml"));
			root = (Parent) loader.load();
			
			LoginController controller = (LoginController) loader.getController();
			controller.setMainApp(this);
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public EventChannel getChannel() {
		return channel;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
