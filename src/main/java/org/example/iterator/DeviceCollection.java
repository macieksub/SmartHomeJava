package org.example.iterator;

import org.example.model.SmartDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Iterator.
// Konkretny Agregat (Concrete Aggregate) zarządzający listą urządzeń i dostarczający dedykowane iteratory.
public class DeviceCollection implements DeviceAggregate {
    private final List<SmartDevice> devices = new ArrayList<>();

    public void addDevice(SmartDevice device) {
        if (device != null) {
            devices.add(device);
        }
    }

    public void removeDevice(SmartDevice device) {
        devices.remove(device);
    }

    public int size() {
        return devices.size();
    }

    @Override
    public SmartDeviceIterator createIterator() {
        return new AllDevicesIterator();
    }

    @Override
    public SmartDeviceIterator createActiveDevicesIterator() {
        return new ActiveDevicesIterator();
    }

    // Wewnętrzny iterator przeglądający wszystkie elementy
    private class AllDevicesIterator implements SmartDeviceIterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < devices.size();
        }

        @Override
        public SmartDevice next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Brak kolejnych urządzeń.");
            }
            return devices.get(currentIndex++);
        }
    }

    // Wewnętrzny iterator filtrujący, przeglądający wyłącznie urządzenia aktualnie włączone
    private class ActiveDevicesIterator implements SmartDeviceIterator {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < devices.size()) {
                SmartDevice dev = devices.get(currentIndex);
                if (dev.isTurnedOn()) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public SmartDevice next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Brak kolejnych aktywnych urządzeń.");
            }
            return devices.get(currentIndex++);
        }
    }
}
// Koniec, Tydzień 2, Wzorzec Iterator 6
