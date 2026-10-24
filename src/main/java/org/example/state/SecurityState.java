package org.example.state;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
// Interfejs Stanu definiujący reakcje systemu ochrony na bodźce (detekcja ruchu, otwarcie drzwi).
public interface SecurityState {
    void onMotionDetected(SecuritySystemContext context, String location);
    void onDoorOpened(SecuritySystemContext context, String doorName);
    String getStateName();
}
// Koniec, Tydzień 2, Wzorzec State 4
