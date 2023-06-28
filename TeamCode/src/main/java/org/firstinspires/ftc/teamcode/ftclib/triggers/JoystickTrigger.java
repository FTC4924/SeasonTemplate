package org.firstinspires.ftc.teamcode.ftclib.triggers;

import com.arcrobotics.ftclib.command.button.Trigger;

import static org.firstinspires.ftc.teamcode.RobotConstants.CONTROLLER_TOLERANCE;

import java.util.function.DoubleSupplier;

public class JoystickTrigger extends Trigger {
    public final AxisTrigger x;
    public final AxisTrigger y;

    public JoystickTrigger(DoubleSupplier x, DoubleSupplier y) {
        this.x = new AxisTrigger(x, CONTROLLER_TOLERANCE);
        this.y = new AxisTrigger(y, CONTROLLER_TOLERANCE);
    }
    
    @Override
    public boolean get() {
        return x.get() || y.get();
    }
}
