/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

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
        // Load root layout from fxml file.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Root.fxml"));
        rootLayout = (BorderPane) loader.load();

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        RootController controller = loader.getController();
        controller.setMain(this);

        primaryStage.show();
    } catch (IOException e) {
    }

    // Try to load last opened person file.
    File file = getCharacterFilePath();
    if (file != null) {
        loadPersonDataFromFile(file);
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
    
    
    public File getCharacterFilePath() {
    Preferences prefs = Preferences.userNodeForPackage(Main.class);
    String filePath = prefs.get("filePath", null);
    if (filePath != null) {
        return new File(filePath);
    } else {
        return null;
    }
}

/**
 * Sets the file path of the currently loaded file. The path is persisted in
 * the OS specific registry.
 * 
 * @param file the file or null to remove the path
 */
    public void setCharacterFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("SupercapitalWatchlist - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("SupercapitalWatchlist");
        }
    }
    
    public void loadPersonDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(CharacterSaver.class);
        Unmarshaller um = context.createUnmarshaller();

        // Reading XML from the file and unmarshalling.
        CharacterSaver wrapper = (CharacterSaver) um.unmarshal(file);

        characterList.clear();
        characterList.addAll(wrapper.getCharacters());

        // Save the file path to the registry.
        setCharacterFilePath(file);

    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Application could not load Data");
        alert.setContentText("File Path:\n" + file.getPath());

        alert.showAndWait();
    }
}

/**
 * Saves the current person data to the specified file.
 * 
 * @param file
 */
    public void saveCharacterDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterSaver.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            CharacterSaver saver = new CharacterSaver();
            saver.setCharacters(characterList);

            // Marshalling and saving XML to the file.
            m.marshal(saver, file);

            // Save the file path to the registry.
            setCharacterFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
