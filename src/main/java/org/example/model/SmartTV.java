package org.example.model;

public class SmartTV implements SmartDevice {
    private boolean isOn = false;
    private int volume = 20;
    private String currentInput = "HDMI 1 (Kino Domowe)";

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Telewizor Smart TV włączony (Źródło: " + currentInput + ", Głośność: " + volume + ").");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Telewizor Smart TV wyłączony.");
    }

    @Override
    public String getStatus() {
        return "SmartTV: " + (isOn ? "ON (" + currentInput + ")" : "OFF");
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getCurrentInput() {
        return currentInput;
    }

    public void setCurrentInput(String currentInput) {
        this.currentInput = currentInput;
    }
}
