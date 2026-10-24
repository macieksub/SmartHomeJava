package org.example.strategy;

import org.example.model.SmartLight;
import org.example.model.SmartThermostat;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
// Klasa EnergyManager pełni rolę Kontekstu (Context), przechowując referencję do wybranej strategii
// zarządzania energią i delegując jej realizację.
public class EnergyManager {
    private EnergySavingStrategy strategy;

    public EnergyManager(EnergySavingStrategy initialStrategy) {
        this.strategy = initialStrategy;
    }

    public void setStrategy(EnergySavingStrategy strategy) {
        this.strategy = strategy;
    }

    public void applyCurrentStrategy(SmartThermostat thermostat, SmartLight light) {
        if (strategy == null) {
            System.out.println("[MENEDŻER ENERGII]: Nie wybrano strategii.");
            return;
        }
        System.out.println("\n[MENEDŻER ENERGII]: Wdrażanie strategii: " + strategy.getStrategyName());
        strategy.apply(thermostat, light);
    }

    public String getCurrentStrategyName() {
        return strategy != null ? strategy.getStrategyName() : "Brak";
    }
}
// Koniec, Tydzień 2, Wzorzec Strategy 3
