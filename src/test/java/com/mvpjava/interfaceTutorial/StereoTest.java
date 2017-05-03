/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial;

import com.mvpjava.interfaceTutorial.electricity.ElectricalOutlet;
import com.mvpjava.interfaceTutorial.electricity.Electricity;
import com.mvpjava.interfaceTutorial.exceptions.NotPluggedInRuntimeException;
import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;
import com.mvpjava.interfaceTutorial.exceptions.NotPoweredOnRuntimeException;
import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author aluis
 */
public class StereoTest {

    Stereo stereo; //SUT
    ElectricalOutlet electricalOutlet; //DOC

    public StereoTest() {
    }

    @Before
    public void setUp() {
        /* a new stereo does not have to be plugged in, hence why we do no pass 
        in an ElectricalOutlet to constructor
         */
        stereo = new Stereo();
        electricalOutlet = mock(ElectricalOutlet.class);

    }

    @Test
    public void pluggingInStereoShouldIndicatePluggedIn() {
        stereo.plugin(electricalOutlet);
        assertTrue("Should have reported plugged in", stereo.isPlugged());
    }

    @Test
    public void newStereoShouldReportExpectedDefaultValues() {
        assertFalse("Should have returned false (not pluged in)", stereo.isPlugged());
        assertFalse("Should have returned false (not powered)", stereo.isPowered());

    }

    @Test
    public void shouldThrowExceptionIfPluggintoNullElectricalOutlet() {
        catchException(stereo).plugin(null);
        assertTrue("Should have thrown an exception when trying to plugin into null",
                caughtException() instanceof NullPointerException);
        assertFalse("Should not  be considered plugged in", stereo.isPlugged());
    }

    @Test
    public void pluggedInWithElectricityShouldIndicatePoweredOn() {
        //happpy path
        bringToNominalPowerOnState();

        assertTrue("Should be powered on", stereo.isPowered());
    }

    @Test
    public void throwExceptionIfNoElectricityAvailableWhenPoweringON() {
        stereo.plugin(electricalOutlet);
        when(electricalOutlet.getElectricity()).thenThrow(new NoElectricityRuntimeException());

        catchException(stereo).powerOn();
        assertTrue("Should have thrown NoElectricityRuntimeException if no electricity available at power on",
                caughtException() instanceof NoElectricityRuntimeException);
    }

    @Test
    public void ShouldIndicatePoweredOffWhenPoweredOff() {
        stereo.powerOff();
        assertFalse("Powering off should force power off", stereo.isPowered());
    }

    @Test
    public void shouldThrowExceptionWhenPoweringOnButNeverPlugged() {
        //never pluggin in --> stereo.plugin(electricalOutlet);
        catchException(stereo).powerOn();
        assertTrue("Should have thrown an exception when trying to power on an unplugged device",
                caughtException() instanceof NotPluggedInRuntimeException);
        assertFalse("Should be powered off if unplugged", stereo.isPowered());
    }

    @Test
    public void ShouldIndicateUnpluggedWhenUnplugged() {
        stereo.unplug();
        assertFalse("Unplugging should force an unplug", stereo.isPlugged());
    }

    @Test
    public void shouldThrowExceptionWhenPoweringOnAfterUnlugged() {
        bringToNominalPowerOnState();
        stereo.powerOff();
        stereo.unplug();

        catchException(stereo).powerOn();
        assertTrue("Should have thrown an exception when trying to power on after unplugged device",
                caughtException() instanceof NotPluggedInRuntimeException);
        assertFalse("Should be powered off if unplugged", stereo.isPowered());
    }

    @Test
    public void shouldIndicatePoweredOffIfUnpluggedBeforePoweringOff() {
        bringToNominalPowerOnState();
        // never happens --> stereo.powerOff();
        stereo.unplug();

        assertFalse("Should be powered off if unplugged while powered on", stereo.isPowered());
    }

    @Test
    public void shouldPlayMusicWhenPlayRequested() {
        String expectedSong = "Let it be";
        
        bringToNominalPowerOnState();
        assertEquals("Suppose to be playing song; " + expectedSong,
                expectedSong,
                stereo.playMusic());
    }

    @Test
    public void shouldThrowExceptionWhenPressPlayMusicWhenNoPower() {
        catchException(stereo).playMusic();
        
        assertTrue("Should not be able to play music if not powered on!",
                caughtException() instanceof NotPoweredOnRuntimeException);
    }
    /**
     * ********************** PRIVATE METHODS ********************
     */
    // properly transition SUT to power ON state with electricity
    private void bringToNominalPowerOnState() throws NoElectricityRuntimeException {
        stereo.plugin(electricalOutlet);
        when(electricalOutlet.getElectricity()).thenReturn(new Electricity());
        stereo.powerOn();
    }
}
