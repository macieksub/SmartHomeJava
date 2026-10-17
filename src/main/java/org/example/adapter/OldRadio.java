package org.example.adapter;

// ---------------------------------------------------------
// Klasa 'starego' systemu (Adaptee) - niekompatybilna z interfejsem SmartDevice.
// Posiada własne nazewnictwo metod (powerOn, powerOff).
// ---------------------------------------------------------
public class OldRadio {
    private boolean isPlaying = false;

    public void powerOn() {
        isPlaying = true;
        System.out.println("Stare radio trzeszczy i gra na falach FM.");
    }

    public void powerOff() {
        isPlaying = false;
        System.out.println("Stare radio zgasło.");
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}