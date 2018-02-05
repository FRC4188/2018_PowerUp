package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	WPI_TalonSRX innerElevator = RobotMap.innerElevator;
	WPI_TalonSRX outerElevator = RobotMap.outerElevator;
	XboxController coPilotXboxController = Robot.m_oi.coPilotXboxController;
    // Initialize your subsystem here
    public Elevator() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("Elevator", 0.0,0.0,0.0);
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void ElevatorUp() {
    	innerElevator.set(coPilotXboxController.getY(Hand.kLeft));
    	outerElevator.set(coPilotXboxController.getY(Hand.kLeft));
    }
    public void ElevatorStop() {
    	innerElevator.set(0);
    	outerElevator.set(0);
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Math.abs(innerElevator.getSelectedSensorPosition(0));
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	innerElevator.set(output);
    	outerElevator.set(-output);
    	
    }
    
}
