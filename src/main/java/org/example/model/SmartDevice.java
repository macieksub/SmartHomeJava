package org.example.model;

// Interfejs wspólny dla wszystkich urządzeń Smart Home
public interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();

    // Domyślna metoda sprawdzająca czy urządzenie jest włączone
    default boolean isTurnedOn() {
        return getStatus() != null && getStatus().toUpperCase().contains("ON");
    }
}
