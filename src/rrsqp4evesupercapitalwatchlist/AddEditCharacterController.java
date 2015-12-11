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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reiker
 */
public class AddEditCharacterController implements Initializable {


    
    
     @FXML
    private TextField usernameField;
    @FXML
    private TextField corporationField;
    @FXML
    private TextField allianceField;
    @FXML
    private TextField shipField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField lastSeenField;


    private Stage dialogStage;
    private Character character;
    private boolean okClicked = false;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     */
    public void setCharacter(Character character) {
        this.character = character;

        usernameField.setText(character.getUsername());
        corporationField.setText(character.getCorporation());
        allianceField.setText(character.getAlliance());
        shipField.setText(character.getShip());
        locationField.setText(character.getLocation());
        lastSeenField.setText(DateToString.format(character.getLastSeen()));
        lastSeenField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void submit() {
        if (isInputValid()) {
            character.setUsername(usernameField.getText());
            character.setCorporation(corporationField.getText());
            character.setAlliance(allianceField.getText());
            character.setShip(shipField.getText());
            character.setLocation(locationField.getText());
            character.setLastSeen(DateToString.parse(lastSeenField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void cancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
 private boolean isInputValid() {
        String errorMessage = "";

        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid Username!\n"; 
        }
        if (corporationField.getText() == null || corporationField.getText().length() == 0) {
            errorMessage += "No valid Corporation!\n"; 
        }
        if (allianceField.getText() == null || allianceField.getText().length() == 0) {
            errorMessage += "No valid Alliance!\n"; 
        }

        if (shipField.getText() == null || shipField.getText().length() == 0) {
            errorMessage += "No valid Ship!\n"; 
        }

        if (locationField.getText() == null || locationField.getText().length() == 0) {
            errorMessage += "No valid Location!\n"; 
        }

        if (lastSeenField.getText() == null || lastSeenField.getText().length() == 0) {
            errorMessage += "No valid last sighting!\n";
        } else {
            if (!DateToString.validDate(lastSeenField.getText())) {
                errorMessage += "No valid last sighting. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    
}
