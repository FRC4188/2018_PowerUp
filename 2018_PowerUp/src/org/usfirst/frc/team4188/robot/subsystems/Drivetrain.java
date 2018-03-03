package org.usfirst.frc.team4188.robot.subsystems;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;
import org.usfirst.frc.team4188.robot.commands.drive.ManualDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends PIDSubsystem {
	
	int polarity;
	
    // Initialize your subsystem here
    public Drivetrain() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system	
        //                  to
        // enable() - Enables the PID controller.
    	super("DriveTrain Encoders", 0.0,0.0,0.0);
    	switch(Robot.m_name) {
    		case SCRAPPY:
    			polarity = -1;
    			break;
    		case BREAKOUT:
    			polarity = 1;
    			break;
    	}
    }
    
	WPI_TalonSRX frontLeft = RobotMap.frontLeft;
	WPI_TalonSRX rearLeft = RobotMap.rearLeft;
	WPI_TalonSRX frontRight = RobotMap.frontRight;
	WPI_TalonSRX rearRight = RobotMap.rearRight;
	SpeedControllerGroup rightSide = RobotMap.rightSet;
	SpeedControllerGroup leftSide = RobotMap.leftSet;
	ADXRS450_Gyro gyro = RobotMap.gyro;
	DoubleSolenoid gearShift = RobotMap.gearShift;
	DifferentialDrive drivetrain = RobotMap.drivetrain;
	
	public final double SENSOR_UNITS = 1.0/4096.0;
	public final double SENSOR_UNITS_PER_ROTATION = (3600.0/SENSOR_UNITS);
	public final double ROTATION_TO_FEET_CONSTANT = 6.0/12.0 * Math.PI;
	public final double UNITS_TO_FEET_CONSTANT = SENSOR_UNITS * ROTATION_TO_FEET_CONSTANT;
	
	public enum PIDType {
    	noType,
		turnToAngle,
    	driveToDistance,
    	driveToDistanceTwoEncoder
    }
	
    private static PIDType driveType;
    
    public static void setPIDType(PIDType type) {    	
    	driveType = type;
    	switch (driveType) {
    		case turnToAngle:
    			SmartDashboard.putString("Setting PIDType =", "turnToAngle");
    			break;
    		case driveToDistance:
    			SmartDashboard.putString("Setting PIDType =", "driveToDistance");
    			break;
    		case driveToDistanceTwoEncoder:
    			SmartDashboard.putString("Setting PIDType =", "driveToDistance");
    			break;
    		case noType:
    			break;
    	}
    }
	
	public enum PIDInput{
		gyro,
		encoderToAngle,
		encoder,
		none
	}
	
	private static PIDInput sensorType;
	
	public void setPIDInput(PIDInput type) {
    	sensorType = type;
    	switch (sensorType) {
    	case gyro:
    		SmartDashboard.putString("Current PID Input", "Gyro");
    		setPID(0.1,0.0,0.15);
    		break;
    	case encoderToAngle:
    		SmartDashboard.putString("Current PID Input", "Rear Left Encoder");
    		setPID(0.1,0.005,0.007);
    		break;
    	case encoder:
    		SmartDashboard.putString("Current PID Input", "Rear Left Encoder");
    		setPID(0.13, 0.0, 0.0);
    		break;
    	case none:
    		SmartDashboard.putString("Current PID Input", "None");
    		setPID(0.0,0.0,0.0);
    		break;
    	}
    }
	
   protected double returnPIDInput() {
	        // Return your input value for the PID loop
	        // e.g. a sensor, like a potentiometer:
	        // yourPot.getAverageVoltage() / kYourMaxVoltage;
	   switch (sensorType) {
	    	case gyro:
	    		return gyro.getAngle();
	    	case encoderToAngle:
	    		return rearLeft.getSelectedSensorPosition(0) * UNITS_TO_FEET_CONSTANT;
	    	case encoder:
	    		return Math.abs(rearLeft.getSelectedSensorPosition(0) * UNITS_TO_FEET_CONSTANT);
	    	case none:
	    		return -1.0;
	    	default:
	    		return -1.0;
	   	}
    }
	
    public void setPID(double p, double i, double d) {
    	getPIDController().setP(p);
    	getPIDController().setI(i);
    	getPIDController().setD(d);
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	switch (sensorType) {
    	case gyro:
    		rearRight.set(-output);
    		rearLeft.set(-output);
    		frontLeft.follow(rearLeft);
    		frontRight.follow(rearRight);
    		break;
    	case encoderToAngle:
    		rearRight.set(output);
        	rearLeft.set(output);
        	frontLeft.follow(rearLeft);
        	frontRight.follow(rearRight);
    	case encoder:
    		rearRight.set(output);
        	rearLeft.set(-output);
        	frontLeft.follow(rearLeft);
        	frontRight.follow(rearRight);
        	break;
    	case none: 
        	break;
    	}
    }
    
    public void arcadeDrive(double x, double twist, double throttle){
    	drivetrain.arcadeDrive(x*throttle, -twist*throttle);
    }
    
    public void autoDrive(double moveValue, double rotateValue) {
		// TODO Auto-generated method stub
		drivetrain.arcadeDrive(moveValue, rotateValue);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ManualDrive());
    }
    
    public void resetEncoders(){
		rearRight.setSelectedSensorPosition(0, 0, 0);
		rearLeft.setSelectedSensorPosition(0, 0, 0);
    }
    
    public double getRightEncoderRotation() {
    	double rotations = rearRight.getSelectedSensorPosition(0) * SENSOR_UNITS;
    	SmartDashboard.putNumber("Right Encoder Rotation", rotations);
    	return rotations;
    }
    
    public double getLeftEncoderRotation() {
    	double rotations = rearLeft.getSelectedSensorPosition(0) * SENSOR_UNITS;
    	SmartDashboard.putNumber("Left Encoder Rotation", rotations);
    	return rotations;
    }
    
    public double getGyroAngle() {
    	double angle = gyro.getAngle();
    	SmartDashboard.putNumber("Gyro Angle", angle);
    	return angle;
    }
    
    
    public void gyroReset(){
		gyro.reset();
	}
    
    public void setClosedloopRamp(double timeToRamp) {
    	frontLeft.configClosedloopRamp(timeToRamp, 0);
    	rearLeft.configClosedloopRamp(timeToRamp, 0);
    	frontRight.configClosedloopRamp(timeToRamp, 0);
    	rearRight.configClosedloopRamp(timeToRamp, 0);
    }
    
    public void setOpenloopRampRate(double rampRate) {
    	frontLeft.configOpenloopRamp(rampRate, 0);
    	rearLeft.configOpenloopRamp(rampRate, 0);
    	frontRight.configOpenloopRamp(rampRate, 0);
    	rearRight.configOpenloopRamp(rampRate, 0);
    }
    
    public void enableCurrentLimit() {
    	frontLeft.enableCurrentLimit(true);
    	rearLeft.enableCurrentLimit(true);
    	frontRight.enableCurrentLimit(true);
    	rearRight.enableCurrentLimit(true);
    }
    
    public void shiftGearIn() {
    	gearShift.set(Value.kForward);
    }
    
    public void shiftGearOut() {
    	gearShift.set(Value.kReverse);
    }
    
    public void shiftGearOff() {
    	gearShift.set(Value.kOff);
    }
    
}


