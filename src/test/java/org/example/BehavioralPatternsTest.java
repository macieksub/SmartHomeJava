package org.example;

import org.example.command.Command;
import org.example.command.RemoteControl;
import org.example.command.TurnOffDeviceCommand;
import org.example.command.TurnOnDeviceCommand;
import org.example.iterator.DeviceCollection;
import org.example.iterator.SmartDeviceIterator;
import org.example.memento.ConfigHistoryCaretaker;
import org.example.memento.DeviceConfigMemento;
import org.example.memento.HomeConfigOriginator;
import org.example.model.SmartLight;
import org.example.model.SmartThermostat;
import org.example.observer.LightingAutomationObserver;
import org.example.observer.MotionSensor;
import org.example.observer.SecurityAlarmObserver;
import org.example.state.ArmedAwayState;
import org.example.state.ArmedStayState;
import org.example.state.DisarmedState;
import org.example.state.SecuritySystemContext;
import org.example.strategy.ComfortEnergyStrategy;
import org.example.strategy.EcoEnergyStrategy;
import org.example.strategy.EnergyManager;
import org.example.strategy.NightEnergyStrategy;
import org.example.templatemethod.LightDiagnosticsRoutine;
import org.example.templatemethod.ThermostatDiagnosticsRoutine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BehavioralPatternsTest {

    @Test
    @DisplayName("1. Command: Wykonywanie poleceń oraz operacja cofania (Undo)")
    void testCommand() {
        SmartLight light = new SmartLight();
        RemoteControl remote = new RemoteControl();

        Command onCmd = new TurnOnDeviceCommand(light, "Lampa Gabinet");
        Command offCmd = new TurnOffDeviceCommand(light, "Lampa Gabinet");

        assertFalse(light.isOn());

        remote.executeCommand(onCmd);
        assertTrue(light.isOn());
        assertEquals(1, remote.getHistorySize());

        remote.executeCommand(offCmd);
        assertFalse(light.isOn());
        assertEquals(2, remote.getHistorySize());

        boolean undone = remote.undoLastCommand();
        assertTrue(undone);
        assertTrue(light.isOn(), "Cofnięcie wyłączenia powinno przywrócić włączenie światła");
        assertEquals(1, remote.getHistorySize());
    }

    @Test
    @DisplayName("2. Observer: Powiadamianie obserwatorów po wykryciu ruchu przez czujnik")
    void testObserver() {
        MotionSensor sensor = new MotionSensor("Sensor Garaż");
        SecurityAlarmObserver alarm = new SecurityAlarmObserver();
        SmartLight light = new SmartLight();
        LightingAutomationObserver autoLight = new LightingAutomationObserver(light);

        sensor.attach(alarm);
        sensor.attach(autoLight);
        assertEquals(2, sensor.getObserverCount());

        assertFalse(alarm.isAlarmTriggered());
        assertFalse(light.isOn());

        sensor.triggerMotion("Garaż");

        assertTrue(alarm.isAlarmTriggered());
        assertTrue(light.isOn());
        assertEquals(1, autoLight.getTriggerCount());

        sensor.detach(alarm);
        assertEquals(1, sensor.getObserverCount());
    }

    @Test
    @DisplayName("3. Strategy: Wymienne strategie zarządzania energią (Eco, Comfort, Night)")
    void testStrategy() {
        SmartThermostat thermostat = new SmartThermostat();
        SmartLight light = new SmartLight();
        EnergyManager manager = new EnergyManager(new ComfortEnergyStrategy());

        manager.applyCurrentStrategy(thermostat, light);
        assertEquals(22.5, thermostat.getTemperature());
        assertTrue(light.isOn());

        manager.setStrategy(new EcoEnergyStrategy());
        manager.applyCurrentStrategy(thermostat, light);
        assertEquals(18.0, thermostat.getTemperature());
        assertFalse(light.isOn());

        manager.setStrategy(new NightEnergyStrategy());
        manager.applyCurrentStrategy(thermostat, light);
        assertEquals(19.5, thermostat.getTemperature());
        assertFalse(light.isOn());
    }

    @Test
    @DisplayName("4. State: Zmiana zachowania systemu bezpieczeństwa w zależności od stanu")
    void testState() {
        SecuritySystemContext system = new SecuritySystemContext(new DisarmedState());
        assertEquals("ROZBROJONY", system.getStateName());

        system.setState(new ArmedStayState());
        assertEquals("UZBROJONY (TRYB DOMOWY)", system.getStateName());

        system.setState(new ArmedAwayState());
        assertEquals("UZBROJONY (POZA DOMEM)", system.getStateName());
    }

    @Test
    @DisplayName("5. Memento: Zapisywanie i odtwarzanie punktów przywracania konfiguracji")
    void testMemento() {
        HomeConfigOriginator config = new HomeConfigOriginator(true, "Biały", 80, 21.0);
        ConfigHistoryCaretaker caretaker = new ConfigHistoryCaretaker();

        DeviceConfigMemento backup1 = config.saveToMemento("Domyślny");
        caretaker.pushMemento(backup1);

        config.setLightOn(false);
        config.setLightColor("Czerwony");
        config.setThermostatTemp(17.5);

        assertFalse(config.isLightOn());
        assertEquals("Czerwony", config.getLightColor());
        assertEquals(17.5, config.getThermostatTemp());

        DeviceConfigMemento restoredMemento = caretaker.popMemento();
        config.restoreFromMemento(restoredMemento);

        assertTrue(config.isLightOn());
        assertEquals("Biały", config.getLightColor());
        assertEquals(21.0, config.getThermostatTemp());
    }

    @Test
    @DisplayName("6. Iterator: Sekwencyjne przeglądanie wszystkich oraz tylko aktywnych urządzeń")
    void testIterator() {
        DeviceCollection collection = new DeviceCollection();
        SmartLight light1 = new SmartLight();
        SmartLight light2 = new SmartLight();
        SmartThermostat thermostat = new SmartThermostat();

        light1.turnOn();
        // light2 wyłączone
        thermostat.turnOn();

        collection.addDevice(light1);
        collection.addDevice(light2);
        collection.addDevice(thermostat);

        assertEquals(3, collection.size());

        // Test iteratora wszystkich urządzeń
        SmartDeviceIterator allIter = collection.createIterator();
        int totalCount = 0;
        while (allIter.hasNext()) {
            assertNotNull(allIter.next());
            totalCount++;
        }
        assertEquals(3, totalCount);

        // Test iteratora filtrującego (tylko włączone)
        SmartDeviceIterator activeIter = collection.createActiveDevicesIterator();
        int activeCount = 0;
        while (activeIter.hasNext()) {
            assertTrue(activeIter.next().isTurnedOn());
            activeCount++;
        }
        assertEquals(2, activeCount);
    }

    @Test
    @DisplayName("7. Template Method: Niezmienna metoda szablonowa z hakami kalibracyjnymi")
    void testTemplateMethod() {
        LightDiagnosticsRoutine lightRoutine = new LightDiagnosticsRoutine("Lampa Sufitowa");
        ThermostatDiagnosticsRoutine thermostatRoutine = new ThermostatDiagnosticsRoutine("Termostat Salon");

        assertFalse(thermostatRoutine.isCalibrationPerformed());

        // Wykonanie metody szablonowej
        lightRoutine.runDiagnostics();
        thermostatRoutine.runDiagnostics();

        assertTrue(thermostatRoutine.isCalibrationPerformed(), "Termostat powinien wywołać hak kalibracji sensorów");
    }
}
