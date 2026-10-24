package org.example.memento;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Memento.
// Klasa Pamiątka (Memento) przechowująca niemutowalny zrzut stanu konfiguracji urządzeń domowych w danym momencie.
public class DeviceConfigMemento {
    private final boolean lightOn;
    private final String lightColor;
    private final int lightBrightness;
    private final double thermostatTemp;
    private final String description;

    public DeviceConfigMemento(boolean lightOn, String lightColor, int lightBrightness, double thermostatTemp, String description) {
        this.lightOn = lightOn;
        this.lightColor = lightColor;
        this.lightBrightness = lightBrightness;
        this.thermostatTemp = thermostatTemp;
        this.description = description;
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public String getLightColor() {
        return lightColor;
    }

    public int getLightBrightness() {
        return lightBrightness;
    }

    public double getThermostatTemp() {
        return thermostatTemp;
    }

    public String getDescription() {
        return description;
    }
}
// Koniec, Tydzień 2, Wzorzec Memento 5
