package org.firstinspires.ftc.teamcode.ftclib.triggers;

import com.arcrobotics.ftclib.command.button.Trigger;

import java.util.function.DoubleSupplier;

public class AxisTrigger extends Trigger {
    private final DoubleSupplier value;
    private final double threshold;

    public AxisTrigger(DoubleSupplier value, double threshold) {
        this.value = value;
        this.threshold = threshold;
    }

    @Override
    public boolean get() {
        return Math.abs(value.getAsDouble()) >= threshold;
    }
}
