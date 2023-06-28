package org.firstinspires.ftc.teamcode.ftclib.commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.WaitCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryCommand extends CommandBase {
    private final Telemetry telemetry;
    private final String msg;
    private final WaitCommand waitCommand;

    public TelemetryCommand(Telemetry telemetry, String msg, long duration) {
        this.telemetry = telemetry;
        this.msg = msg;
        waitCommand = new WaitCommand((long) (duration * 1000));
    }

    @Override
    public void initialize() {
        CommandScheduler.getInstance().schedule(waitCommand);
    }

    @Override
    public void execute() {
        telemetry.addLine(msg);
    }

    @Override
    public boolean isFinished() {
        return waitCommand.isFinished();
    }
}
