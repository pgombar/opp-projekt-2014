package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.profile.ProfileController;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UserListController {

	private AdminApp mainApp;
	@FXML
	private ListView<Korisnik> listView;
	private ObservableList<Korisnik> data = FXCollections.observableArrayList();
	
	public UserListController() {
	}
	
	@FXML
	private void initialize() {
		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Korisnik>, ListCell<Korisnik>>(){
			 
            @Override
            public ListCell<Korisnik> call(ListView<Korisnik> p) {
            	ListCell<Korisnik> cell = new ListCell<Korisnik>() {
            		  @Override
                      protected void updateItem(Korisnik t, boolean bln) {
                          super.updateItem(t, bln);
                          if(t != null) {
	           	   	        try {
	           		   	        FXMLLoader loader = new FXMLLoader();
	           		   	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/admin/ListItemLayout.fxml"));
	           					Parent userList = (Parent) loader.load();
	           			        ListItemController controller = loader.getController();
	           			        controller.setMainApp(UserListController.this.mainApp);
	           			        controller.setKorisnik(t);
	           			        
	           			        setGraphic(userList);
	           				} catch (IOException e) {
	           					e.printStackTrace();
	           				}
	
	                      } 
            		  }
            	};
            	cell.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                    	try {
                    		FXMLLoader loader = new FXMLLoader();
                    		loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ProfileLayout.fxml"));
                    		Parent profile = (Parent) loader.load();
                    		ProfileController controller = loader.getController();
                    		controller.setMainApp(UserListController.this.mainApp);
                    		profile.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());
                    		Korisnik korisnik = listView.getSelectionModel().getSelectedItem();
                    		controller.setKorisnik(korisnik);
                    		
                    		Stage stage = new Stage();
                    		stage.setTitle(korisnik.getIme() + " " + korisnik.getPrezime());
                    		Scene scene = new Scene(profile);
                			stage.setScene(scene);
                			stage.show();
                    	} catch (IOException e) {
                    		e.printStackTrace();
                    	}
                    }
                });
            	return cell;
            }
        });
	}
	
    public void setMainApp(AdminApp adminApp) {
        this.mainApp = adminApp;
    }
    
    public void setList(List<Korisnik> korisnici) {
    	data.clear();
    	data.addAll(korisnici);
    }
    
    public void add(Korisnik korisnik) {
    	data.add(korisnik);
    }
    
}
