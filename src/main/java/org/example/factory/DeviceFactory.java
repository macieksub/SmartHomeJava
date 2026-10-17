package org.example.factory;

import org.example.model.SmartDevice;
import org.example.model.SmartLight;
import org.example.model.SmartThermostat;
import org.example.model.SmartTV;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Factory (Simple Factory).
// Fabryka DeviceFactory odpowiada za tworzenie odpowiednich obiektów urządzeń na podstawie przekazanego typu,
// odciążając klienta z konieczności bezpośredniego posługiwania się konkretnymi konstruktorami klas.
public class DeviceFactory {
    public SmartDevice createDevice(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Typ urządzenia nie może być null.");
        }

        switch (type.trim().toUpperCase()) {
            case "LIGHT":
                return new SmartLight();
            case "THERMOSTAT":
                return new SmartThermostat();
            case "TV":
                return new SmartTV();
            default:
                throw new IllegalArgumentException("Nieznany typ urządzenia: " + type);
        }
    }
}
// Koniec, Tydzień 1, Wzorzec Factory Method 2