/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rrsqp4evesupercapitalwatchlist;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Reiker
 */
public class TitanPilot extends Character{
    
        private final IntegerProperty titan;
        private final IntegerProperty portal;
    
    public TitanPilot(int titan, int portal){
        System.out.println("Warning, new Titan Pilot, watch for DoomsDay");
        this.titan = new SimpleIntegerProperty(1);
        this.portal = new SimpleIntegerProperty(1);
    }
    
    public IntegerProperty getTitan(){
        System.out.println("This Character is a Titan Pilot");
        return titan;
    }
    
    public IntegerProperty getPortal(){
        System.out.println("This Character can portal");
        return portal;
    }
}
