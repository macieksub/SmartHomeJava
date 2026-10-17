package org.example.adapter;

import org.example.model.SmartDevice;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Adapter.
// Adapter OldRadioAdapter adaptuje interfejs klasycznego odbiornika OldRadio do ujednoliconego
// interfejsu SmartDevice, umożliwiając bezproblemową integrację starszego sprzętu z nowoczesną centralą.
public class OldRadioAdapter implements SmartDevice {
    private final OldRadio radio;

    public OldRadioAdapter(OldRadio radio) {
        this.radio = radio;
    }

    @Override
    public void turnOn() {
        radio.powerOn();
    }

    @Override
    public void turnOff() {
        radio.powerOff();
    }

    @Override
    public String getStatus() {
        return "Stare Radio (przez Adapter): " + (radio.isPlaying() ? "ON" : "OFF");
    }

    public OldRadio getRadio() {
        return radio;
    }
}
// Koniec, Tydzień 1, Wzorzec Adapter 5