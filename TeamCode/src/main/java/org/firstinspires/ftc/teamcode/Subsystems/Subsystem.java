package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AllianceColor;
import org.firstinspires.ftc.teamcode.Commands.Command;
import org.firstinspires.ftc.teamcode.ResourceManager;

import androidx.annotation.NonNull;

/*
 * Created by Brendan Clark on 02/27/2022 at 10:30 AM.
 */

/**
 * Base class for all subsystems of the robot.
 * Each subsystem is used to control a part of the robot, like the drive system or the arm system,
 * and can make it easier to make more complex movements in your {@link Command Commands}. Must be
 * annotated with {@link Subsystem} to be found by the {@link ResourceManager} and be made available to any
 * {@link Command Commands}.
 *
 * @see Command
 * @see Subsystem
 */
public abstract class Subsystem {
    @NonNull
    protected Telemetry telemetry;
    @NonNull
    protected AllianceColor allianceColor;
    protected String name;

    protected Subsystem(@NonNull ResourceManager resourceManager, String name) {
        this.telemetry = resourceManager.telemetry;
        allianceColor = resourceManager.allianceColor;
    }

    /**
     * Adds functionality to gracefully shutdown the subsystem when the OpMode is stopped.
     */
    public void stop() { }

    /**
     * Contains code for the subsystem to run each loop. An example usage would be a drive train
     * subsystem computing gyro correction each loop.
     */
    public void loop() { }

    public String getName() {
        return name;
    }
}