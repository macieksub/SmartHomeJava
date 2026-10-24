package org.example.command;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
// Interfejs bazowy dla poleceń, definiujący metody wykonania operacji (execute) oraz jej cofnięcia (undo).
public interface Command {
    void execute();
    void undo();
    String getName();
}
// Koniec, Tydzień 2, Wzorzec Command 1
