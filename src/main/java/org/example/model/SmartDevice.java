package org.example.model;

// Interfejs wspólny dla wszystkich urządzeń
public interface SmartDevice {
    void turnOn();
    void turnOff();
    String getStatus();
}