package org.example.strategy;

import org.example.model.SmartLight;
import org.example.model.SmartThermostat;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
// Strategia nocna (Night): obniża temperaturę do optymalnej dla snu (19.5°C) i wygasza oświetlenie.
public class NightEnergyStrategy implements EnergySavingStrategy {
    @Override
    public String getStrategyName() {
        return "Tryb NOCNY";
    }

    @Override
    public void apply(SmartThermostat thermostat, SmartLight light) {
        System.out.println("[STRATEGIA NOCNA]: Przygotowanie domu do snu (19.5°C, wyłączone światła).");
        thermostat.setTemperature(19.5);
        thermostat.turnOn();
        light.turnOff();
    }
}
// Koniec, Tydzień 2, Wzorzec Strategy 3
