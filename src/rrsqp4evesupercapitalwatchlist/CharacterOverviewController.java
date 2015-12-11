/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Reiker
 */
public class CharacterOverviewController implements Initializable {

    @FXML
    private TableView<Character> characterTable;
    @FXML
    private TableColumn<Character, String> usernameColumn;
    @FXML
    private TableColumn<Character, String> shipColumn;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label shipLabel;
    @FXML
    private Label corporationLabel;
    @FXML
    private Label allianceLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label lastSeenLabel;
    
    
    private Main main;
    
    public CharacterOverviewController() {
    }
    
    /**
     * Initializes the controller class.
     */
    
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        characterTable.setItems(main.getCharacterList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        shipColumn.setCellValueFactory(cellData -> cellData.getValue().shipProperty());
        showcharacterDetails(null);
        characterTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showcharacterDetails(newValue));
        
    }
    
    private void showcharacterDetails(Character character) {
    if (character != null) {
        usernameLabel.setText(character.getUsername());
        shipLabel.setText(character.getShip());
        corporationLabel.setText(character.getCorporation());
        allianceLabel.setText(character.getAlliance());
        locationLabel.setText(character.getLocation());
        lastSeenLabel.setText(DateToString.format(character.getLastSeen()));

        // TODO: We need a way to convert the birthday into a String! 
        // birthdayLabel.setText(...);
    } else {
        usernameLabel.setText("");
        shipLabel.setText("");
        corporationLabel.setText("");
        allianceLabel.setText("");
        locationLabel.setText("");
        lastSeenLabel.setText("");
         }
    }
    
    @FXML
    private void deleteCharacter() {
        int selectedIndex = characterTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            characterTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection Made");
            alert.setHeaderText("You Must First Select a Character!");
            alert.setContentText("Select a character in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void newCharacter() {
        Character tempPerson = new Character();
        boolean okClicked = main.charactertEditDialog(tempPerson);
        if (okClicked) {
            main.getCharacterList().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void editCharacter() {
        Character selectedCharacter = characterTable.getSelectionModel().getSelectedItem();
        if (selectedCharacter != null) {
            boolean okClicked = main.charactertEditDialog(selectedCharacter);
            if (okClicked) {
                showcharacterDetails(selectedCharacter);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Character Selected");
            alert.setHeaderText("You Must Select a Character");
            alert.setContentText("Select a character in the table.");

            alert.showAndWait();
        }
    }
    
    
}
