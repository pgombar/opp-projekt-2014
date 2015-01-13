package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.client.profile.ProfileController;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UserListController implements Controller {

	private MainApp mainApp;
	@FXML
	private ListView<Korisnik> korisnici;
	private List<Korisnik> prikaz;
	private ObservableList<Korisnik> data = FXCollections.observableArrayList();
	
	public UserListController() {
	}
	
	@FXML
	private void initialize() {
		korisnici.setItems(data);
		korisnici.setCellFactory(new Callback<ListView<Korisnik>, ListCell<Korisnik>>(){
			 
            @Override
            public ListCell<Korisnik> call(ListView<Korisnik> p) {
            	ListCell<Korisnik> cell = new ListCell<Korisnik>() {
            		  @Override
                      protected void updateItem(Korisnik t, boolean bln) {
                          super.updateItem(t, bln);
                          if(t != null) {
	           	   	        try {
	           		   	        FXMLLoader loader = new FXMLLoader();
	           		   	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/main/ListItemLayout.fxml"));
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
            	return cell;
            }
        });
		
		korisnici.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Korisnik>() {

		    @Override
		    public void changed(ObservableValue<? extends Korisnik> observable, Korisnik oldValue, Korisnik newValue) {
		    	try {	
		    		if(korisnici.getSelectionModel().getSelectedItem() == null) return;
            		FXMLLoader loader = new FXMLLoader();
            		loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ProfileLayout.fxml"));
            		Parent profile = (Parent) loader.load();
            		ProfileController controller = loader.getController();
            		controller.setMainApp(UserListController.this.mainApp);
            		profile.getStylesheets().add(this.getClass().getClassLoader().getResource(mainApp.getSkin()).toExternalForm());
            		Korisnik korisnik = korisnici.getSelectionModel().getSelectedItem();
            		controller.setKorisnik(korisnik);
            		
            		Stage stage = new Stage();
            		stage.setResizable(false);
            		stage.setTitle(korisnik.getIme() + " " + korisnik.getPrezime());
            		Scene scene = new Scene(profile);
        			stage.setScene(scene);
        			stage.show();
        			
		    		korisnici.getSelectionModel().clearSelection();
            	} catch (IOException e) {
            		e.printStackTrace();
            	}
		    }
		});
	}
	
    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp) mainApp;
    }
    
    public void setList(List<Korisnik> korisnici) {
    	prikaz = korisnici;
    	data.clear();
    	data.addAll(korisnici);
    }
    
    public List<Korisnik> getPrikaz() {
    	return prikaz;
    }
    
    public void add(Korisnik korisnik) {
    	data.add(korisnik);
    }
    
}
