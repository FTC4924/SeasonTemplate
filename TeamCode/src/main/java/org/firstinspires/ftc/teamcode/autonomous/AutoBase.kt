package org.firstinspires.ftc.teamcode.autonomous

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.arcrobotics.ftclib.command.Command
import com.arcrobotics.ftclib.command.CommandOpMode
import com.arcrobotics.ftclib.command.InstantCommand
import org.firstinspires.ftc.teamcode.ftclib.subsystems.DriveSubsystem
import org.firstinspires.ftc.teamcode.ftclib.subsystems.RoadRunnerSubsystem

abstract class AutoBase : CommandOpMode() {
    protected lateinit var drive: DriveSubsystem
    protected lateinit var roadRunner: RoadRunnerSubsystem

    // TODO: 6/27/2023 Declare subsystems here
    override fun initialize() {
        drive = DriveSubsystem(
                hardwareMap,
                "leftFront",
                "rightFront",
                "leftBack",
                "rightBack"
        )
        roadRunner = RoadRunnerSubsystem(hardwareMap)

        // TODO: 6/27/2023 Construct subsystems here
        schedule(InstantCommand().andThen(commands)) // Schedules commmands with the command scheduler.
        register(drive, roadRunner) // TODO: 6/27/2023 Register subsystems with the command scheduler here
    }

    fun setRoadRunnerStart(pose2d: Pose2d) {
        roadRunner.setPoseEstimate(pose2d)
    }

    protected abstract val commands: Command?
}
