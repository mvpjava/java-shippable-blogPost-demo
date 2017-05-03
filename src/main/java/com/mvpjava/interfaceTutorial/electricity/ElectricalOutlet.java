/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.electricity;

import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;
import java.util.Objects;

/**
 *
 * @author aluis
 */
public class ElectricalOutlet {

    private final ElectricityProvider electricityProvider;

    public ElectricalOutlet(ElectricityProvider electricityProvider) {
        this.electricityProvider = electricityProvider;
    }

    public Electricity getElectricity() throws NoElectricityRuntimeException {
        if (Objects.isNull(getElectricityProvider())) {
            throw new IllegalStateException("No ElectricityProvider impl. provided!");
        }
        
        /*may throw NoElectricityRuntimeException if ever there is no elecricity
        * available, it will depend on quality of implementation 
        */
        return getElectricityProvider().provideElectricity();
    }

    private ElectricityProvider getElectricityProvider() {
        return this.electricityProvider;
    }

}
