package org.example.memento;

import java.util.ArrayDeque;
import java.util.Deque;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Memento.
// Klasa Dozorca (Caretaker) przechowuje historię utworzonych pamiątek bez wnikania w ich wewnętrzną strukturę.
public class ConfigHistoryCaretaker {
    private final Deque<DeviceConfigMemento> history = new ArrayDeque<>();

    public void pushMemento(DeviceConfigMemento memento) {
        if (memento != null) {
            history.push(memento);
        }
    }

    public DeviceConfigMemento popMemento() {
        if (history.isEmpty()) {
            System.out.println("[DOZORCA]: Brak wcześniejszych zapisów konfiguracji.");
            return null;
        }
        return history.pop();
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 2, Wzorzec Memento 5
