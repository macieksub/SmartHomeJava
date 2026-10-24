package org.example.command;

import org.example.model.SmartDevice;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
// Konkretne polecenie włączające wybrane urządzenie i umożliwiające cofnięcie tej akcji (wyłączenie).
public class TurnOnDeviceCommand implements Command {
    private final SmartDevice device;
    private final String deviceName;

    public TurnOnDeviceCommand(SmartDevice device, String deviceName) {
        this.device = device;
        this.deviceName = deviceName;
    }

    @Override
    public void execute() {
        System.out.println("[POLECENIE]: Włączam urządzenie: " + deviceName);
        device.turnOn();
    }

    @Override
    public void undo() {
        System.out.println("[COFNIĘCIE POLECENIA]: Wyłączam urządzenie: " + deviceName);
        device.turnOff();
    }

    @Override
    public String getName() {
        return "Włącz [" + deviceName + "]";
    }
}
// Koniec, Tydzień 2, Wzorzec Command 1
