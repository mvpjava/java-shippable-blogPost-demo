/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.electricity;

import com.mvpjava.interfaceTutorial.exceptions.NoElectricityRuntimeException;
import static com.googlecode.catchexception.CatchException.caughtException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static com.googlecode.catchexception.CatchException.catchException;

/**
 *
 * @author aluis
 */
public class ElectricalOutletTest {

    ElectricalOutlet electricalOutletWithoutImpl; //SUT not properly constructedf
    ElectricalOutlet electricalOutlet; //SUT  properly constructed
    ElectricityProvider electricityProvider; //mock

    @Before
    public void setUp() {
        electricalOutletWithoutImpl = new ElectricalOutlet(null);
        electricityProvider = mock(ElectricityProvider.class);
        electricalOutlet = new ElectricalOutlet(electricityProvider);
    }


    @Test
    public void shouldThrowExceptionWhenNoImplProvided() {
        catchException(electricalOutletWithoutImpl).getElectricity();
        assertTrue("Should have thrown an exception when no electricity implementation provided",
                caughtException() instanceof IllegalStateException);
    }

    @Test
    public void shouldGenerateElectricityWhenImplProvided() {
        //happy path
        when(electricalOutlet.getElectricity()).thenReturn(new Electricity());
        assertNotNull("An Electricity object should have been returned", electricalOutlet.getElectricity());
    }
    
    @Test
    public void shouldThrowExceptionWhenNoElectricityIsAvailable() {
        when(electricityProvider.provideElectricity()).thenThrow(new NoElectricityRuntimeException());
        catchException(electricalOutlet).getElectricity();
        assertTrue("Should have thrown an exception when no electricity is available",
                caughtException() instanceof NoElectricityRuntimeException);   
    }
      
}
