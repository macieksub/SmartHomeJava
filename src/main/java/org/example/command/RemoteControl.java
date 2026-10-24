package org.example.command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
// Klasa RemoteControl pełni rolę Wywołującego (Invoker), rejestrując historię wykonanych poleceń
// i umożliwiając ich realizację oraz cofanie za pomocą stosu akcji.
public class RemoteControl {
    private final Deque<Command> history = new ArrayDeque<>();
    private final List<String> executionLog = new ArrayList<>();

    public void executeCommand(Command command) {
        if (command == null) {
            return;
        }
        command.execute();
        history.push(command);
        executionLog.add(command.getName());
    }

    public boolean undoLastCommand() {
        if (history.isEmpty()) {
            System.out.println("[PILOT]: Brak akcji do cofnięcia.");
            return false;
        }
        Command lastCommand = history.pop();
        lastCommand.undo();
        executionLog.add("COFNIĘTO: " + lastCommand.getName());
        return true;
    }

    public List<String> getExecutionLog() {
        return new ArrayList<>(executionLog);
    }

    public int getHistorySize() {
        return history.size();
    }
}
// Koniec, Tydzień 2, Wzorzec Command 1
