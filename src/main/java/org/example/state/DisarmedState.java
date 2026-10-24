package org.example.state;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
// Stan: Rozbrojony (Disarmed). Ruch i otwarcia drzwi są traktowane jako normalna aktywność mieszkańców.
public class DisarmedState implements SecurityState {
    @Override
    public void onMotionDetected(SecuritySystemContext context, String location) {
        System.out.println("[SYSTEM BEZPIECZEŃSTWA (ROZBROJONY)]: Ruch w: " + location + ". Brak reakcji alarmowej (domownicy w domu).");
    }

    @Override
    public void onDoorOpened(SecuritySystemContext context, String doorName) {
        System.out.println("[SYSTEM BEZPIECZEŃSTWA (ROZBROJONY)]: Otwarto drzwi: " + doorName + ". Zezwolono na swobodne wejście.");
    }

    @Override
    public String getStateName() {
        return "ROZBROJONY";
    }
}
// Koniec, Tydzień 2, Wzorzec State 4
