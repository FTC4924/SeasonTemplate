package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

import androidx.annotation.NonNull;

/*
 * Created by Brendan Clark on 02/27/2022 at 3:50 PM.
 */

/**
 * Command for executing a list of commands in order, one after another.
 */
public class Sequential extends Command {
    private final Queue<Command> commands;
    private ResourceManager resourceManager;
    private Command activeCommand;

    public Sequential(Command... commands) {
        this.commands = new ArrayDeque<>(Arrays.asList(commands));
    }

    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);
        this.resourceManager = resourceManager;
        return nextCommand();
    }

    public boolean update() {
        if (!activeCommand.update()) {
            activeCommand.stop(resourceManager);
            return nextCommand();
        } else { return true; }
    }

    private boolean nextCommand() {
        if ((activeCommand = commands.poll()) != null) {
            if (activeCommand.start(resourceManager)) return true;
            else return nextCommand();
        } else { return false; }
    }
}
