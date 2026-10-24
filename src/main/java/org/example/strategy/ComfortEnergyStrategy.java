package org.example.strategy;

import org.example.model.SmartLight;
import org.example.model.SmartThermostat;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
// Strategia komfortowa (Comfort): optymalizuje warunki mieszkalne (22.5°C) i włącza przyjemne oświetlenie.
public class ComfortEnergyStrategy implements EnergySavingStrategy {
    @Override
    public String getStrategyName() {
        return "Tryb KOMFORT";
    }

    @Override
    public void apply(SmartThermostat thermostat, SmartLight light) {
        System.out.println("[STRATEGIA KOMFORT]: Ustawianie optymalnych warunków dla domowników.");
        thermostat.setTemperature(22.5);
        thermostat.turnOn();
        light.turnOn();
    }
}
// Koniec, Tydzień 2, Wzorzec Strategy 3
