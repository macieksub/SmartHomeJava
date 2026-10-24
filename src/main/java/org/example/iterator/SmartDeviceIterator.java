package org.example.iterator;

import org.example.model.SmartDevice;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Iterator.
// Interfejs Iteratora definiujący metody sekwencyjnego dostępu do elementów kolekcji urządzeń.
public interface SmartDeviceIterator {
    boolean hasNext();
    SmartDevice next();
}
// Koniec, Tydzień 2, Wzorzec Iterator 6
