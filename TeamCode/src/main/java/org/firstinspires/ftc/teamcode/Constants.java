package org.firstinspires.ftc.teamcode;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;

import androidx.annotation.FloatRange;

/**
 * Contains constants for all of the programs in one file for easy access.
 */

public class Constants {
    /**
     * Number of motor encoder ticks per foot.
     */
    public static final double TICKS_PER_FOOT = 0;

    /**
     * Defines the dead zone for controller input.
     */
    @FloatRange(from=0.0, to=1.0)
    protected static final double CONTROLLER_TOLERANCE = 0.05;
    /**
     * Defines the tolerance for the angle error.
     */
    @FloatRange(from=0.0, to=1.0)
    public static final double HEADING_ERROR_TOLERANCE = 0.05;
    /**
     * How close the encoder needs to get to the target position for autonomous to move to the next command.
     */
    public static final double ENCODER_POSITION_TOLERANCE = 100.0;

    public static final double TURNING_ENCODER_POSITION_SCALAR = 20.0;
    public static final double TURNING_POWER_SCALAR = 1;

    /*
    All the constants below are part of image processing.
     */
    public static final Scalar GREEN = new Scalar(0, 255, 0);
    public static final Rect ROI = new Rect(new Point(0,665), new Point(960,840));
    public static final int RESOLUTION_WIDTH = 1280;
    public static final int RESOLUTION_HEIGHT = 960;
    public static final String WEBCAM_RECORDING_FILE = "/Movies/match_recording.mp4";

}
