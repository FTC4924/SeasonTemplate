package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.teamcode.ftclib.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.ftclib.subsystems.RoadRunnerSubsystem;

public abstract class AutoBase extends CommandOpMode {
    protected DriveSubsystem drive;
    protected RoadRunnerSubsystem roadRunner;
    // TODO: 6/27/2023 Declare subsystems here

    @Override
    public void initialize() {
        drive = new DriveSubsystem(
                hardwareMap,
                "leftFront",
                "rightFront",
                "leftBack",
                "rightBack"
        );

        roadRunner = new RoadRunnerSubsystem(hardwareMap);

        // TODO: 6/27/2023 Construct subsystems here

        schedule(new InstantCommand().andThen(getCommands()));  // Schedules commmands with the command scheduler.

        register(drive, roadRunner);  // TODO: 6/27/2023 Register subsystems with the command scheduler here
    }
    
    public void setRoadRunnerStart(Pose2d pose2d) {
        roadRunner.setPoseEstimate(pose2d);
    }

    protected abstract Command getCommands();
}
