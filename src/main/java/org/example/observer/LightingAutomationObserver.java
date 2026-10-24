package org.example.observer;

import org.example.model.SmartDevice;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Observer.
// Konkretny obserwator realizujący automatyzację oświetlenia — włącza powiązane światło po wykryciu ruchu.
public class LightingAutomationObserver implements SensorObserver {
    private final SmartDevice light;
    private int triggerCount = 0;

    public LightingAutomationObserver(SmartDevice light) {
        this.light = light;
    }

    @Override
    public void onSensorEvent(String sensorName, String eventType, String location) {
        triggerCount++;
        System.out.println("[AUTOMATYKA ŚWIATŁA]: Wykryto ruch (" + location + "). Automatycznie włączam oświetlenie.");
        light.turnOn();
    }

    public int getTriggerCount() {
        return triggerCount;
    }
}
// Koniec, Tydzień 2, Wzorzec Observer 2
