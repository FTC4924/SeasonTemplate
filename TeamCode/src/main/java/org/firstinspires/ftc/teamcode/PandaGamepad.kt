//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.arcrobotics.ftclib.gamepad

import com.arcrobotics.ftclib.command.button.GamepadButton
import com.qualcomm.robotcore.hardware.Gamepad

class PandaGamepad(var gamepad: Gamepad) {
    private val buttonReaders: HashMap<GamepadKeys.Button?, ButtonReader?>
    private val gamepadButtons: HashMap<GamepadKeys.Button?, GamepadButton?>
    private val buttons: Array<GamepadKeys.Button>

    init {
        buttons = arrayOf(GamepadKeys.Button.Y, GamepadKeys.Button.X, GamepadKeys.Button.A, GamepadKeys.Button.B, GamepadKeys.Button.LEFT_BUMPER, GamepadKeys.Button.RIGHT_BUMPER, GamepadKeys.Button.BACK, GamepadKeys.Button.START, GamepadKeys.Button.DPAD_UP, GamepadKeys.Button.DPAD_DOWN, GamepadKeys.Button.DPAD_LEFT, GamepadKeys.Button.DPAD_RIGHT, GamepadKeys.Button.LEFT_STICK_BUTTON, GamepadKeys.Button.RIGHT_STICK_BUTTON)
        buttonReaders = HashMap<Any?, Any?>()
        gamepadButtons = HashMap<Any?, Any?>()
        val var2 = buttons
        val var3 = var2.size
        for (var4 in 0 until var3) {
            val button = var2[var4]
            buttonReaders[button] = ButtonReader(this, button)
            gamepadButtons[button] = GamepadButton(this, *arrayOf(button))
        }
    }

    fun getButton(button: GamepadKeys.Button?): Boolean {
        var buttonValue = false
        buttonValue = when (button) {
            GamepadKeys.Button.A -> gamepad.a
            GamepadKeys.Button.B -> gamepad.b
            GamepadKeys.Button.X -> gamepad.x
            GamepadKeys.Button.Y -> gamepad.y
            GamepadKeys.Button.LEFT_BUMPER -> gamepad.left_bumper
            GamepadKeys.Button.RIGHT_BUMPER -> gamepad.right_bumper
            GamepadKeys.Button.DPAD_UP -> gamepad.dpad_up
            GamepadKeys.Button.DPAD_DOWN -> gamepad.dpad_down
            GamepadKeys.Button.DPAD_LEFT -> gamepad.dpad_left
            GamepadKeys.Button.DPAD_RIGHT -> gamepad.dpad_right
            GamepadKeys.Button.BACK -> gamepad.back
            GamepadKeys.Button.START -> gamepad.start
            GamepadKeys.Button.LEFT_STICK_BUTTON -> gamepad.left_stick_button
            GamepadKeys.Button.RIGHT_STICK_BUTTON -> gamepad.right_stick_button
            else -> false
        }
        return buttonValue
    }

    fun getTrigger(trigger: GamepadKeys.Trigger?): Double {
        var triggerValue = 0.0
        when (trigger) {
            GamepadKeys.Trigger.LEFT_TRIGGER -> triggerValue = gamepad.left_trigger.toDouble()
            GamepadKeys.Trigger.RIGHT_TRIGGER -> triggerValue = gamepad.right_trigger.toDouble()
        }
        return triggerValue
    }

    val leftY: Double
        get() = (-gamepad.left_stick_y).toDouble()
    val rightY: Double
        get() = gamepad.right_stick_y.toDouble()
    val leftX: Double
        get() = gamepad.left_stick_x.toDouble()
    val rightX: Double
        get() = gamepad.right_stick_x.toDouble()

    fun wasJustPressed(button: GamepadKeys.Button?): Boolean {
        return buttonReaders[button]!!.wasJustPressed()
    }

    fun wasJustReleased(button: GamepadKeys.Button?): Boolean {
        return buttonReaders[button]!!.wasJustReleased()
    }

    fun readButtons() {
        val var1 = buttons
        val var2 = var1.size
        for (var3 in 0 until var2) {
            val button = var1[var3]
            buttonReaders[button]!!.readValue()
        }
    }

    fun isDown(button: GamepadKeys.Button?): Boolean {
        return buttonReaders[button]!!.isDown
    }

    fun stateJustChanged(button: GamepadKeys.Button?): Boolean {
        return buttonReaders[button]!!.stateJustChanged()
    }

    fun getGamepadButton(button: GamepadKeys.Button?): GamepadButton? {
        return gamepadButtons[button]
    }
}
