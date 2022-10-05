package org.firstinspires.ftc.teamcode.Commands;

import org.firstinspires.ftc.teamcode.ResourceManager;

import androidx.annotation.NonNull;

/**
 * Command for adding a delay between the previous command and the next command.
 */
public class Pause extends Command {
    double startTime;
    double duration;

    public Pause(double duration) {
        this.duration = duration;
    }

    public boolean start(@NonNull ResourceManager resourceManager) {
        init(resourceManager);
        startTime = System.nanoTime() / 1e9;
        return true;
    }

    public boolean update() {
        return Math.abs(startTime - System.nanoTime() / 1e9) < duration;
    }
}
