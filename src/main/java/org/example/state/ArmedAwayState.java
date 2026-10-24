package org.example.state;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
// Stan: Uzbrojony Poza Domem (Armed Away). Domownicy poza budynkiem: jakikolwiek ruch lub otwarcie drzwi
// natychmiast wyzwala pełen alarm przeciwwłamaniowy i powiadamia agencję ochrony.
public class ArmedAwayState implements SecurityState {
    @Override
    public void onMotionDetected(SecuritySystemContext context, String location) {
        System.out.println("[SYSTEM BEZPIECZEŃSTWA (POZA DOMEM)]: KRYTYCZNY ALARM WŁAMANIOWY! Wykryto intruza w: " + location + "! Powiadamianie policji!");
    }

    @Override
    public void onDoorOpened(SecuritySystemContext context, String doorName) {
        System.out.println("[SYSTEM BEZPIECZEŃSTWA (POZA DOMEM)]: KRYTYCZNY ALARM WŁAMANIOWY! Wyważono drzwi: " + doorName + "! Uruchomienie syren i blokad!");
    }

    @Override
    public String getStateName() {
        return "UZBROJONY (POZA DOMEM)";
    }
}
// Koniec, Tydzień 2, Wzorzec State 4
