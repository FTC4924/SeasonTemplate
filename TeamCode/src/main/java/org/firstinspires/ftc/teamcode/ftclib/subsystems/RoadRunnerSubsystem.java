package org.firstinspires.ftc.teamcode.ftclib.subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.drive.PoseStorage;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDriveCancelable;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequenceBuilder;

public class RoadRunnerSubsystem extends SubsystemBase {
    private final SampleMecanumDriveCancelable drive;

    public RoadRunnerSubsystem(HardwareMap hardwareMap) {
        drive = new SampleMecanumDriveCancelable(hardwareMap);
    }

    public void setPoseEstimate(Pose2d pose2d) {
        drive.setPoseEstimate(pose2d);
    }

    @Override
    public void periodic() {
        drive.update();
        PoseStorage.pose2d = drive.getPoseEstimate();
    }

    public void breakFollowing() {
        drive.breakFollowing();
    }

    public boolean isBusy() {
        return drive.isBusy();
    }

    public void followTrajectorySequence(TrajectorySequence trajectory) {
        drive.followTrajectorySequenceAsync(trajectory);
    }

    public void turn(double angle) {
        drive.turnAsync(angle);
    }

    public TrajectorySequenceBuilder trajectorySequenceBuilder(Pose2d startPos) {
        return drive.trajectorySequenceBuilder(startPos);
    }

    public void setMode(DcMotor.RunMode mode) {
        drive.setMode(mode);
    }
}
