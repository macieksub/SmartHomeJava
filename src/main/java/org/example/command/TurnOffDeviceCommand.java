package org.example.command;

import org.example.model.SmartDevice;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
// Konkretne polecenie wyłączające wybrane urządzenie i umożliwiające cofnięcie tej akcji (ponowne włączenie).
public class TurnOffDeviceCommand implements Command {
    private final SmartDevice device;
    private final String deviceName;

    public TurnOffDeviceCommand(SmartDevice device, String deviceName) {
        this.device = device;
        this.deviceName = deviceName;
    }

    @Override
    public void execute() {
        System.out.println("[POLECENIE]: Wyłączam urządzenie: " + deviceName);
        device.turnOff();
    }

    @Override
    public void undo() {
        System.out.println("[COFNIĘCIE POLECENIA]: Włączam ponownie urządzenie: " + deviceName);
        device.turnOn();
    }

    @Override
    public String getName() {
        return "Wyłącz [" + deviceName + "]";
    }
}
// Koniec, Tydzień 2, Wzorzec Command 1
