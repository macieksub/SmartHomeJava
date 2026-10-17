package org.example.facade;

import org.example.model.SmartDevice;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Facade.
// Fasada HomeTheaterFacade dostarcza wyspecjalizowany, zwięzły interfejs ukrywający złożoność
// operowania na wielu podsystemach (telewizor, oświetlenie) podczas włączania i kończenia seansu filmowego.
public class HomeTheaterFacade {
    private final SmartDevice tv;
    private final SmartDevice lights;

    public HomeTheaterFacade(SmartDevice tv, SmartDevice lights) {
        this.tv = tv;
        this.lights = lights;
    }

    public void watchMovie() {
        System.out.println("\n--- [Fasada Kina Domowego]: Uruchamianie trybu filmowego ---");
        System.out.println("Krok 1: Wygaszanie oświetlenia w pokoju...");
        lights.turnOff();
        System.out.println("Krok 2: Uruchamianie telewizora...");
        tv.turnOn();
        System.out.println("Gotowe! Życzymy udanego seansu filmowego.");
    }

    public void endMovie() {
        System.out.println("\n--- [Fasada Kina Domowego]: Wyłączanie trybu filmowego ---");
        System.out.println("Krok 1: Wyłączanie telewizora...");
        tv.turnOff();
        System.out.println("Krok 2: Przywracanie głównego oświetlenia...");
        lights.turnOn();
        System.out.println("Kino domowe zostało wyłączone.");
    }

    public SmartDevice getTv() {
        return tv;
    }

    public SmartDevice getLights() {
        return lights;
    }
}
// Koniec, Tydzień 1, Wzorzec Facade 7