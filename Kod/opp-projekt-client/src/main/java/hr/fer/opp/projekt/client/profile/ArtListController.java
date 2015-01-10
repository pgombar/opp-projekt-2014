package hr.fer.opp.projekt.client.profile;

import hr.fer.opp.projekt.client.main.MainApp;
import hr.fer.opp.projekt.common.model.Umjetnina;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

            	return cell;
            }
        });
		
		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Umjetnina>() {

		    @Override
		    public void changed(ObservableValue<? extends Umjetnina> observable, Umjetnina oldValue, Umjetnina newValue) {
		    		Umjetnina umjetnina = listView.getSelectionModel().getSelectedItem();
	    			if(umjetnina == null) return;

            		Stage stage = new Stage();
            		stage.setResizable(false);
            		stage.setTitle(umjetnina.getIme());
            		StackPane root = new StackPane();
            		root.getChildren().add(new ImageView(SwingFXUtils.toFXImage(umjetnina.getSlika(), null)));

        			stage.setScene(new Scene(root));
        			stage.show();
		    		listView.getSelectionModel().clearSelection();
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
