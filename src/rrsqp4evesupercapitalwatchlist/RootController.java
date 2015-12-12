/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Reiker
 */
public class RootController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void New() {
        main.getCharacterList().clear();
        main.setCharacterFilePath(null);
    }

    @FXML
    private void open() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        if (file != null) {
            main.loadCharacterDataFromFile(file);
        }
    }

    @FXML
    private void save() {
        File characterFile = main.getCharacterFilePath();
        if (characterFile != null) {
            main.saveCharacterDataToFile(characterFile);
        } else {
            saveAs();
        }
    }
    
    @FXML
    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            main.saveCharacterDataToFile(file);
        }
    }

    @FXML
    private void about() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Supercapital Watchlist");
        alert.setHeaderText("About The Eve Supercapital Watchlist");
        alert.setContentText("This app is intended to help keep an eye on players in the game Eve Online that own supercapital ships."
                + "These ships are the most powerful waepons in the game and take hundreds of players months to build");
        alert.showAndWait();
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
    
}
