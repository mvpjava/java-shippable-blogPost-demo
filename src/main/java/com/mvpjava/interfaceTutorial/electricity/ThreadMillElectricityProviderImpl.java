/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.electricity;

import com.mvpjava.interfaceTutorial.time.SystemTime;
import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;
import java.util.Objects;

/**
 *
 * @author aluis
 */
public class ThreadMillElectricityProviderImpl implements ElectricityProvider {

    final int LUNCH_TIME_HOUR = 12;
    private boolean runningOoThreadMill;//power source is someone running on a threadmill
    private SystemTime systemTime;
    private final Electricity electricity = new Electricity();

    @Override
    public Electricity provideElectricity() throws NoElectricityRuntimeException {
        if (!isRunningOnThreadMill()) {
            throw new NoElectricityRuntimeException("No electricity since nobody running on threadmill!");
        }
        return getElectricity();
    }

    @Override
    public String getPowerSourceDetails() {
        return "Electricity generated by Human running on threadmill";
    }

    //only used for testing
    void setSystemTime(SystemTime systemTime) {
        this.systemTime = systemTime;
    }

    private boolean isLunchTime() {
        int currentHour = getSystemTime().asLocalTime().getHour();
        return (currentHour == LUNCH_TIME_HOUR);
    }

    private SystemTime getSystemTime() {
        return (Objects.nonNull(systemTime)
                ? systemTime
                : new SystemTime());
    }

    private Electricity getElectricity() {
        return electricity;
    }

    private boolean isRunningOnThreadMill() {
        runningOoThreadMill = (!isLunchTime());
        return runningOoThreadMill;
    }
}
