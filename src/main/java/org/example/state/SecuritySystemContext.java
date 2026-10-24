package org.example.state;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
// Klasa SecuritySystemContext pełni rolę Kontekstu (Context), delegując przetwarzanie zdarzeń bezpieczeństwa
// do obiektu aktualnie aktywnego stanu.
public class SecuritySystemContext {
    private SecurityState currentState;

    public SecuritySystemContext(SecurityState initialState) {
        this.currentState = initialState != null ? initialState : new DisarmedState();
    }

    public void setState(SecurityState state) {
        System.out.println("\n[SYSTEM BEZPIECZEŃSTWA]: Zmiana stanu z [" + currentState.getStateName() + "] na [" + state.getStateName() + "].");
        this.currentState = state;
    }

    public void triggerMotion(String location) {
        currentState.onMotionDetected(this, location);
    }

    public void triggerDoorOpen(String doorName) {
        currentState.onDoorOpened(this, doorName);
    }

    public SecurityState getCurrentState() {
        return currentState;
    }

    public String getStateName() {
        return currentState.getStateName();
    }
}
// Koniec, Tydzień 2, Wzorzec State 4
