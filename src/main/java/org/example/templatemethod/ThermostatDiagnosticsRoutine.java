package org.example.templatemethod;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Template Method.
// Konkretna implementacja procedury diagnostycznej dla termostatu, wykorzystująca metodę hakową (hook) do kalibracji sondy ciepła.
public class ThermostatDiagnosticsRoutine extends DeviceDiagnosticsRoutine {
    private boolean calibrationPerformed = false;

    public ThermostatDiagnosticsRoutine(String thermostatName) {
        super(thermostatName);
    }

    @Override
    protected void runHardwareSpecificDiagnostics() {
        System.out.println("-> [TEST TERMOSTATU]: Weryfikacja przekaźnika zaworu grzewczego i układu PID: 100% sprawne.");
    }

    @Override
    protected boolean requiresCalibrationHook() {
        return true; // Termostat wymaga kalibracji sensora temperatury
    }

    @Override
    protected void calibrateSensors() {
        calibrationPerformed = true;
        System.out.println("-> [HAK KALIBRACJI]: Przeprowadzono precyzyjną kalibrację cyfrowego czujnika temperatury NTC.");
    }

    public boolean isCalibrationPerformed() {
        return calibrationPerformed;
    }
}
// Koniec, Tydzień 2, Wzorzec Template Method 7
