package org.example.model;

// Podstawowa implementacja inteligentnego oświetlenia
public class SmartLight implements SmartDevice {
    private boolean isOn = false;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Światło włączone.");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Światło wyłączone.");
    }

    @Override
    public String getStatus() {
        return "SmartLight: " + (isOn ? "ON" : "OFF");
    }

    public boolean isOn() {
        return isOn;
    }
}