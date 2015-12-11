/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Reiker
 */
public class Main {
    
    private Stage primaryStage;
    private BorderPane rootLayout;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Supercapital Watchlist");

        initRootLayout();

        showCharacterOverview();
    }
    
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
        }
    }
    
     public void showCharacterOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CharacterOverview.fxml"));
            AnchorPane characterOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(characterOverview);
        } catch (IOException e) {
        }
    }
     
     public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private ObservableList<Character> characterList = FXCollections.observableArrayList();
    
    public Main() {
        characterList.add(new Character("omega45", "aeon"));
    }
    
    public ObservableList<Character> getCharacterList() {
        return characterList;
    }
    
    
    public void showPersonOverview() {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CharacterOverview.fxml"));
        AnchorPane personOverview = (AnchorPane) loader.load();
        rootLayout.setCenter(personOverview);
        CharacterOverviewController controller = loader.getController();
        controller.setMainApp(this);

    } catch (IOException e) {
    }
}
    
}
