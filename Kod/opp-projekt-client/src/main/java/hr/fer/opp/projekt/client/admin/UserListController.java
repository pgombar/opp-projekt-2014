package hr.fer.opp.projekt.client.admin;

import hr.fer.opp.projekt.client.Controller;
import hr.fer.opp.projekt.common.model.Korisnik;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class UserListController implements Controller {

	private AdminApp mainApp;
	@FXML
	private ListView<Korisnik> korisnici;
	private List<Korisnik> prikaz;
	private ObservableList<Korisnik> data = FXCollections.observableArrayList();

	public UserListController() {
	}

	@FXML
	private void initialize() {
		korisnici.setItems(data);
		korisnici.setCellFactory(new Callback<ListView<Korisnik>, ListCell<Korisnik>>() {

			@Override
			public ListCell<Korisnik> call(ListView<Korisnik> p) {
				ListCell<Korisnik> cell = new ListCell<Korisnik>() {
					@Override
					protected void updateItem(Korisnik t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							try {
								FXMLLoader loader = new FXMLLoader();
								loader.setLocation(this.getClass().getClassLoader()
										.getResource("fxml/admin/ListItemLayout.fxml"));
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

	}

	public void setMainApp(Application mainApp) {
		this.mainApp = (AdminApp) mainApp;
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

	public ListView<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(ListView<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

}
