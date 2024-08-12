import java.util.Scanner;

// Command Interface
interface Command {
    void execute();
}

// Light Class (Receiver)
class Light {
    public void turnOn() {
        System.out.println("Light is turned on");
    }

    public void turnOff() {
        System.out.println("Light is turned off");
    }

    public void dim(int level) {
        System.out.println("Light is dimmed to " + level + "%");
    }
}

// Concrete Commands for Light
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class DimLightCommand implements Command {
    private Light light;
    private int level;

    public DimLightCommand(Light light, int level) {
        this.light = light;
        this.level = level;
    }

    @Override
    public void execute() {
        light.dim(level);
    }
}

// Remote Control Class (Invoker) for Light
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Thermostat Class (Receiver)
class Thermostat {
    public void setTemperature(int temperature) {
        System.out.println("Thermostat temperature set to " + temperature + "\u00B0C");
    }

    public void switchToCoolingMode() {
        System.out.println("Thermostat switched to cooling mode");
    }

    public void switchToHeatingMode() {
        System.out.println("Thermostat switched to heating mode");
    }
}

// Concrete Commands for Thermostat
class SetTemperatureCommand implements Command {
    private Thermostat thermostat;
    private int temperature;

    public SetTemperatureCommand(Thermostat thermostat, int temperature) {
        this.thermostat = thermostat;
        this.temperature = temperature;
    }

    @Override
    public void execute() {
        thermostat.setTemperature(temperature);
    }
}

class SwitchToCoolingModeCommand implements Command {
    private Thermostat thermostat;

    public SwitchToCoolingModeCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.switchToCoolingMode();
    }
}

class SwitchToHeatingModeCommand implements Command {
    private Thermostat thermostat;

    public SwitchToHeatingModeCommand(Thermostat thermostat) {
        this.thermostat = thermostat;
    }

    @Override
    public void execute() {
        thermostat.switchToHeatingMode();
    }
}

// Smart Thermostat Controller Class (Invoker)
class SmartThermostatController {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Main Class to Run Both Systems
public class SmartHomeAutomationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Smart Lighting System
        Light livingRoomLight = new Light();
        RemoteControl lightRemote = new RemoteControl();

        // Smart Thermostat Control
        Thermostat thermostat = new Thermostat();
        SmartThermostatController thermostatController = new SmartThermostatController();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Turn on the light");
            System.out.println("2. Turn off the light");
            System.out.println("3. Dim the light");
            System.out.println("4. Set thermostat temperature");
            System.out.println("5. Switch to cooling mode");
            System.out.println("6. Switch to heating mode");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    lightRemote.setCommand(new TurnOnLightCommand(livingRoomLight));
                    lightRemote.pressButton();
                    break;
                case 2:
                    lightRemote.setCommand(new TurnOffLightCommand(livingRoomLight));
                    lightRemote.pressButton();
                    break;
                case 3:
                    System.out.print("Enter dim level (0-100): ");
                    int dimLevel = scanner.nextInt();
                    lightRemote.setCommand(new DimLightCommand(livingRoomLight, dimLevel));
                    lightRemote.pressButton();
                    break;
                case 4:
                    System.out.print("Enter temperature: ");
                    int temperature = scanner.nextInt();
                    thermostatController.setCommand(new SetTemperatureCommand(thermostat, temperature));
                    thermostatController.pressButton();
                    break;
                case 5:
                    thermostatController.setCommand(new SwitchToCoolingModeCommand(thermostat));
                    thermostatController.pressButton();
                    break;
                case 6:
                    thermostatController.setCommand(new SwitchToHeatingModeCommand(thermostat));
                    thermostatController.pressButton();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
