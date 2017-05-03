/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial;

import com.mvpjava.interfaceTutorial.electricity.ElectricalOutlet;
import com.mvpjava.interfaceTutorial.exceptions.NotPoweredOnRuntimeException;
import com.mvpjava.interfaceTutorial.exceptions.NotPluggedInRuntimeException;
import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;
import java.util.Objects;

/**
 *
 * @author aluis
 */
public class Stereo {

    private boolean plugged;
    /* Once plugged into a electrical outlet, gets set to true */
    private ElectricalOutlet electricalOutlet;
    private boolean powered;

    public Stereo() {
        this.plugged = false;
        this.powered = false;
        this.electricalOutlet = null;
    }

    public boolean isPlugged() {
        return this.plugged;
    }

    public void plugin(ElectricalOutlet electricalOutlet) {
        this.electricalOutlet = Objects.requireNonNull(electricalOutlet, "ElectricalOutlet is null!");
        this.plugged = true;
    }

    public boolean powerOn() throws NoElectricityRuntimeException {
        if (!isPlugged()) {
            throw new NotPluggedInRuntimeException();
        } else {
            /* if both plugged in and receiving electricity from outlet,
                (meaning an exception isn't thrown then its powered on */
            this.powered = Objects.nonNull(this.electricalOutlet.getElectricity());
        }
        return isPowered();
    }

    public void powerOff() {
        this.powered = false;
    }

    public boolean isPowered() {
        return this.powered;
    }

    public void unplug() {
        this.electricalOutlet = null; //simulate no more access to electricity
        powerOff();
        this.plugged = false;
    }

    public String playMusic() {
        if (!isPowered()) {
            throw new NotPoweredOnRuntimeException();
        }
        return "Let it be";
    }

}
