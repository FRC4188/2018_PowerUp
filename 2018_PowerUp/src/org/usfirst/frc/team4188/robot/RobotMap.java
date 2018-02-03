/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static WPI_TalonSRX frontLeft; 
	public static WPI_TalonSRX rearLeft; 
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX rearRight;  
	
	public static SpeedControllerGroup leftSet; 
	public static SpeedControllerGroup rightSet; 
	
	public static CSPRobotDrive driveTrain;
	
	public static DoubleSolenoid gearShift;
	
	public static ADXRS450_Gyro gyro;
	
	public static WPI_TalonSRX innerElevator;
	
	public static PowerDistributionPanel pdp;
	
	public static void init() {
		frontLeft = new WPI_TalonSRX(4);
		rearLeft = new WPI_TalonSRX(6);
		frontRight = new WPI_TalonSRX(7);
		rearRight = new WPI_TalonSRX(8);
		
		rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
		rearLeft.setSensorPhase(false);
		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rearRight.setSensorPhase(false);
		
		leftSet = new SpeedControllerGroup(frontLeft, rearLeft);
		rightSet = new SpeedControllerGroup(frontRight, rearRight);
		
		driveTrain = new CSPRobotDrive(leftSet, rightSet);
		driveTrain.setSafetyEnabled(false);
		driveTrain.setExpiration(0.1);
		driveTrain.setSensitivity(0.5);
		driveTrain.setMaxOutput(1.0);
		
		gyro = new ADXRS450_Gyro();
		
		innerElevator = new WPI_TalonSRX(1);
		
		gearShift = new DoubleSolenoid(0,1);
		pdp = new PowerDistributionPanel();
		
	}

}
