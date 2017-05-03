/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvpjava.interfaceTutorial.electricity;

/**
 *
 * @author aluis
 */
public class Mass {

    private long weight;
    private String unit;


    public Mass(long weight, String unit) {
        this.weight = weight;
        this.unit = unit;
    }

    long getWeight() {
        return this.weight;
    }
    
}
