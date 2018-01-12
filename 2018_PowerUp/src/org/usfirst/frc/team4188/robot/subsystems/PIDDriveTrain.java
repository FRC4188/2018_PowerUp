package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.CSPRobotDrive;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PIDDriveTrain extends PIDSubsystem {

	WPI_TalonSRX frontLeft = RobotMap.frontLeft;
	WPI_TalonSRX midLeft = RobotMap.midLeft;
	WPI_TalonSRX rearLeft = RobotMap.rearLeft;
	WPI_TalonSRX frontRight = RobotMap.frontRight;
	WPI_TalonSRX midRight = RobotMap.midRight;
	WPI_TalonSRX rearRight = RobotMap.rearRight;
	
	SpeedControllerGroup rightSide = RobotMap.rightSet;
	SpeedControllerGroup leftSide = RobotMap.leftSet;
	
	AnalogGyro gyro = RobotMap.gyro;
	
	CSPRobotDrive driveTrain = RobotMap.driveTrain;
    // Initialize your subsystem here
    public PIDDriveTrain(double kP, double kI, double kD) {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	super("DriveTrain Encoders", kP, kI, kD);
    	setAbsoluteTolerance(1.0);
    }
    

    
    public void setRampRate(double rampRate) {
    	frontLeft.configClosedloopRamp(rampRate, 0);
    	midLeft.configClosedloopRamp(rampRate, 0);
    	rearLeft.configClosedloopRamp(rampRate, 0);
    	frontRight.configClosedloopRamp(rampRate, 0);
    	midRight.configClosedloopRamp(rampRate, 0);
    	rearRight.configClosedloopRamp(rampRate, 0);
    }
    
    public void runPIDLoop() {
    	getPIDController().enable();
    	if(getPIDController().onTarget()) {
    		getPIDController().disable();
    		getPIDController().free();
    	}
    }
    
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void resetEncoders(){
		rearRight.setSelectedSensorPosition(0, 0, 0);
		rearLeft.setSelectedSensorPosition(0, 0, 0);
    }
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return rearRight.getSelectedSensorPosition(0);
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	rearRight.pidWrite(output);
    	frontLeft.follow(rearRight);
    	frontRight.follow(rearRight);
    	rearLeft.follow(rearRight);
    	midLeft.follow(rearRight);
    	midRight.follow(rearRight);
    }
    
    public void arcadeDrive(double x, double twist, double throttle){
    	driveTrain.arcadeDrive(x*throttle, twist*throttle);
    }
    
    public void autoDrive(double moveValue, double rotateValue) {
		// TODO Auto-generated method stub
		driveTrain.arcadeDrive(moveValue, rotateValue);
	}
    
    
    
    public void gyroReset(){
		gyro.reset();
	}
}


