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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        ObservableList<Product> items = FXCollections.observableArrayList(
                new Product("Salakis Schafmilchkäse", "200 Gramm Packung", 2.59, 1.99, "sample/1.jpg", "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."),
                new Product("Pfeffer", "1 Stück", 2.59, 1.99, "sample/2.jpg", "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird. "),
                new Product("Vöslauer", "1.5 Liter Flasche", 2.59, 1.99, "sample/3.jpg", "Spritziges Vöslauer Mineralwasser."),
                new Product("Zucker", "500 Gramm Paket", 2.59, 1.99, "sample/4.jpg", "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde.")
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
        Button btnReport = new Button("Report");

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

        VBox productControls = new VBox(hBoxName, hBoxQuantity, hBoxOldPrice, hBoxNewPrice, selectedImage, descriptionTitle, description, btnUpdate, btnReport);

        HBox main = new HBox(productControls, list);

        productControls.setSpacing(5);
        description.setWrapText(true);

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

        // FILEWRITER
        File file = new File("report.txt");

        if(!file.exists()) {
            file.createNewFile();
        }

        PrintWriter pw = new PrintWriter(file);
        pw.println("ACTION PRICES: \n");

        for(Product p : items) {
            pw.println("~ " + p.getName() + "\n" + p.getQuantity() + "\n" + p.getDescription() + "\n" + "instead of " + p.getOldPrice() + "\naction price : " + p.getNewPrice() + "\n" );
        }

        pw.println("\n");
        pw.close();

        btnReport.setOnAction(actionEvent -> {
            Desktop desktop = Desktop.getDesktop();
            if(file.exists()) {
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(grid, 1280, 720);
        System.out.println(scene.getStylesheets());
        scene.getStylesheets().add("sample/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
