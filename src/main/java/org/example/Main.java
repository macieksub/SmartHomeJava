package org.example;

import org.example.adapter.OldRadio;
import org.example.adapter.OldRadioAdapter;
import org.example.builder.Scene;
import org.example.builder.SceneBuilder;
import org.example.composite.Room;
import org.example.facade.HomeTheaterFacade;
import org.example.factory.DeviceFactory;
import org.example.model.SmartDevice;
import org.example.prototype.RGBSmartLight;
import org.example.singleton.SmartHomeHub;

public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("   PROJEKT: SYSTEM INTELIGENTNEGO DOMU (SMART HOME SIMULATOR)");
        System.out.println("   LAB 1: Wzorce konstrukcyjne i strukturalne (7 wzorców projektowych)");
        System.out.println("======================================================================\n");

        // -------------------------------------------------------------------
        // 1. WZORZEC SINGLETON
        // -------------------------------------------------------------------
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

        // -------------------------------------------------------------------
        // 2. WZORZEC FACTORY (SIMPLE FACTORY)
        // -------------------------------------------------------------------
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

        // -------------------------------------------------------------------
        // 3. WZORZEC PROTOTYPE
        // -------------------------------------------------------------------
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

        // -------------------------------------------------------------------
        // 4. WZORZEC ADAPTER
        // -------------------------------------------------------------------
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

        // -------------------------------------------------------------------
        // 5. WZORZEC COMPOSITE
        // -------------------------------------------------------------------
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

        // -------------------------------------------------------------------
        // 6. WZORZEC FACADE
        // -------------------------------------------------------------------
        // Tydzień 1, Lab 1 - Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych. Wzorzec Facade.
        // Fasada HomeTheaterFacade ułatwia sterowanie wieloma komponentami kina domowego za pomocą prostych poleceń.
        System.out.println(">>> 6. TEST WZORCA: FACADE (HomeTheaterFacade) <<<");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(livingRoomTV, livingRoomLight);
        homeTheater.watchMovie();
        homeTheater.endMovie();
        // Koniec, Tydzień 1, Wzorzec Facade 7
        System.out.println();

        // -------------------------------------------------------------------
        // 7. WZORZEC BUILDER
        // -------------------------------------------------------------------
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

        System.out.println("======================================================================");
        System.out.println("   SYMULACJA ZAKOŃCZONA POMYŚLNIE (Wszystkie 7 wzorców przetestowane)");
        System.out.println("======================================================================");
    }
}