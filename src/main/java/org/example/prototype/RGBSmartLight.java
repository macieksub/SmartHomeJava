package org.example.prototype;

import org.example.model.SmartDevice;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Prototype.
// Wzorzec Prototype pozwala na szybkie powielanie (klonowanie) istniejącej konfiguracji żarówki RGB
// bez konieczności ponownego definiowania wszystkich parametrów od początku.
public class RGBSmartLight implements SmartDevice, Cloneable {
    private String color;
    private int brightness;
    private boolean isOn = false;

    public RGBSmartLight(String color, int brightness) {
        this.color = color;
        this.brightness = brightness;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Żarówka RGB włączona [Kolor: " + color + ", Jasność: " + brightness + "%].");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Żarówka RGB wyłączona.");
    }

    @Override
    public String getStatus() {
        return "RGB Light (" + (isOn ? "ON" : "OFF") + ", Kolor: " + color + ", Jasność: " + brightness + "%)";
    }

    @Override
    public RGBSmartLight clone() {
        try {
            return (RGBSmartLight) super.clone();
        } catch (CloneNotSupportedException e) {
            // Bezpieczny fallback w razie potrzeby
            RGBSmartLight copy = new RGBSmartLight(this.color, this.brightness);
            copy.isOn = this.isOn;
            return copy;
        }
    }
}
// Koniec, Tydzień 1, Wzorzec Prototype 3