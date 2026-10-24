package org.example.observer;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Observer.
// Interfejs Obserwatora definiujący metodę aktualizacji wywoływaną w momencie wykrycia zdarzenia przez czujnik.
public interface SensorObserver {
    void onSensorEvent(String sensorName, String eventType, String location);
}
// Koniec, Tydzień 2, Wzorzec Observer 2
