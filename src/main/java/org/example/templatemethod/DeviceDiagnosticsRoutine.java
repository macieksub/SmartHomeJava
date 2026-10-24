package org.example.templatemethod;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Template Method.
// Klasa abstrakcyjna definiująca wzorcową metodę szablonową (runDiagnostics), ustalającą
// niezmienny porządek kroków procedury diagnostycznej dla każdego typu sprzętu Smart Home.
public abstract class DeviceDiagnosticsRoutine {
    private final String deviceName;

    public DeviceDiagnosticsRoutine(String deviceName) {
        this.deviceName = deviceName;
    }

    // Metoda szablonowa (Template Method) - finalna, by podklasy nie mogły zmienić struktury algorytmu
    public final void runDiagnostics() {
        System.out.println("\n--- [DIAGNOSTYKA]: Rozpoczynam procedurę testową dla: " + deviceName + " ---");
        initializeEnvironment();
        checkPowerSupply();
        runHardwareSpecificDiagnostics();
        if (requiresCalibrationHook()) {
            calibrateSensors();
        }
        generateDiagnosticReport();
        System.out.println("--- [DIAGNOSTYKA]: Zakończono pomyślnie dla: " + deviceName + " ---");
    }

    // Krok wspólny 1
    private void initializeEnvironment() {
        System.out.println("1. Inicjalizacja kanału diagnostycznego i magistrali komunikacyjnej...");
    }

    // Krok wspólny 2
    private void checkPowerSupply() {
        System.out.println("2. Weryfikacja parametrów zasilania (napięcie nominalne 230V / 5V DC - OK).");
    }

    // Krok abstrakcyjny - implementowany przez konkretne podklasy
    protected abstract void runHardwareSpecificDiagnostics();

    // Metoda hakowa (Hook) - opcjonalne rozszerzenie zachowania przez podklasę
    protected boolean requiresCalibrationHook() {
        return false;
    }

    protected void calibrateSensors() {
        // Domyślnie brak operacji
    }

    // Krok wspólny 3
    private void generateDiagnosticReport() {
        System.out.println("3. Zapisano raport diagnostyczny w pamięci centrali SmartHomeHub.");
    }

    public String getDeviceName() {
        return deviceName;
    }
}
// Koniec, Tydzień 2, Wzorzec Template Method 7
