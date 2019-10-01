package dad.javafx.cambiodivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisaApp extends Application {

	private TextField origenText;
	private TextField destinoText;
	private ComboBox<String> origenModesCombo;
	private ComboBox<String> destinoModesCombo;
	private Button cambiarButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		origenText = new TextField();
		origenText.setPromptText("0");
		origenText.setMaxWidth(50);

		origenModesCombo = new ComboBox<String>();
		origenModesCombo.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		origenModesCombo.setPromptText("Divisa");

		destinoText = new TextField();
		destinoText.setPromptText("0");
		destinoText.setMaxWidth(50);
		destinoText.setEditable(false);

		destinoModesCombo = new ComboBox<String>();
		destinoModesCombo.getItems().addAll("Euro", "Libra", "Dolar", "Yen");
		destinoModesCombo.setPromptText("Divisa");

		cambiarButton = new Button("Cambiar");
		cambiarButton.setDefaultButton(true);
		cambiarButton.setOnAction(e -> onLoginButtonAction(e));

		HBox origenBox = new HBox(5, origenText, origenModesCombo);
		origenBox.setAlignment(Pos.CENTER);

		HBox destinoBox = new HBox(5, destinoText, destinoModesCombo);
		destinoBox.setAlignment(Pos.CENTER);

		HBox buttonBox = new HBox(5, cambiarButton);
		buttonBox.setAlignment(Pos.CENTER);

		VBox root = new VBox(5, origenBox, destinoBox, buttonBox);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onLoginButtonAction(ActionEvent e) {
		try {
			double cantidad = Double.parseDouble (origenText.getText());
			String comboOrigen = origenModesCombo.getSelectionModel().getSelectedItem();
			String comboDestino = destinoModesCombo.getSelectionModel().getSelectedItem();
			
			Divisa euro = new Divisa("Euro", 1.0);
			Divisa libra = new Divisa("Libra", 0.8873);
			Divisa dolar = new Divisa("Dolar", 1.2007);
			Divisa yen = new Divisa("Yen", 133.59);
			Divisa origen = yen;
			Divisa destino = dolar; 
			
			switch (comboOrigen) {
			case "Euro":
				origen=euro;
				break;
			case "Libra":
				origen=libra;
				break;
			
			case "Dolar":
				origen=dolar;
				break;
			
			case "Yen":
				origen=yen;
				break;
			}
			
			switch (comboDestino) {
			case "Euro":
				destino=euro;
				break;
			
			case "Libra":
				destino=libra;
				break;

			case "Dolar":
				destino=dolar;
				break;
			
			case "Yen":
				destino=yen;
				break;
			}
			String resultado = ""+(Divisa.fromTo(origen,  destino,  cantidad));
			destinoText.setText(resultado);
			
		} catch (NumberFormatException e1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("CambioDivisaApp");
			alert.setHeaderText("Error");
			alert.setContentText("El numero introducido no es valido");
			alert.showAndWait();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}