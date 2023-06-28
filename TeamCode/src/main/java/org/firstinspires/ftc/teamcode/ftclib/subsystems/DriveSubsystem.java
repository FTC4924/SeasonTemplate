package org.firstinspires.ftc.teamcode.ftclib.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.HDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class DriveSubsystem extends SubsystemBase { // TODO: 12/13/2022 Rewrite to use RoadRunner.

    private final MotorEx frontLeft, frontRight, backLeft, backRight;

    private final HDrive xDrive;

    private final RevIMU imu;
    private Orientation angles;
    private double angleOffset;

    public DriveSubsystem(HardwareMap hardwareMap, MotorEx frontLeft, MotorEx frontRight, MotorEx backLeft, MotorEx backRight) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        this.xDrive = new HDrive(backRight, backLeft, frontRight, frontLeft);

        imu = new RevIMU(hardwareMap);
        imu.init();
        angles = null;
    }

    public DriveSubsystem(HardwareMap hMap, String frontLeft, String frontRight, String backLeft, String backRight) {
        this(
                hMap,
                new MotorEx(hMap, frontLeft),
                new MotorEx(hMap, frontRight),
                new MotorEx(hMap, backLeft),
                new MotorEx(hMap, backRight)
        );
    }

    public void drive(double strafeSpeed, double forwardSpeed, double turn) {
        double heading = imu.getRotation2d().getDegrees() - angleOffset;
        xDrive.driveFieldCentric(strafeSpeed, forwardSpeed, turn, heading);
    }

    public void resetGyro() {
        angleOffset = imu.getRotation2d().getDegrees();
    }

    public void stop() { xDrive.stop(); }
}
