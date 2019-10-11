package co.za.josiahthobejane.pypislack.controller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Directions 
{

    //create gpio controller
    final static GpioController gpioController = GpioFactory.getInstance();

    final static GpioPinDigitalOutput motorAForwardPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_27);
    final static GpioPinDigitalOutput motorABackwardPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_26);
    final static GpioPinDigitalOutput motorBForwardPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_23);
    final static GpioPinDigitalOutput motorBBackwardPin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_24);
    
    public static void moveLeft() {
        motorABackwardPin.setState(PinState.HIGH);
        motorBBackwardPin.setState(PinState.LOW);
        //
        motorAForwardPin.setState(PinState.HIGH);
        motorBForwardPin.setState(PinState.LOW);
    }

    public static void moveRight() {
        motorABackwardPin.setState(PinState.LOW);
        motorBBackwardPin.setState(PinState.HIGH);
        //
        motorAForwardPin.setState(PinState.LOW);
        motorBForwardPin.setState(PinState.HIGH);
    }

    public static void moveForward() {
        motorABackwardPin.setState(PinState.LOW);
        motorBBackwardPin.setState(PinState.LOW);
        //
        motorAForwardPin.setState(PinState.HIGH);
        motorBForwardPin.setState(PinState.HIGH);
    }

    public static void moveBackward() {
        motorAForwardPin.setState(PinState.LOW);
        motorBForwardPin.setState(PinState.LOW);
        //
        motorABackwardPin.setState(PinState.HIGH);
        motorBBackwardPin.setState(PinState.HIGH);
    }

    public static void brake() {
        motorABackwardPin.setState(PinState.LOW);
        motorBBackwardPin.setState(PinState.LOW);
        //
        motorAForwardPin.setState(PinState.LOW);
        motorBForwardPin.setState(PinState.LOW);
    }
}