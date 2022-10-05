package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AllianceColor;
import org.firstinspires.ftc.teamcode.ResourceManager;

import androidx.annotation.NonNull;

/*
 * Created by Brendan Clark on 09/24/2020 at 11:54 AM.
 */

/**
 * Base class for all commands.
 * The methods in this class are executed as follows:
 * <ul>
 *     <li>{@link Command#start(ResourceManager)} -- Executed once before {@linkplain Command#update()} on the first loop of the command.</li>
 *     <li>{@link Command#update()} -- Executed once each loop after {@linkplain Command#start(ResourceManager)} has successfully run once.</li>
 *     <li>{@link Command#stop(ResourceManager)} -- Executed once after {@linkplain Command#update()} has returned false,
 *              but before the next command is loaded.</li>
 * </ul>
 * See each method for more detail.
 */
public abstract class Command {

    protected Telemetry telemetry;
    protected AllianceColor allianceColor;

    /**
     * Loads the telemetry, the alliance color, and AllianceValuesBase objects from the {@link ResourceManager}
     *
     * @param resourceManager The {@link ResourceManager} object to load from.
     */
    public void init(@NonNull ResourceManager resourceManager) {
        telemetry = resourceManager.telemetry;
        allianceColor = resourceManager.allianceColor;
    }

    /**
     * Executed once before {@link Command#update()} on the first loop of the command.
     *
     * @param resourceManager The {@link ResourceManager} object to use to access setup resources.
     * @return successfully finished.
     */
    public abstract boolean start(@NonNull ResourceManager resourceManager);

    /**
     * Executed once each loop after {@link Command#start(ResourceManager)} has successfully run once.
     */
    public boolean update() {
        return false;
    }

    /**
     * Executed once after {@link Command#update()} has returned true, but before the next command is loaded.
     */
    public void stop(@NonNull ResourceManager resourceManager) {

    }
}
