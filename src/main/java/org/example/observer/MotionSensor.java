package org.example.observer;

import java.util.ArrayList;
import java.util.List;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Observer.
// Klasa MotionSensor pełni rolę Podmiotu (Subject/Observable). Zarządza listą obserwatorów
// i powiadamia ich w przypadku wykrycia ruchu w monitorowanej strefie.
public class MotionSensor {
    private final String sensorName;
    private final List<SensorObserver> observers = new ArrayList<>();

    public MotionSensor(String sensorName) {
        this.sensorName = sensorName;
    }

    public void attach(SensorObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(SensorObserver observer) {
        observers.remove(observer);
    }

    public void triggerMotion(String location) {
        System.out.println("\n[CZUJNIK RUCHU '" + sensorName + "']: Zarejestrowano ruch w: " + location + "!");
        notifyObservers("WYKRYTO_RUCH", location);
    }

    private void notifyObservers(String eventType, String location) {
        for (SensorObserver observer : observers) {
            observer.onSensorEvent(sensorName, eventType, location);
        }
    }

    public int getObserverCount() {
        return observers.size();
    }

    public String getSensorName() {
        return sensorName;
    }
}
// Koniec, Tydzień 2, Wzorzec Observer 2
