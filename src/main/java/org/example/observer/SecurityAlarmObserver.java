package org.example.observer;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Observer.
// Konkretny obserwator realizujący funkcję syreny alarmowej, powiadamiający służby w razie detekcji intruza.
public class SecurityAlarmObserver implements SensorObserver {
    private boolean alarmTriggered = false;
    private String lastAlertMessage = "";

    @Override
    public void onSensorEvent(String sensorName, String eventType, String location) {
        alarmTriggered = true;
        lastAlertMessage = "[ALARM BEZPIECZEŃSTWA]: Czujnik '" + sensorName + "' zgłosił " + eventType + " w [" + location + "]. Uruchamiam syrenę!";
        System.out.println(lastAlertMessage);
    }

    public boolean isAlarmTriggered() {
        return alarmTriggered;
    }

    public String getLastAlertMessage() {
        return lastAlertMessage;
    }

    public void resetAlarm() {
        alarmTriggered = false;
        lastAlertMessage = "";
    }
}
// Koniec, Tydzień 2, Wzorzec Observer 2
