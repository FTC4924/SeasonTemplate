package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;

import androidx.annotation.NonNull;

/**
 * Command for running a different set of commands depending on which alliance we are on.
 */
public class AllianceSplit extends Command { // FIXME: 3/24/22 Needs to be implemented.
    private final Command blueCommand, redCommand;
    private Command command;

    public AllianceSplit(Command blueCommand, Command redCommand) {
        this.blueCommand = blueCommand;
        this.redCommand = redCommand;
    }

    @Override
    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);

        switch (allianceColor) {
            case BLUE:
                command = blueCommand;
                break;
            case RED:
                command = redCommand;
                break;
            default:
                return false;
        }

        return command.start(resourceManager);
    }

    @Override
    public boolean update() {
        return command.update();
    }

    @Override
    public void stop(@NonNull ResourceManager resourceManager) {
        command.stop(resourceManager);
    }
}
