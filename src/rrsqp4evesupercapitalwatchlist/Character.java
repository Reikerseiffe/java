/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Reiker
 */
public class Character {
    
    //USING PROPERTIES TO WATCH FOR CHANGES
    private final StringProperty username;
    private final StringProperty ship;
    private final StringProperty corporation;
    private final StringProperty alliance;
    private final StringProperty location;
    private final ObjectProperty<LocalDate> lastSeen;

    
    public Character() {
        this(null, null);
    }
    
    public Character(String username, String ship) {
        this.username = new SimpleStringProperty(username);
        this.ship = new SimpleStringProperty(ship);
        //EXAMPLE STUFF
        this.corporation = new SimpleStringProperty("Graviton Solutions");
        this.alliance = new SimpleStringProperty("Alts");
        this.location = new SimpleStringProperty("Jita");
        this.lastSeen = new SimpleObjectProperty<>(LocalDate.of(2015, 10, 31));
    }
    
     public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getShip() {
        return ship.get();
    }

    public void setShip(String ship) {
        this.ship.set(ship);
    }

    public StringProperty shipProperty() {
        return ship;
    }

    public String getCorporation() {
        return corporation.get();
    }

    public void setCorporation(String corporation) {
        this.corporation.set(corporation);
    }

    public StringProperty corporationProperty() {
        return corporation;
    }

    public String getAlliance() {
        return alliance.get();
    }

    public void setAlliance(String alliance) {
        this.alliance.set(alliance);
    }

    public StringProperty allianceProperty() {
        return alliance;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public LocalDate getLastSeen() {
        return lastSeen.get();
    }

    public void setLastSeen(LocalDate lastSeen) {
        this.lastSeen.set(lastSeen);
    }

    public ObjectProperty<LocalDate> lastSeenProperty() {
        return lastSeen;
    }
    


}
