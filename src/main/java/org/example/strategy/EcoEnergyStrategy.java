package org.example.strategy;

import org.example.model.SmartLight;
import org.example.model.SmartThermostat;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
// Strategia ekologiczna (Eco): obniża temperaturę do 18.0°C i wyłącza zbędne oświetlenie, minimalizując zużycie prądu.
public class EcoEnergyStrategy implements EnergySavingStrategy {
    @Override
    public String getStrategyName() {
        return "Tryb EKO (Oszczędny)";
    }

    @Override
    public void apply(SmartThermostat thermostat, SmartLight light) {
        System.out.println("[STRATEGIA EKO]: Maksymalna redukcja poboru prądu.");
        thermostat.setTemperature(18.0);
        thermostat.turnOn();
        light.turnOff();
    }
}
// Koniec, Tydzień 2, Wzorzec Strategy 3
