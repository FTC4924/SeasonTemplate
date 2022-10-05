package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

import org.checkerframework.common.value.qual.IntVal;

import androidx.annotation.IntRange;

/**
 * An enum representing the color of the robot's current alliance and values that change based on the alliance.
 */
public enum AllianceColor {

    BLUE(1,
            -90,
            .625,
            RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_BLUE
    ),
    RED(0,
            90,
            .45,
            RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED
    );

    /**
     * Used to reflect autonomous programs onto the other side of the field.
     */
    @IntVal({-1, 1})
    public final int direction;
    /**
     * The offset of the starting angle to the field angle.
     */
    public final double angleOffset;
    /**
     * The horizontal distance to the position to detect the barcode position.
     */
    public final double distanceToDucks;
    /**
     * The pattern for the led strip while idling.
     */
    public final RevBlinkinLedDriver.BlinkinPattern pattern;

    /**
     * Construct an alliance.
     *
     * @param direction       See {@linkplain AllianceColor#direction}.
     * @param angleOffset     See {@linkplain AllianceColor#angleOffset}.
     * @param distanceToDucks See {@linkplain AllianceColor#distanceToDucks}.
     * @param pattern         See {@linkplain AllianceColor#pattern}.
     */
    AllianceColor(@IntRange(from = -1, to = 1) int direction,
                  double angleOffset,
                  double distanceToDucks,
                  RevBlinkinLedDriver.BlinkinPattern pattern) {
        this.direction = direction;
        this.angleOffset = Math.toRadians(angleOffset);
        this.distanceToDucks = distanceToDucks;
        this.pattern = pattern;
    }
}
