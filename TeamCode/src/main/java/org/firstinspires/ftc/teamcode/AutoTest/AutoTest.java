package org.firstinspires.ftc.teamcode.AutoTest;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.AllianceColor;
import org.firstinspires.ftc.teamcode.AutoBase;
import org.firstinspires.ftc.teamcode.Commands.Command;
import org.firstinspires.ftc.teamcode.Commands.Move;
import org.firstinspires.ftc.teamcode.Commands.Sequential;

@Autonomous(name="AutoTest")
public class AutoTest extends AutoBase {
    protected AllianceColor getAllianceColor()  {
        return AllianceColor.BLUE;
    }

    protected Command getCommand() {
        return  new Sequential(
                new Move(2, 0, .5)
        );
    }
}
