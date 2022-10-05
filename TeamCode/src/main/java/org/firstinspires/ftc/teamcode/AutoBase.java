package org.firstinspires.ftc.teamcode;

import android.os.Build;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Commands.Command;
import org.firstinspires.ftc.teamcode.Subsystems.Camera;
import org.firstinspires.ftc.teamcode.Subsystems.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.LedMatrix;

import androidx.annotation.RequiresApi;


/**
 * The base that all of the autonomous programs run off of.
 */
public abstract class AutoBase extends OpMode {

    private Command command;

    private ResourceManager resourceManager;

    /**
     * Sets up the state machine by determining the current alliance color, registering all devices
     * and subsystems with the {@link ResourceManager}, and fetching the command.
     */
    @Override
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void init() {

        DcMotor armRotator = hardwareMap.get(DcMotor.class, "armRotator");
        armRotator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRotator.setTargetPosition(0);
        armRotator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRotator.setPower(1);

        DcMotor armExtender = hardwareMap.get(DcMotor.class, "armExtender");
        armExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armExtender.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armExtender.setTargetPosition(0);
        armExtender.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armExtender.setPower(1);

        DigitalChannel armTouch = hardwareMap.get(DigitalChannel.class, "armTouch");
        armTouch.setMode(DigitalChannel.Mode.INPUT);

        DigitalChannel duckWheelTouch = hardwareMap.get(DigitalChannel.class, "duckWheelTouch");
        duckWheelTouch.setMode(DigitalChannel.Mode.INPUT);

        resourceManager = new ResourceManager(telemetry, getAllianceColor(), hardwareMap);

        resourceManager.addSubsystems(
                new Camera(resourceManager, "Webcam 1", hardwareMap),
                new Drive(resourceManager, "drive", hardwareMap),
                new LedMatrix(resourceManager, "display")
        );

        command = getCommand();
    }

    @Override
    public void start() { command.start(resourceManager); }

    @Override
    public void loop() {

        if (!command.update()) {
            command.stop(resourceManager);
            requestOpModeStop();
        }

        // Runs the loop method of all subsystems that have overridden it.
        resourceManager.loopSubsystems();
        telemetry.addData("active", resourceManager.getActive());

    }

    @Override
    public void stop() {
        // Runs the stop method of all subsystems that have overridden it.
        resourceManager.stopSubsystems();
    }

    /**
     * @return The alliance color this autonomous program is configured for.
     */
    protected abstract AllianceColor getAllianceColor();

    /**
     * @return The commands to execute.
     */
    protected abstract Command getCommand();

}