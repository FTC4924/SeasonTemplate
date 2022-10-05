package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

import androidx.annotation.NonNull;

import static org.firstinspires.ftc.teamcode.Constants.HEADING_ERROR_TOLERANCE;

/**
 * Command that changes the robot's auto correction system's target angle.
 */
public class Turn extends Command { // TODO: 3/24/22 Needs verification that this works correctly.
    double heading;

    Drive drive;

    public Turn(double heading) {
        this.heading = Math.toRadians(heading);
    }

    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);

        drive = resourceManager.removeSubsystem(Drive.class, "drive");
        if (drive == null) return false;

        drive.setTargetHeading(heading * allianceColor.direction);
        return true;
    }

    public boolean update() {
        return Math.abs(drive.getHeadingError()) > HEADING_ERROR_TOLERANCE;
    }

    public void stop(@NonNull ResourceManager resourceManager) {
        resourceManager.addSubsystems(drive);
    }
}
