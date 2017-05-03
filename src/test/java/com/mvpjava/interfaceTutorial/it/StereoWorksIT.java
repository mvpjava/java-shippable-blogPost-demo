/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.it;

import com.mvpjava.interfaceTutorial.electricity.ElectricalOutlet;
import com.mvpjava.interfaceTutorial.electricity.ElectricityProvider;
import com.mvpjava.interfaceTutorial.electricity.Mass;
import com.mvpjava.interfaceTutorial.electricity.NuclearElectricityProviderImpl;
import com.mvpjava.interfaceTutorial.Stereo;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author netbeans
 */
public class StereoWorksIT {

    @Test
    public void checkStereoPlaysMusic() {
        Mass mass = new Mass(5L, "kg");
        ElectricityProvider electricityProvider = new NuclearElectricityProviderImpl(mass);
        ElectricalOutlet electricalOutlet = new ElectricalOutlet(electricityProvider);
        Stereo stereo = new Stereo();
        stereo.plugin(electricalOutlet);
        stereo.powerOn();
        String song = stereo.playMusic();
        Assert.assertEquals("Let it be", song);

    }
}
