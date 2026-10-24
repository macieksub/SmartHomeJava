package org.example.templatemethod;

// Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Template Method.
// Konkretna implementacja procedury diagnostycznej dla inteligentnego oświetlenia LED / RGB.
public class LightDiagnosticsRoutine extends DeviceDiagnosticsRoutine {

    public LightDiagnosticsRoutine(String lightName) {
        super(lightName);
    }

    @Override
    protected void runHardwareSpecificDiagnostics() {
        System.out.println("-> [TEST ŚWIATŁA]: Test diod LED, sterownika PWM oraz pełnego spektrum barw RGB: 100% sprawne.");
    }

    // Oświetlenie nie wymaga kalibracji czujników (zostaje domyślny hook = false)
}
// Koniec, Tydzień 2, Wzorzec Template Method 7
