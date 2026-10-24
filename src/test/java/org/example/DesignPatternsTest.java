package org.example;

import org.example.adapter.OldRadio;
import org.example.adapter.OldRadioAdapter;
import org.example.builder.Scene;
import org.example.builder.SceneBuilder;
import org.example.composite.Room;
import org.example.facade.HomeTheaterFacade;
import org.example.factory.DeviceFactory;
import org.example.model.SmartDevice;
import org.example.model.SmartLight;
import org.example.model.SmartTV;
import org.example.model.SmartThermostat;
import org.example.prototype.RGBSmartLight;
import org.example.singleton.SmartHomeHub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DesignPatternsTest {

    @Test
    @DisplayName("1. Singleton: SmartHomeHub zwraca zawsze tę samą instancję")
    void testSingleton() {
        SmartHomeHub hub1 = SmartHomeHub.getInstance();
        SmartHomeHub hub2 = SmartHomeHub.getInstance();

        assertNotNull(hub1);
        assertSame(hub1, hub2, "SmartHomeHub musi być tą samą instancją (Singleton)");
    }

    @Test
    @DisplayName("2. Factory: DeviceFactory poprawnie tworzy różne typy urządzeń")
    void testFactory() {
        DeviceFactory factory = new DeviceFactory();

        SmartDevice light = factory.createDevice("LIGHT");
        SmartDevice thermostat = factory.createDevice("THERMOSTAT");
        SmartDevice tv = factory.createDevice("TV");

        assertInstanceOf(SmartLight.class, light);
        assertInstanceOf(SmartThermostat.class, thermostat);
        assertInstanceOf(SmartTV.class, tv);

        assertThrows(IllegalArgumentException.class, () -> factory.createDevice("UNKNOWN_DEVICE"));
    }

    @Test
    @DisplayName("3. Prototype: Klonowanie żarówki RGB tworzy niezależną kopię")
    void testPrototype() {
        RGBSmartLight original = new RGBSmartLight("Ciepły Biały", 100);
        RGBSmartLight clone = original.clone();

        assertNotNull(clone);
        assertNotSame(original, clone, "Klon musi być nowym obiektem w pamięci");
        assertEquals(original.getColor(), clone.getColor());
        assertEquals(original.getBrightness(), clone.getBrightness());

        // Zmiana parametrów klona nie powinna modyfikować oryginału
        clone.setColor("Czerwony");
        clone.setBrightness(30);

        assertEquals("Ciepły Biały", original.getColor());
        assertEquals(100, original.getBrightness());
        assertEquals("Czerwony", clone.getColor());
        assertEquals(30, clone.getBrightness());
    }

    @Test
    @DisplayName("4. Adapter: OldRadioAdapter adaptuje OldRadio do SmartDevice")
    void testAdapter() {
        OldRadio oldRadio = new OldRadio();
        assertFalse(oldRadio.isPlaying());

        SmartDevice adapter = new OldRadioAdapter(oldRadio);
        adapter.turnOn();
        assertTrue(oldRadio.isPlaying());
        assertTrue(adapter.getStatus().contains("ON"));

        adapter.turnOff();
        assertFalse(oldRadio.isPlaying());
        assertTrue(adapter.getStatus().contains("OFF"));
    }

    @Test
    @DisplayName("5. Composite: Room zarządza grupą urządzeń jednolicie")
    void testComposite() {
        Room room = new Room("Sypialnia");
        SmartLight light = new SmartLight();
        SmartThermostat thermostat = new SmartThermostat();

        room.addDevice(light);
        room.addDevice(thermostat);

        assertEquals(2, room.getDevices().size());
        assertFalse(light.isOn());
        assertFalse(thermostat.isOn());

        room.turnOn();
        assertTrue(light.isOn());
        assertTrue(thermostat.isOn());

        room.turnOff();
        assertFalse(light.isOn());
        assertFalse(thermostat.isOn());

        room.removeDevice(thermostat);
        assertEquals(1, room.getDevices().size());
    }

    @Test
    @DisplayName("6. Facade: HomeTheaterFacade steruje TV i oświetleniem")
    void testFacade() {
        SmartLight light = new SmartLight();
        SmartTV tv = new SmartTV();

        light.turnOn();
        assertTrue(light.isOn());

        HomeTheaterFacade facade = new HomeTheaterFacade(tv, light);
        facade.watchMovie();

        assertFalse(light.isOn(), "Podczas oglądania filmu światła powinny być zgaszone");
        assertTrue(tv.getStatus().contains("ON"), "Podczas seansu TV powinien być włączony");

        facade.endMovie();
        assertFalse(tv.getStatus().contains("ON"), "Po zakończeniu filmu TV powinien być wyłączony");
        assertTrue(light.isOn(), "Po zakończeniu filmu światła powinny być włączone");
    }

    @Test
    @DisplayName("7. Builder: SceneBuilder tworzy obiekt Scene z poprawną listą akcji")
    void testBuilder() {
        Scene scene = new SceneBuilder("Wieczór filmowy")
                .addLockDoors()
                .addTemperatureDrop()
                .addLightsOff()
                .addAction("Włącz projektor")
                .build();

        assertNotNull(scene);
        assertEquals("Wieczór filmowy", scene.getName());
        assertEquals(4, scene.getActions().size());
        assertTrue(scene.getActions().contains("Zarygluj zamki w drzwiach wejściowych"));
        assertTrue(scene.getActions().contains("Obniż temperaturę termostatu do 19°C"));
        assertTrue(scene.getActions().contains("Wyłącz główne oświetlenie"));
        assertTrue(scene.getActions().contains("Włącz projektor"));
    }
}
