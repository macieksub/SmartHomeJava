package org.example.singleton;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Singleton.
// Klasa SmartHomeHub implementuje wzorzec Singleton, gwarantując istnienie wyłącznie jednej instancji
// centrali zarządzającej całym domem oraz udostępniając globalny, bezpieczny wielowątkowo punkt dostępu.
public class SmartHomeHub {
    private static volatile SmartHomeHub instance;
    private String systemStatus;

    private SmartHomeHub() {
        this.systemStatus = "System Smart Home zainicjalizowany i gotowy do pracy.";
    }

    public static SmartHomeHub getInstance() {
        if (instance == null) {
            synchronized (SmartHomeHub.class) {
                if (instance == null) {
                    instance = new SmartHomeHub();
                }
            }
        }
        return instance;
    }

    public void logAction(String message) {
        System.out.println("[HUB LOG]: " + message);
    }

    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }
}
// Koniec, Tydzień 1, Wzorzec Singleton 1