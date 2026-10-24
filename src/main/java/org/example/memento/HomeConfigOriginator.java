package org.example.memento;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Memento.
// Klasa Twórca (Originator) przechowująca aktualne parametry konfiguracji domu, potrafiąca
// utworzyć nową pamiątkę (Memento) oraz odtworzyć swój stan z przekazanej pamiątki.
public class HomeConfigOriginator {
    private boolean lightOn;
    private String lightColor;
    private int lightBrightness;
    private double thermostatTemp;

    public HomeConfigOriginator(boolean lightOn, String lightColor, int lightBrightness, double thermostatTemp) {
        this.lightOn = lightOn;
        this.lightColor = lightColor;
        this.lightBrightness = lightBrightness;
        this.thermostatTemp = thermostatTemp;
    }

    public DeviceConfigMemento saveToMemento(String snapshotDescription) {
        System.out.println("[MEMENTO]: Tworzę punkt przywracania (" + snapshotDescription + ") -> " + getStatus());
        return new DeviceConfigMemento(lightOn, lightColor, lightBrightness, thermostatTemp, snapshotDescription);
    }

    public void restoreFromMemento(DeviceConfigMemento memento) {
        if (memento == null) {
            return;
        }
        this.lightOn = memento.isLightOn();
        this.lightColor = memento.getLightColor();
        this.lightBrightness = memento.getLightBrightness();
        this.thermostatTemp = memento.getThermostatTemp();
        System.out.println("[MEMENTO]: Przywrócono stan z punktu: '" + memento.getDescription() + "' -> " + getStatus());
    }

    public String getStatus() {
        return "Światło: " + (lightOn ? "ON" : "OFF") + " [" + lightColor + ", " + lightBrightness + "%], Temp: " + thermostatTemp + "°C";
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
    }

    public String getLightColor() {
        return lightColor;
    }

    public void setLightColor(String lightColor) {
        this.lightColor = lightColor;
    }

    public int getLightBrightness() {
        return lightBrightness;
    }

    public void setLightBrightness(int lightBrightness) {
        this.lightBrightness = lightBrightness;
    }

    public double getThermostatTemp() {
        return thermostatTemp;
    }

    public void setThermostatTemp(double thermostatTemp) {
        this.thermostatTemp = thermostatTemp;
    }
}
// Koniec, Tydzień 2, Wzorzec Memento 5
