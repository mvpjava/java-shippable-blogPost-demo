/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.electricity;

import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;

/**
 *
 * @author aluis
 */
public interface ElectricityProvider {

    public Electricity provideElectricity() throws NoElectricityRuntimeException;
    public String getPowerSourceDetails();
    
}
