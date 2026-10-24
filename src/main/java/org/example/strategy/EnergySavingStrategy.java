package org.example.strategy;

import org.example.model.SmartLight;
import org.example.model.SmartThermostat;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
// Interfejs Strategii definiujący kontrakt dla wymiennych algorytmów zarządzania energią w domu.
public interface EnergySavingStrategy {
    String getStrategyName();
    void apply(SmartThermostat thermostat, SmartLight light);
}
// Koniec, Tydzień 2, Wzorzec Strategy 3
