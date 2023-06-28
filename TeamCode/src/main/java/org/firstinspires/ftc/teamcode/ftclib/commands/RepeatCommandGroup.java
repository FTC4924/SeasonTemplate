package org.firstinspires.ftc.teamcode.ftclib.commands;


import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

public class RepeatCommandGroup extends SequentialCommandGroup {

    private int count;
    private final int repeat;

    /**
     * Creates a new SequentialCommandGroup.  The given commands will be run sequentially, with
     * the CommandGroup finishing when the last command finishes.
     *
     * @param commands the commands to include in this group.
     */
    public RepeatCommandGroup(int repeat, Command... commands) {
        super(commands);
        this.repeat = repeat;
    }

    @Override
    public boolean isFinished() {
        if (super.isFinished()) {
            count++;
            if (count < repeat) initialize();
        }
        return count >= repeat;
    }

}
