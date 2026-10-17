package org.example.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Klasa wynikowa (Product) budowana krok po kroku przez SceneBuilder.
public class Scene {
    private final String name;
    private final List<String> actions;

    Scene(String name, List<String> actions) {
        this.name = name;
        this.actions = actions != null ? actions : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getActions() {
        return Collections.unmodifiableList(actions);
    }

    public void executeScene() {
        System.out.println("--- Wykonywanie Sceny: \"" + name + "\" ---");
        for (String action : actions) {
            System.out.println("-> Akcja: " + action);
        }
    }
}