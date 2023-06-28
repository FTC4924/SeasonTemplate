package org.firstinspires.ftc.teamcode.ftclib.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.util.Timing.Timer;

import org.firstinspires.ftc.teamcode.ftclib.subsystems.DriveSubsystem;

import java.util.concurrent.TimeUnit;

public class Drive extends CommandBase {

    private final DriveSubsystem drive;
    private final double forward;
    private final double rotation;
    private final double strafe;
    private final Timer timer;

    /**
     * Creates a new DriveCommand.
     *  @param drive The drive subsystem this command will run on.
     * @param forward The control input for driving forwards/backwards
     * @param strafe The control input for driving sideways.
     * @param rotation The control input for turning
     * @param duration The duration to move in seconds.
     */
    public Drive(DriveSubsystem drive, double forward,
                 double strafe, double rotation, double duration) {
        this.drive = drive;
        this.forward = forward;
        this.rotation = rotation;
        this.strafe = strafe;
        timer = new Timer((int) (duration * 1000000), TimeUnit.MICROSECONDS);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.drive(
                forward,
                rotation,
                strafe
        );
        timer.start();
    }

    @Override
    public boolean isFinished() {
        return timer.done();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}
