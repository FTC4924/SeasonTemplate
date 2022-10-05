package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;

import static org.firstinspires.ftc.teamcode.Constants.ENCODER_POSITION_TOLERANCE;
import static org.firstinspires.ftc.teamcode.Constants.TICKS_PER_FOOT;

/**
 * Command for moving the robot using the holonomic drive.
 */
public class Move extends Command { // TODO: 3/24/22 Needs verification that this works.
    Drive drive;

    boolean useCurrentAngle;

    double distance, angle, power;
    double mainDiagonalPercent, antiDiagonalPercent;

    public Move(double distance, double angle, @FloatRange(from=0.0, to=1.0) double power) {
        this.distance = distance;
        this.angle = Math.toRadians(angle);
        this.power = power;
    }

    public Move(double distance, @FloatRange(from = 0.0, to = 1.0) double power) {
        this.distance = distance;
        this.power = power;
        useCurrentAngle = true;
    }

    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);

        drive = resourceManager.removeSubsystem(Drive.class, "drive");
        if (drive == null) return false;

        double targetPosition = distance * TICKS_PER_FOOT;

        /*Subtracts the robot's current angle from the command angle so that it travels globally
        rather than relative to the robot, then rotates it 45 degrees so that the components align
        with the wheels*/
        double holonomicAngle = angle * allianceColor.direction - drive.getHeading() + (Math.PI / 4);

        //the main diagonal is the diagonal from top left to bottom right
        mainDiagonalPercent = Math.cos(holonomicAngle);

        //the anti-diagonal is the diagonal from topRight to bottomLeft
        antiDiagonalPercent = Math.sin(holonomicAngle);

        double mainDiagonalTargetPosition = targetPosition * mainDiagonalPercent;
        double antiDiagonalTargetPosition = targetPosition * antiDiagonalPercent;

        drive.setBaseTargetPositions(
                -mainDiagonalTargetPosition,
                antiDiagonalTargetPosition,
                -antiDiagonalTargetPosition,
                mainDiagonalTargetPosition
        );

        drive.setBasePowers(
                -mainDiagonalPercent * power,
                antiDiagonalPercent * power,
                -antiDiagonalPercent * power,
                mainDiagonalPercent * power
        );

        return true;
    }

    public boolean update() {
        double[] targetPositions = drive.getTargetPositions();
        double[] motorPositions = drive.getMotorPositions();
        boolean isFinished = false;
        for(int i = 0; i < 4; i++) {
            if(Math.abs(targetPositions[i] - motorPositions[i]) <= ENCODER_POSITION_TOLERANCE) {
                isFinished = true;
                break;
            }
        }
        return !isFinished;
    }

    public void stop(@NonNull ResourceManager resourceManager) {
        drive.setBasePowers(0, 0, 0, 0);
        resourceManager.addSubsystems(drive);
    }
}
