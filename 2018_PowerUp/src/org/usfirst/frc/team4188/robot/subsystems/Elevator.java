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
	
	WPI_TalonSRX outerElevatorRight = RobotMap.outerElevatorRight;
	WPI_TalonSRX outerElevatorLeft = RobotMap.outerElevatorLeft;
	WPI_TalonSRX innerElevator = RobotMap.innerElevator;
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
    
    public void outerElevatorRun() {
    	outerElevatorLeft.set(coPilotXboxController.getY(Hand.kLeft)*0.75);
    	outerElevatorRight.set(-coPilotXboxController.getY(Hand.kLeft)*0.75);
    }
    
    public void outerElevatorStop() {
    	outerElevatorLeft.set(0);
    	outerElevatorRight.set(0);
    }
    
    public void innerElevatorRun() {
    	if(coPilotXboxController.getY(Hand.kRight) < 0) {
    		innerElevator.set(coPilotXboxController.getY(Hand.kRight)*-.75);
    	} else {
    		innerElevator.set(coPilotXboxController.getY(Hand.kRight)*-.25);
    	}
    }
    
    public void innerElevatorStop() {
    	innerElevator.set(0);
    }
    
    public void bothElevatorsRun(double power) {
    	innerElevator.set(power);
    	outerElevatorLeft.set(-power);
    	outerElevatorRight.set(power);
    }
    
    public void bothElevatorsStop() {
    	innerElevator.set(0);
    	outerElevatorLeft.set(0);
    	outerElevatorRight.set(0);
    }
    
    public void setPID(double p, double i, double d) {
    	getPIDController().setP(p);
    	getPIDController().setI(i);
    	getPIDController().setD(d);
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Math.abs(outerElevatorLeft.getSelectedSensorPosition(0));
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	outerElevatorLeft.set(output);
    	outerElevatorRight.set(-output);
    }
    
}
