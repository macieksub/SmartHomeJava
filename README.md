# Smart Home Simulator — System Symulacji Inteligentnego Domu

Projekt realizowany w języku **Java** w ramach laboratoriów akademickich z Zaawansowanych Technik Programowania / Wzorców Projektowych. Projekt rozwija spójną domenę inteligentnego budynku (**Smart Home Simulator**), łącząc w kolejnych iteracjach wzorce konstrukcyjne, strukturalne oraz czynnościowe.

---

## Moduły laboratoryjne

### Lab 1: Stosowanie konstrukcyjnych i strukturalnych wzorców projektowych (21 pkt / 7 wzorców)
1. **Singleton** — [`SmartHomeHub`](src/main/java/org/example/singleton/SmartHomeHub.java) — centrala systemu z globalnym, bezpiecznym wielowątkowo punktem dostępu.
2. **Factory (Simple Factory)** — [`DeviceFactory`](src/main/java/org/example/factory/DeviceFactory.java) — hermetyzacja tworzenia instancji urządzeń (`SmartLight`, `SmartThermostat`, `SmartTV`).
3. **Prototype** — [`RGBSmartLight`](src/main/java/org/example/prototype/RGBSmartLight.java) — klonowanie konfiguracji żarówki RGB metodą `clone()`.
4. **Builder** — [`SceneBuilder`](src/main/java/org/example/builder/SceneBuilder.java) / [`Scene`](src/main/java/org/example/builder/Scene.java) — płynne, etapowe budowanie scenariuszy domowych.
5. **Adapter** — [`OldRadioAdapter`](src/main/java/org/example/adapter/OldRadioAdapter.java) — adaptacja klasycznego radia [`OldRadio`](src/main/java/org/example/adapter/OldRadio.java) do interfejsu `SmartDevice`.
6. **Composite** — [`Room`](src/main/java/org/example/composite/Room.java) — jednorodne zarządzanie pojedynczym urządzeniem oraz całą grupą urządzeń w pokoju.
7. **Facade** — [`HomeTheaterFacade`](src/main/java/org/example/facade/HomeTheaterFacade.java) — uproszczony interfejs wysokiego poziomu sterujący kinem domowym.

---

### Lab 2: Stosowanie czynnościowych wzorców projektowych (21 pkt / 7 wzorców)

Zaimplementowano **7 wzorców behawioralnych (czynnościowych)** (maksymalna ocena: 5.0, 21 punktów):

| Lp. | Wzorzec | Klasy implementujące | Rola w systemie Smart Home |
|:---:|:---:|:---:|:---|
| 1 | **Command** | [`Command`](src/main/java/org/example/command/Command.java), [`TurnOnDeviceCommand`](src/main/java/org/example/command/TurnOnDeviceCommand.java), [`TurnOffDeviceCommand`](src/main/java/org/example/command/TurnOffDeviceCommand.java), [`RemoteControl`](src/main/java/org/example/command/RemoteControl.java) | Hermetyzacja operacji na urządzeniach jako obiektów z historią wywołań oraz mechanizmem wielopoziomowego cofania (**Undo**). |
| 2 | **Observer** | [`MotionSensor`](src/main/java/org/example/observer/MotionSensor.java), [`SensorObserver`](src/main/java/org/example/observer/SensorObserver.java), [`SecurityAlarmObserver`](src/main/java/org/example/observer/SecurityAlarmObserver.java), [`LightingAutomationObserver`](src/main/java/org/example/observer/LightingAutomationObserver.java) | Automatyczne powiadamianie wielu subskrybentów (syrena alarmowa, zapalanie świateł) w reakcji na zdarzenie czujnika ruchu (Subject). |
| 3 | **Strategy** | [`EnergySavingStrategy`](src/main/java/org/example/strategy/EnergySavingStrategy.java), [`EcoEnergyStrategy`](src/main/java/org/example/strategy/EcoEnergyStrategy.java), [`ComfortEnergyStrategy`](src/main/java/org/example/strategy/ComfortEnergyStrategy.java), [`NightEnergyStrategy`](src/main/java/org/example/strategy/NightEnergyStrategy.java), [`EnergyManager`](src/main/java/org/example/strategy/EnergyManager.java) | Wymienne algorytmy optymalizacji zużycia energii i ogrzewania budynku, przełączane dynamicznie w trakcie działania programu. |
| 4 | **State** | [`SecurityState`](src/main/java/org/example/state/SecurityState.java), [`DisarmedState`](src/main/java/org/example/state/DisarmedState.java), [`ArmedStayState`](src/main/java/org/example/state/ArmedStayState.java), [`ArmedAwayState`](src/main/java/org/example/state/ArmedAwayState.java), [`SecuritySystemContext`](src/main/java/org/example/state/SecuritySystemContext.java) | Zmiana zachowania systemu alarmowego w zależności od stanu uzbrojenia (Rozbrojony, Tryb Domowy, Poza Domem) przy tych samych bodźcach wejściowych. |
| 5 | **Memento** | [`DeviceConfigMemento`](src/main/java/org/example/memento/DeviceConfigMemento.java), [`HomeConfigOriginator`](src/main/java/org/example/memento/HomeConfigOriginator.java), [`ConfigHistoryCaretaker`](src/main/java/org/example/memento/ConfigHistoryCaretaker.java) | Zapisywanie niemutowalnych punktów przywracania konfiguracji domu i bezpieczny rollback ustawień bez naruszania hermetyzacji. |
| 6 | **Iterator** | [`SmartDeviceIterator`](src/main/java/org/example/iterator/SmartDeviceIterator.java), [`DeviceAggregate`](src/main/java/org/example/iterator/DeviceAggregate.java), [`DeviceCollection`](src/main/java/org/example/iterator/DeviceCollection.java) | Wzorcowy dostęp sekwencyjny do kolekcji urządzeń, w tym iterator ogólny oraz iterator filtrujący wyłącznie urządzenia aktywne (ON). |
| 7 | **Template Method** | [`DeviceDiagnosticsRoutine`](src/main/java/org/example/templatemethod/DeviceDiagnosticsRoutine.java), [`LightDiagnosticsRoutine`](src/main/java/org/example/templatemethod/LightDiagnosticsRoutine.java), [`ThermostatDiagnosticsRoutine`](src/main/java/org/example/templatemethod/ThermostatDiagnosticsRoutine.java) | Niezmienny szkielet algorytmu procedury testowej sprzętu ze specyficznymi krokami weryfikacji oraz hakami kalibracyjnymi (Hook). |

---

## Wymogi formalne dotyczące komentarzy w kodzie (Wymóg 4)

Zgodnie z wytycznymi prowadzącego:
- **Komentarz początkowy**: zawiera numer tygodnia, pełny tytuł laboratorium oraz 1-2 linijkowy opis techniki:
  ```java
  // Tydzień 2, Lab 2 - Stosowanie czynnościowych wzorców projektowych. Wzorzec Command.
  // Wzorzec Command hermetyzuje żądania wykonania operacji na urządzeniach jako autonomiczne obiekty,
  // umożliwiając parametryzację poleceń, ich kolejkowanie oraz obsługę wielopoziomowego cofania (undo).
  ```
- **Komentarz końcowy**:
  ```java
  // Koniec, Tydzień 2, Wzorzec Command 1
  ```
- Oznaczenia znajdują się zarówno w plikach klas źródłowych, jak i w punktach wywołania w [`Main.java`](src/main/java/org/example/Main.java).

---

## Uruchomienie i testy

### 1. Kompilacja i uruchomienie symulacji
```bash
./gradlew run
```

### 2. Wykonanie testów jednostkowych (14 testów: 7 Lab 1 + 7 Lab 2)
```bash
./gradlew test
```
Klasy testowe:
- [`DesignPatternsTest`](src/test/java/org/example/DesignPatternsTest.java) (Lab 1)
- [`BehavioralPatternsTest`](src/test/java/org/example/BehavioralPatternsTest.java) (Lab 2)
