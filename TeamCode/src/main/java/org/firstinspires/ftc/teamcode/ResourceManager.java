package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Subsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class ResourceManager {

    public Telemetry telemetry;
    public AllianceColor allianceColor;

    private final HashMap<String, HardwareDevice> idleDevices;
    private final HashMap<String, HardwareDevice> activeDevices;
    private final HashMap<String, Subsystem> idleSubsystems;
    private final HashMap<String, Subsystem> activeSubsystems;

    public ResourceManager(Telemetry telemetry, AllianceColor allianceColor, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.allianceColor = allianceColor;

        idleDevices = new HashMap<>();
        //Adds all HardwareDevices in HardwareMap to allDevices
        for (HardwareDevice device : hardwareMap) {
            idleDevices.put(device.getDeviceName(), device);
        }

        activeDevices = new HashMap<>();
        idleSubsystems = new HashMap<>();
        activeSubsystems = new HashMap<>();
    }

    /**
     * Gets a subsystem if it is idle, and marks it as active.
     *
     * @param classType The class of the subsystem.
     * @return The subsystem of type classType.
     */
    @Nullable
    public <T extends Subsystem> T removeSubsystem(@NonNull Class<T> classType, String name) {
        name = name.trim();
        Subsystem subsystem = idleSubsystems.remove(name);
        activeSubsystems.put(name, subsystem);
        return classType.cast(subsystem);
    }

    /**
     * Adds subsystems to the idle subsystems in the manager.
     *
     * @param subsystems The subsystem(s) to add to the manager.
     */
    public void addSubsystems(@NonNull Subsystem... subsystems) {
        for(Subsystem subsystem : subsystems) {
            idleSubsystems.put(subsystem.getName(), subsystem);
        }
    }

    /**
     * Gets a device if it is idle, and marks it as active.
     *
     * @param classType The class of the device.
     * @param name  The name of the device.
     * @return The the device register under deviceName of type deviceClass.
     */
    public <T extends HardwareDevice> T removeDevice(@NonNull Class<T> classType, String name) {
        name = name.trim();
        HardwareDevice device = idleDevices.remove(name);
        activeDevices.put(name, device);
        return classType.cast(device);
    }

    /**
     * Returns a device to the manager and marks it as idle.
     *
     * @param devices The device(s) to return to the manager.
     */
    public void addDevices(@NonNull HardwareDevice... devices) {
        for(HardwareDevice device : devices) {
            idleDevices.put(device.getDeviceName(), device);
        }
    }

    /**
     * Runs the loop method of all known subsystems
     */
    public void loopSubsystems() {
        for (Subsystem subsystem : idleSubsystems.values()) {
            subsystem.loop();
        }
        for (Subsystem subsystem : activeSubsystems.values()) {
            subsystem.loop();
        }
    }

    /**
     * Runs the stop method of all known subsystems
     */
    public void stopSubsystems() {
        for (Subsystem subsystem : idleSubsystems.values()) {
            subsystem.stop();
        }
        for (Subsystem subsystem : activeSubsystems.values()) {
            subsystem.stop();
        }
    }

    /**
     * gets the names for all active devices and subsystems
     * @return an ArrayList of names for all active devices and subsystems
     */
    public List<String> getActive() {
        List<String> names = new ArrayList<>();
        for(HardwareDevice device : activeDevices.values()) {
            names.add(device.getDeviceName());
        }
        for(Subsystem subsystem : activeSubsystems.values()) {
            names.add(subsystem.getName());
        }
        return names;
    }
}
