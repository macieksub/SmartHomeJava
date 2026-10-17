package org.example.builder;

import java.util.ArrayList;
import java.util.List;

// Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Builder.
// Wzorzec Builder umożliwia elastyczne, czytelne i wieloetapowe tworzenie obiektu sceny,
// oddzielając specyfikację poszczególnych czynności od gotowej postaci gotowego scenariusza.
public class SceneBuilder {
    private String name = "Domyślna Scena";
    private final List<String> actions = new ArrayList<>();

    public SceneBuilder(String name) {
        this.name = name;
    }

    public SceneBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SceneBuilder addLightsOff() {
        actions.add("Wyłącz główne oświetlenie");
        return this;
    }

    public SceneBuilder addTemperatureDrop() {
        actions.add("Obniż temperaturę termostatu do 19°C");
        return this;
    }

    public SceneBuilder addLockDoors() {
        actions.add("Zarygluj zamki w drzwiach wejściowych");
        return this;
    }

    public SceneBuilder addAction(String action) {
        actions.add(action);
        return this;
    }

    public Scene build() {
        return new Scene(name, new ArrayList<>(actions));
    }
}
// Koniec, Tydzień 1, Wzorzec Builder 4