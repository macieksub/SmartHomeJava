package org.example.model;

// Podstawowa implementacja inteligentnego termostatu
public class SmartThermostat implements SmartDevice {
    private boolean isOn = false;
    private double temperature = 21.5;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Termostat włączony (grzanie aktywne, temp: " + temperature + "°C).");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Termostat wyłączony (tryb czuwania).");
    }

    @Override
    public String getStatus() {
        return "SmartThermostat: " + (isOn ? "ON (" + temperature + "°C)" : "OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}