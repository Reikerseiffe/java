/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    public void initialize() {
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        shipColumn.setCellValueFactory(cellData -> cellData.getValue().shipProperty());
    }    
    
    public void setMainApp(Main main) {
        this.main = main;

        // Add observable list data to the table
        characterTable.setItems(main.getCharacterList());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
