package org.example.iterator;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Iterator.
// Interfejs Agregatu (Aggregate) definiujący metody fabrykujące iteratory dla kolekcji urządzeń.
public interface DeviceAggregate {
    SmartDeviceIterator createIterator();
    SmartDeviceIterator createActiveDevicesIterator();
}
// Koniec, Tydzień 2, Wzorzec Iterator 6
