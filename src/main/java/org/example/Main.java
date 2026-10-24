package org.example;

import org.example.adapter.OldRadio;
import org.example.adapter.OldRadioAdapter;
import org.example.builder.Scene;
import org.example.builder.SceneBuilder;
import org.example.command.Command;
import org.example.command.RemoteControl;
import org.example.command.TurnOffDeviceCommand;
import org.example.command.TurnOnDeviceCommand;
import org.example.composite.Room;
import org.example.facade.HomeTheaterFacade;
import org.example.factory.DeviceFactory;
import org.example.iterator.DeviceCollection;
import org.example.iterator.SmartDeviceIterator;
import org.example.memento.ConfigHistoryCaretaker;
import org.example.memento.DeviceConfigMemento;
import org.example.memento.HomeConfigOriginator;
import org.example.model.SmartDevice;
import org.example.model.SmartLight;
import org.example.model.SmartTV;
import org.example.model.SmartThermostat;
import org.example.observer.LightingAutomationObserver;
import org.example.observer.MotionSensor;
import org.example.observer.SecurityAlarmObserver;
import org.example.prototype.RGBSmartLight;
import org.example.singleton.SmartHomeHub;
import org.example.state.ArmedAwayState;
import org.example.state.ArmedStayState;
import org.example.state.DisarmedState;
import org.example.state.SecuritySystemContext;
import org.example.strategy.ComfortEnergyStrategy;
import org.example.strategy.EcoEnergyStrategy;
import org.example.strategy.EnergyManager;
import org.example.strategy.NightEnergyStrategy;
import org.example.templatemethod.DeviceDiagnosticsRoutine;
import org.example.templatemethod.LightDiagnosticsRoutine;
import org.example.templatemethod.ThermostatDiagnosticsRoutine;

public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("          SYSTEM INTELIGENTNEGO DOMU (SMART HOME SIMULATOR)");
        System.out.println("   LAB 1: Wzorce konstrukcyjne i strukturalne (7 wzorców)");
        System.out.println("   LAB 2: Wzorce czynnościowe / behawioralne   (7 wzorców)");
        System.out.println("======================================================================\n");

        // ===================================================================
        // SEKCJA 1: WZORCE Z ETAPU LAB 1 (Konstrukcyjne i Strukturalne)
        // ===================================================================
        System.out.println("######################################################################");
        System.out.println("                 CZĘŚĆ I: WZORCE Z LABORATORIUM 1                     ");
        System.out.println("######################################################################\n");

        // 1. WZORZEC SINGLETON
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Singleton.
        // Wykorzystanie unikalnej instancji klasy SmartHomeHub do zarządzania systemem i rejestrowania logów.
        System.out.println(">>> 1. TEST WZORCA: SINGLETON (SmartHomeHub) <<<");
        SmartHomeHub hub1 = SmartHomeHub.getInstance();
        SmartHomeHub hub2 = SmartHomeHub.getInstance();
        hub1.logAction("Uruchomiono system centrali domowej.");
        System.out.println("Czy obie referencje hub1 i hub2 wskazują na tę samą instancję? " + (hub1 == hub2));
        System.out.println("Status centrali: " + hub1.getSystemStatus());
        // Koniec, Tydzień 1, Wzorzec Singleton 1
        System.out.println();

        // 2. WZORZEC FACTORY (SIMPLE FACTORY)
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Factory (Simple Factory).
        // Fabryka DeviceFactory tworzy obiekty oświetlenia, termostatu i telewizora bez ujawniania logiki ich kreacji.
        System.out.println(">>> 2. TEST WZORCA: FACTORY (DeviceFactory) <<<");
        DeviceFactory factory = new DeviceFactory();
        SmartDevice livingRoomLight = factory.createDevice("LIGHT");
        SmartDevice livingRoomThermostat = factory.createDevice("THERMOSTAT");
        SmartDevice livingRoomTV = factory.createDevice("TV");

        System.out.println("Utworzono: " + livingRoomLight.getStatus());
        System.out.println("Utworzono: " + livingRoomThermostat.getStatus());
        System.out.println("Utworzono: " + livingRoomTV.getStatus());
        // Koniec, Tydzień 1, Wzorzec Factory Method 2
        System.out.println();

        // 3. WZORZEC PROTOTYPE
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Prototype.
        // Powielenie wzorcowej żarówki RGB za pomocą metody clone() i modyfikacja parametrów kopii.
        System.out.println(">>> 3. TEST WZORCA: PROTOTYPE (RGBSmartLight) <<<");
        RGBSmartLight defaultLight = new RGBSmartLight("Ciepły Biały", 100);
        RGBSmartLight bedroomLight = defaultLight.clone();
        bedroomLight.setColor("Niebieski");
        bedroomLight.setBrightness(40);

        System.out.println("Oryginał (wzorzec): " + defaultLight.getStatus());
        System.out.println("Sklonowana kopia:   " + bedroomLight.getStatus());
        bedroomLight.turnOn();
        // Koniec, Tydzień 1, Wzorzec Prototype 3
        System.out.println();

        // 4. WZORZEC ADAPTER
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Adapter.
        // Zaadaptowanie niekompatybilnego klasycznego radia (OldRadio) do interfejsu SmartDevice.
        System.out.println(">>> 4. TEST WZORCA: ADAPTER (OldRadioAdapter) <<<");
        OldRadio myOldRadio = new OldRadio();
        SmartDevice adaptedRadio = new OldRadioAdapter(myOldRadio);

        System.out.println("Włączanie zaadaptowanego urządzenia:");
        adaptedRadio.turnOn();
        System.out.println("Status adaptera: " + adaptedRadio.getStatus());
        adaptedRadio.turnOff();
        // Koniec, Tydzień 1, Wzorzec Adapter 5
        System.out.println();

        // 5. WZORZEC COMPOSITE
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Composite.
        // Klasa Room grupuje wiele urządzeń i pozwala sterować całym pomieszczeniem jak pojedynczym urządzeniem.
        System.out.println(">>> 5. TEST WZORCA: COMPOSITE (Room) <<<");
        Room livingRoom = new Room("Salon");
        livingRoom.addDevice(livingRoomLight);
        livingRoom.addDevice(livingRoomThermostat);
        livingRoom.addDevice(livingRoomTV);
        livingRoom.addDevice(adaptedRadio);

        System.out.println(livingRoom.getStatus());
        System.out.println("-- Grupowe włączenie urządzeń w salonie --");
        livingRoom.turnOn();
        System.out.println("-- Grupowe wyłączenie urządzeń w salonie --");
        livingRoom.turnOff();
        // Koniec, Tydzień 1, Wzorzec Composite 6
        System.out.println();

        // 6. WZORZEC FACADE
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Facade.
        // Fasada HomeTheaterFacade ułatwia sterowanie wieloma komponentami kina domowego za pomocą prostych poleceń.
        System.out.println(">>> 6. TEST WZORCA: FACADE (HomeTheaterFacade) <<<");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(livingRoomTV, livingRoomLight);
        homeTheater.watchMovie();
        homeTheater.endMovie();
        // Koniec, Tydzień 1, Wzorzec Facade 7
        System.out.println();

        // 7. WZORZEC BUILDER
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Builder.
        // Płynne budowanie obiektu scenariusza domowego Scene krok po kroku za pomocą SceneBuilder.
        System.out.println(">>> 7. TEST WZORCA: BUILDER (SceneBuilder) <<<");
        Scene goodNightScene = new SceneBuilder("Scena: Dobranoc")
                .addLockDoors()
                .addTemperatureDrop()
                .addLightsOff()
                .addAction("Aktywuj monitoring nocny i czujniki ruchu")
                .build();

        goodNightScene.executeScene();
        // Koniec, Tydzień 1, Wzorzec Builder 4
        System.out.println();

        // ===================================================================
        // SEKCJA 2: WZORCE Z ETAPU LAB 2 (Czynnościowe / Behawioralne)
        // ===================================================================
        System.out.println("\n######################################################################");
        System.out.println("                 CZĘŚĆ II: WZORCE Z LABORATORIUM 2                    ");
        System.out.println("######################################################################\n");

        // 1. WZORZEC COMMAND
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
        // Sterowanie urządzeniami za pośrednictwem obiektów poleceń z obsługą historii wywołań i cofania (undo).
        System.out.println(">>> 1. TEST WZORCA: COMMAND (RemoteControl & DeviceCommands) <<<");
        RemoteControl remote = new RemoteControl();
        SmartDevice hallLight = new SmartLight();
        Command turnOnHallLight = new TurnOnDeviceCommand(hallLight, "Światło w Korytarzu");
        Command turnOffHallLight = new TurnOffDeviceCommand(hallLight, "Światło w Korytarzu");

        remote.executeCommand(turnOnHallLight);
        remote.executeCommand(turnOffHallLight);
        System.out.println("Cofanie ostatniej operacji:");
        remote.undoLastCommand(); // Cofnięcie wyłączenia -> światło wraca do stanu włączonego
        System.out.println("Stan światła po cofnięciu: " + hallLight.getStatus());
        // Koniec, Tydzień 2, Wzorzec Command 1
        System.out.println();

        // 2. WZORZEC OBSERVER
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Observer.
        // Czujnik ruchu powiadamia automatycznie subskrybentów (syrenę alarmową oraz oświetlenie wejścia).
        System.out.println(">>> 2. TEST WZORCA: OBSERVER (MotionSensor & Observers) <<<");
        MotionSensor corridorSensor = new MotionSensor("Czujnik Korytarz Główny");
        SecurityAlarmObserver alarm = new SecurityAlarmObserver();
        LightingAutomationObserver autoLight = new LightingAutomationObserver(new SmartLight());

        corridorSensor.attach(alarm);
        corridorSensor.attach(autoLight);
        corridorSensor.triggerMotion("Strefa Wejściowa / Drzwi Główne");
        // Koniec, Tydzień 2, Wzorzec Observer 2
        System.out.println();

        // 3. WZORZEC STRATEGY
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Strategy.
        // Wymienne algorytmy optymalizacji zużycia energii elektrycznej i ogrzewania (Eko, Komfort, Nocny).
        System.out.println(">>> 3. TEST WZORCA: STRATEGY (EnergyManager & Strategies) <<<");
        SmartThermostat hvacThermostat = new SmartThermostat();
        SmartLight livingLight = new SmartLight();
        EnergyManager energyManager = new EnergyManager(new ComfortEnergyStrategy());

        energyManager.applyCurrentStrategy(hvacThermostat, livingLight);
        System.out.println("Status: " + hvacThermostat.getStatus() + ", " + livingLight.getStatus());

        energyManager.setStrategy(new EcoEnergyStrategy());
        energyManager.applyCurrentStrategy(hvacThermostat, livingLight);
        System.out.println("Status: " + hvacThermostat.getStatus() + ", " + livingLight.getStatus());

        energyManager.setStrategy(new NightEnergyStrategy());
        energyManager.applyCurrentStrategy(hvacThermostat, livingLight);
        System.out.println("Status: " + hvacThermostat.getStatus() + ", " + livingLight.getStatus());
        // Koniec, Tydzień 2, Wzorzec Strategy 3
        System.out.println();

        // 4. WZORZEC STATE
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec State.
        // Zmiana reakcji systemu alarmowego na zdarzenia w zależności od aktywnego stanu uzbrojenia.
        System.out.println(">>> 4. TEST WZORCA: STATE (SecuritySystemContext & States) <<<");
        SecuritySystemContext securitySystem = new SecuritySystemContext(new DisarmedState());
        securitySystem.triggerMotion("Hol Wejściowy");

        securitySystem.setState(new ArmedStayState());
        securitySystem.triggerMotion("Kuchnia");
        securitySystem.triggerDoorOpen("Drzwi Tarasowe");

        securitySystem.setState(new ArmedAwayState());
        securitySystem.triggerMotion("Salon Główny");
        // Koniec, Tydzień 2, Wzorzec State 4
        System.out.println();

        // 5. WZORZEC MEMENTO
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Memento.
        // Zapisywanie zrzutów konfiguracji domu (Originator) i bezpieczne przywracanie stanu z historii (Caretaker).
        System.out.println(">>> 5. TEST WZORCA: MEMENTO (HomeConfigOriginator & Caretaker) <<<");
        HomeConfigOriginator homeConfig = new HomeConfigOriginator(true, "Złoty Ciepły", 70, 21.0);
        ConfigHistoryCaretaker caretaker = new ConfigHistoryCaretaker();

        caretaker.pushMemento(homeConfig.saveToMemento("Domyślny Wieczór"));

        // Zmiana parametrów np. podczas głośnej imprezy
        homeConfig.setLightColor("Neonowy Fiolet");
        homeConfig.setLightBrightness(100);
        homeConfig.setThermostatTemp(19.0);
        System.out.println("Stan po zmianach (Tryb Imprezowy): " + homeConfig.getStatus());

        // Przywrócenie pierwotnego stanu ze zrzutu Memento
        DeviceConfigMemento savedState = caretaker.popMemento();
        homeConfig.restoreFromMemento(savedState);
        // Koniec, Tydzień 2, Wzorzec Memento 5
        System.out.println();

        // 6. WZORZEC ITERATOR
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Iterator.
        // Sekwencyjny dostęp do urządzeń za pomocą dedykowanych iteratorów (wszystkie vs tylko aktywne).
        System.out.println(">>> 6. TEST WZORCA: ITERATOR (DeviceCollection & SmartDeviceIterator) <<<");
        DeviceCollection deviceCollection = new DeviceCollection();
        SmartDevice dev1 = new SmartLight();
        SmartDevice dev2 = new SmartThermostat();
        SmartDevice dev3 = new SmartTV();

        dev1.turnOn();
        // dev2 pozostaje wyłączony
        dev3.turnOn();

        deviceCollection.addDevice(dev1);
        deviceCollection.addDevice(dev2);
        deviceCollection.addDevice(dev3);

        System.out.println("-- 1. Przeglądanie WSZYSTKICH urządzeń (Default Iterator): --");
        SmartDeviceIterator allIterator = deviceCollection.createIterator();
        while (allIterator.hasNext()) {
            System.out.println("-> " + allIterator.next().getStatus());
        }

        System.out.println("-- 2. Przeglądanie TYLKO WŁĄCZONYCH urządzeń (Active Devices Iterator): --");
        SmartDeviceIterator activeIterator = deviceCollection.createActiveDevicesIterator();
        while (activeIterator.hasNext()) {
            System.out.println("-> [WŁĄCZONE]: " + activeIterator.next().getStatus());
        }
        // Koniec, Tydzień 2, Wzorzec Iterator 6
        System.out.println();

        // 7. WZORZEC TEMPLATE METHOD
        // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Template Method.
        // Wykonanie wystandaryzowanej procedury testowej sprzętu z dedykowanymi krokami dla oświetlenia i termostatu.
        System.out.println(">>> 7. TEST WZORCA: TEMPLATE METHOD (DeviceDiagnosticsRoutine) <<<");
        DeviceDiagnosticsRoutine lightCheck = new LightDiagnosticsRoutine("Główny Żyrandol LED");
        DeviceDiagnosticsRoutine thermostatCheck = new ThermostatDiagnosticsRoutine("Termostat Pieca Gazowego");

        lightCheck.runDiagnostics();
        thermostatCheck.runDiagnostics();
        // Koniec, Tydzień 2, Wzorzec Template Method 7
        System.out.println();

        System.out.println("======================================================================");
        System.out.println("       SYMULACJA ZAKOŃCZONA POMYŚLNIE (14 wzorców: Lab 1 + Lab 2)     ");
        System.out.println("======================================================================");
    }
}
