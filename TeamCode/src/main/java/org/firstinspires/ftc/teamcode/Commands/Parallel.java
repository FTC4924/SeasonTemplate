package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import androidx.annotation.NonNull;

/*
 * Created by Brendan Clark on 02/27/2022 at 3:50 PM.
 */

/**
 * Command for running multiple commands in parallel.
 */
public class Parallel extends Command {
    final private ArrayList<Command> commands;
    private ResourceManager resourceManager;

    public Parallel(Command... commands) {
        this.commands = new ArrayList<>(Arrays.asList(commands));
    }

    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);
        this.resourceManager = resourceManager;
        Iterator<Command> i = commands.iterator();
        while (i.hasNext()) {
            Command command = i.next();
            if (!command.start(resourceManager)) {
                i.remove();
            }
        }
        return !commands.isEmpty();
    }

    public boolean update() {
        Iterator<Command> i = commands.iterator();
        while (i.hasNext()) {
            Command command = i.next();
            if (!command.update()) {
                command.stop(resourceManager);
                i.remove();
            }
        }
        return !commands.isEmpty();
    }
}
