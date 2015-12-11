/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Reiker
 */
public class Main extends Application{
    
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Supercapital Watchlist");

        initRootLayout();

        showCharacterOverview();
    }
    
    
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Root.fxml"));
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
            loader.setLocation(Main.class.getResource("CharacterOverview.fxml"));
            AnchorPane characterOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(characterOverview);
            
            CharacterOverviewController controller = loader.getController();
            controller.setMain(this);
            
        } catch (IOException e) {
            System.out.println("Could not input characterOverview");
            System.out.println(e);
        }
    }
     
    private ObservableList<Character> characterList = FXCollections.observableArrayList();
    
    public Main() {
        characterList.add(new Character("omega45", "aeon"));
        characterList.add(new Character("Marksman81", "Nyx"));
        
    }
    
    public ObservableList<Character> getCharacterList() {
        return characterList;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public boolean charactertEditDialog(Character character) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AddEditCharacter.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Character Editor");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        AddEditCharacterController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCharacter(character);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
