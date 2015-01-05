package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Korisnik;
import hr.fer.opp.projekt.common.model.Umjetnina;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ArtListController {

	private MainApp mainApp;
	@FXML
	private ListView<Umjetnina> listView;
	private ObservableList<Umjetnina> data = FXCollections.observableArrayList();
	
	public ArtListController() {
	}
	
	@FXML
	private void initialize() {
		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Umjetnina>, ListCell<Umjetnina>>(){
			 
            @Override
            public ListCell<Umjetnina> call(ListView<Umjetnina> p) {
            	ListCell<Umjetnina> cell = new ListCell<Umjetnina>() {
            		  @Override
                      protected void updateItem(Umjetnina t, boolean bln) {
                          super.updateItem(t, bln);
                          if(t != null) {
	           	   	        try {
	           		   	        FXMLLoader loader = new FXMLLoader();
	           		   	        loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ArtListItemLayout.fxml"));
	           					Parent userList = (Parent) loader.load();
	           			        ArtListItemController controller = loader.getController();
	           			        controller.setMainApp(ArtListController.this.mainApp);
	           			        controller.setUmjetnina(t);
	           			        
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
                    	
                    	Umjetnina umjetnina = listView.getSelectionModel().getSelectedItem();
                		Stage stage = new Stage();
                		stage.setTitle(umjetnina.getIme());
                		Scene scene = new Scene(new AnchorPane());
            			stage.setScene(scene);
            			stage.show();
            			
                    	/*try {
                    		FXMLLoader loader = new FXMLLoader();
                    		loader.setLocation(this.getClass().getClassLoader().getResource("fxml/profile/ProfileLayout.fxml"));
                    		Parent profile = (Parent) loader.load();
                    		ProfileController controller = loader.getController();
                    		controller.setMainApp(ArtListController.this.mainApp);
                    		profile.getStylesheets().add(this.getClass().getClassLoader().getResource("menu.css").toExternalForm());
                    		Umjetnina umjetnina = listView.getSelectionModel().getSelectedItem();
                    		controller.setKorisnik(korisnik);
                    		
                    		
                    		
                    	} catch (IOException e) {
                    		e.printStackTrace();
                    	}*/
                    }
                });
            	return cell;
            }
        });
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setList(List<Umjetnina> umjetnine) {
    	data.clear();
    	data.addAll(umjetnine);
    }
    
    public void add(Umjetnina umjetnina) {
    	data.add(umjetnina);
    }
    
}
