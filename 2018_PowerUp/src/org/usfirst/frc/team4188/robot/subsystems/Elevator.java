package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.elevator.BothElevatorsRun;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Elevator extends PIDSubsystem {
	
	WPI_TalonSRX outerElevatorRight = RobotMap.outerElevatorRight;
	WPI_TalonSRX outerElevatorLeft = RobotMap.outerElevatorLeft;
	WPI_TalonSRX innerElevator = RobotMap.innerElevator;
	
	public final double SENSOR_UNITS = 1.0/4096.0;
	public final double INCHES_PER_ROTATION = 0.8*Math.PI;
	public final double INCHES_PER_UNIT = SENSOR_UNITS * INCHES_PER_ROTATION;
 
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
    	setDefaultCommand(new BothElevatorsRun());
    }
    
    public void outerElevatorRun(double power) {
    	outerElevatorLeft.set(-power);
    	//outerElevatorRight.set(power);
    }
    
    public void outerElevatorStop() {
    	outerElevatorLeft.set(0);
    	outerElevatorRight.set(0);
    }
    
    public void innerElevatorRun(double power) {
    	if(power < 0) {
    		innerElevator.set(power*-.75);
    	} else {
    		innerElevator.set(power*-.25);
    	}
    }
    
    public void innerElevatorStop() {
    	innerElevator.set(0);
    }
    
    public void bothElevatorsRun(double innerPower, double outerPower) {
    	innerElevator.set(innerPower);
    	outerElevatorLeft.set(-outerPower);
    	//outerElevatorRight.set(outerPower);
    }
    
    public void bothElevatorsStop() {
    	innerElevator.set(0);
    	outerElevatorLeft.set(0);
    	//outerElevatorRight.set(0);
    }
    
    public void setPID(double p, double i, double d) {
    	getPIDController().setP(p);
    	getPIDController().setI(i);
    	getPIDController().setD(d);
    }
    
    private static double inputReturn;
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        if(Robot.innerElevatorStatus == Robot.InnerElevatorStatus.GOOD) { 
        	inputReturn = innerElevator.getSelectedSensorPosition(0) * INCHES_PER_UNIT;
        } else if(Robot.innerElevatorStatus == Robot.InnerElevatorStatus.BROKEN) {
        	inputReturn = outerElevatorLeft.getSelectedSensorPosition(0) * INCHES_PER_UNIT;
        } 
        
        return inputReturn;
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
        if(Robot.innerElevatorStatus == Robot.InnerElevatorStatus.GOOD) { 
        	innerElevator.set(-output);
        } else if(Robot.innerElevatorStatus == Robot.InnerElevatorStatus.BROKEN) {
        	outerElevatorLeft.set(output);
        	//outerElevatorRight.set(-output);
        } 
    }
    
    public void resetEncoders() {
    	innerElevator.setSelectedSensorPosition(0, 0, 0);
    	outerElevatorRight.setSelectedSensorPosition(0, 0, 0);
    	outerElevatorLeft.setSelectedSensorPosition(0, 0, 0);
    }
    
}
