package org.example.state;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
// Stan: Uzbrojony Tryb Domowy (Armed Stay). Domownicy śpią wewnątrz: ruch wewnętrzny jest dozwolony,
// lecz jakiekolwiek otwarcie drzwi zewnętrznych lub ruch perymetryczny wywołuje alarm natychmiastowy.
public class ArmedStayState implements SecurityState {
    @Override
    public void onMotionDetected(SecuritySystemContext context, String location) {
        if (location.toLowerCase().contains("ogród") || location.toLowerCase().contains("zewnątrz") || location.toLowerCase().contains("garaż")) {
            System.out.println("[SYSTEM BEZPIECZEŃSTWA (TRYB DOMOWY)]: ALARM PERYMETRYCZNY! Wykryto ruch na zewnątrz (" + location + ")!");
        } else {
            System.out.println("[SYSTEM BEZPIECZEŃSTWA (TRYB DOMOWY)]: Ruch wewnętrzny (" + location + ") - domownicy wewnątrz.");
        }
    }

    @Override
    public void onDoorOpened(SecuritySystemContext context, String doorName) {
        System.out.println("[SYSTEM BEZPIECZEŃSTWA (TRYB DOMOWY)]: ALARM WŁAMANIOWY! Otwarto drzwi (" + doorName + ") podczas nocnego uzbrojenia!");
    }

    @Override
    public String getStateName() {
        return "UZBROJONY (TRYB DOMOWY)";
    }
}
// Koniec, Tydzień 2, Wzorzec State 4
