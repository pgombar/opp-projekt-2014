package hr.fer.opp.projekt.client.main;

import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class UserListController {

	private MainApp mainApp;
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
	           		   	        loader.setLocation(MainApp.class.getResource("/fxml/ListItemLayout.fxml"));
	           					Parent userList = (Parent) loader.load();
	           			        ListItemController controller = loader.getController();
	           			        controller.setMainApp(UserListController.this.mainApp);
	           			        System.out.println(t);
	           			        controller.setKorisnickoIme(t.getKorisnickoIme());
	           			        controller.setImePrezime(t.getIme() + " " + t.getPrezime());
	           			        controller.setStatus(t.getOsobniStatus());
	           			        
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
                        mainApp.showProfile(listView.getSelectionModel().getSelectedItem().getId());
                    }
                });
            	return cell;
            }
        });
	}
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    public void setList(List<Korisnik> korisnici) {
    	data.clear();
    	data.addAll(korisnici);
    }
    
    public void add(Korisnik korisnik) {
    	data.add(korisnik);
    }
    
    class UserCell extends ListCell<Korisnik> {
    	
    	   @Override
           protected void updateItem(Korisnik t, boolean bln) {
               super.updateItem(t, bln);
	   	        try {
		   	        FXMLLoader loader = new FXMLLoader();
		   	        loader.setLocation(MainApp.class.getResource("/fxml/ListItemLayout.fxml"));
					Parent userList = (Parent) loader.load();
			        ListItemController controller = loader.getController();
			        controller.setMainApp(UserListController.this.mainApp);
			        controller.setKorisnickoIme(t.getKorisnickoIme());
			        controller.setImePrezime(t.getIme() + " " + t.getPrezime());
			        controller.setStatus(t.getOsobniStatus());
			        
			        setGraphic(userList);
				} catch (IOException e) {
					e.printStackTrace();
				}

           }
    }
}
