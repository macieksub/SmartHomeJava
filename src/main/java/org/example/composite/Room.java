package org.example.composite;

import org.example.model.SmartDevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Composite.
// Klasa Room pełni rolę kompozytu (Composite), implementując interfejs SmartDevice i grupując
// inne urządzenia (zarówno pojedyncze liście, jak i potencjalnie zagnieżdżone kompozyty) pod wspólnym interfejsem.
public class Room implements SmartDevice {
    private final String name;
    private final List<SmartDevice> devices = new ArrayList<>();

    public Room(String name) {
        this.name = name;
    }

    public void addDevice(SmartDevice device) {
        if (device != null && !devices.contains(device)) {
            devices.add(device);
        }
    }

    public void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    public List<SmartDevice> getDevices() {
        return Collections.unmodifiableList(devices);
    }

    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        System.out.println("Włączam wszystkie urządzenia w pomieszczeniu: " + name);
        for (SmartDevice dev : devices) {
            dev.turnOn();
        }
    }

    @Override
    public void turnOff() {
        System.out.println("Wyłączam wszystkie urządzenia w pomieszczeniu: " + name);
        for (SmartDevice dev : devices) {
            dev.turnOff();
        }
    }

    @Override
    public String getStatus() {
        return "Pokój \"" + name + "\" zarządza " + devices.size() + " urządzeniami.";
    }
}
// Koniec, Tydzień 1, Wzorzec Composite 6