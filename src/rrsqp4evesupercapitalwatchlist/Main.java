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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Root.fxml"));
        rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        RootController controller = loader.getController();
        controller.setMain(this);
        primaryStage.show();
    } catch (IOException e) {
    }

    File file = getCharacterFilePath();
    if (file != null) {
        loadCharacterDataFromFile(file);
    }
}

     public void showCharacterOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("CharacterOverview.fxml"));
            AnchorPane characterOverview = (AnchorPane) loader.load();
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AddEditCharacter.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Character Editor");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        AddEditCharacterController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setCharacter(character);
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
    
    public void setCharacterFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("SupercapitalWatchlist - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("SupercapitalWatchlist");
        }
    }
    
    public void loadCharacterDataFromFile(File file) {
    try {
        JAXBContext context = JAXBContext.newInstance(CharacterSaver.class);
        Unmarshaller um = context.createUnmarshaller();
        CharacterSaver wrapper = (CharacterSaver) um.unmarshal(file);
        characterList.clear();
        characterList.addAll(wrapper.getCharacters());
        setCharacterFilePath(file);
    } catch (Exception e) { // catches ANY exception
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Application could not load File");
        alert.setContentText("File Path:\n" + file.getPath());
        alert.showAndWait();
    }
}

    public void saveCharacterDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CharacterSaver.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            CharacterSaver saver = new CharacterSaver();
            saver.setCharacters(characterList);
            m.marshal(saver, file);
            setCharacterFilePath(file);
        } catch (Exception e) {
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
