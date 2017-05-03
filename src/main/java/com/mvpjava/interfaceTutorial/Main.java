
package com.mvpjava.interfaceTutorial;

import com.mvpjava.interfaceTutorial.electricity.ElectricalOutlet;
import com.mvpjava.interfaceTutorial.electricity.ElectricityProvider;
import com.mvpjava.interfaceTutorial.electricity.Mass;
import com.mvpjava.interfaceTutorial.electricity.NuclearElectricityProviderImpl;

public class Main {
    public static void main(String[] args) {
        Mass mass = new Mass (5L, "kg");
        ElectricityProvider electricityProvider = new NuclearElectricityProviderImpl(mass);
        ElectricalOutlet electricalOutlet = new ElectricalOutlet(electricityProvider);
        Stereo stereo = new Stereo();
        stereo.plugin(electricalOutlet);
        stereo.powerOn();
        String song = stereo.playMusic();
        System.out.println("playing song: " + song);
    }
}
