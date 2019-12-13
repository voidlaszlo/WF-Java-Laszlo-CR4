package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        ObservableList<Product> items = FXCollections.observableArrayList(
                new Product("Salakis Schafmilchkäse", "200 Gramm Packung", 2.59, 1.99, "sample/1.jpg", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."),
                new Product("Pfeffer", "1 Stück", 2.59, 1.99, "sample/2.jpg", "hello"),
                new Product("Vöslauer", "11", 2.59, 1.99, "sample/3.jpg", "marhahusleve"),
                new Product("Zucker", "1", 2.59, 1.99, "sample/4.jpg", "hajra")
        );

        ListView<Product> list = new ListView<>();
        list.getItems().addAll(items);

        Label lblName = new Label("Name : ");
        Label lblQuantity = new Label("Quantity : ");
        Label lblOldPrice = new Label("Old price");
        Label lblNewPrice = new Label("New price");
        Label descriptionTitle = new Label("Description: ");
        Label description = new Label("");

        TextField txtName = new TextField();
        TextField txtQuantity = new TextField();
        TextField txtOldPrice = new TextField();
        TextField txtNewPrice = new TextField();

        txtName.setDisable(true);
        txtQuantity.setDisable(true);

        HBox hBoxName = new HBox(lblName, txtName);
        HBox hBoxQuantity = new HBox(lblQuantity, txtQuantity);
        HBox hBoxOldPrice = new HBox(lblOldPrice, txtOldPrice);
        HBox hBoxNewPrice = new HBox(lblNewPrice, txtNewPrice);

        Button btnUpdate = new Button("Update");

        btnUpdate.setOnAction(actionEvent ->  {
            System.out.println("Updated");
            int selectedIdx = list.getSelectionModel().getSelectedIndex();
            if(selectedIdx != -1) {
                // Get values
                String name = txtName.getText();
                String quantity = txtQuantity.getText();
                String oldPrice = txtOldPrice.getText();
                String newPrice = txtNewPrice.getText();
                // Set values
                list.getItems().get(selectedIdx).setName(name);
                list.getItems().get(selectedIdx).setQuantity(quantity);
                list.getItems().get(selectedIdx).setOldPrice(Double.valueOf(oldPrice));
                list.getItems().get(selectedIdx).setNewPrice(Double.valueOf(newPrice));
                list.refresh();
            }
        });

        ImageView selectedImage = new ImageView();
        selectedImage.setFitHeight(250);
        selectedImage.setFitWidth(250);

        VBox productControls = new VBox(hBoxName, hBoxQuantity, hBoxOldPrice, hBoxNewPrice, selectedImage, descriptionTitle, description, btnUpdate);

        HBox main = new HBox(productControls, list);

        GridPane grid = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        grid.getColumnConstraints().addAll(col1, col2);
        grid.getRowConstraints().addAll(row);

        grid.add(list, 1 ,0 );
        grid.add(productControls, 0, 0);

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            txtName.setText(newValue.getName());
            txtQuantity.setText(newValue.getQuantity());
            txtOldPrice.setText(String.valueOf(newValue.getOldPrice()));
            txtNewPrice.setText(String.valueOf(newValue.getNewPrice()));
            selectedImage.setImage(new Image(newValue.getImgPath()));
            description.setText(newValue.getDescription());

        });

        Scene scene = new Scene(grid, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
