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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
	
	// constants for autonomous
	public static final double ROBOT_LENGTH = 32.0/12;
	public static final double ROBOT_WIDTH = 28.0/12;
	public static final double SWITCH_HEIGHT_ROTATIONS = 3.5;
	public static final double INTAKE_RELEASE_TIME = 0.2;
	
	public static WPI_TalonSRX frontLeft; 
	public static WPI_TalonSRX rearLeft; 
	public static WPI_TalonSRX frontRight;
	public static WPI_TalonSRX rearRight;  
	public static Ultrasonic ultrasonic;
	
	public static SpeedControllerGroup leftSet; 
	public static SpeedControllerGroup rightSet; 
	
	public static DifferentialDrive drivetrain;
	
	public static DoubleSolenoid gearShift;
	
	public static ADXRS450_Gyro gyro;
	
	public static WPI_TalonSRX leftElevator;
	public static WPI_TalonSRX rightElevator;
	public static WPI_TalonSRX leadScrew;
	
	public static PowerDistributionPanel pdp;
	
	public static DoubleSolenoid climberSolenoid1;
	public static DoubleSolenoid climberSolenoid2;
	public static DoubleSolenoid climberSolenoid3;
	
	public static WPI_TalonSRX intakeRight;
	public static WPI_TalonSRX intakeLeft;
	public static WPI_TalonSRX intakeRelease;

	public static DoubleSolenoid intakeSolenoid;
	

	
	public static void init() {
		frontLeft = new WPI_TalonSRX(4);
		rearLeft = new WPI_TalonSRX(6);
		frontRight = new WPI_TalonSRX(8);
		rearRight = new WPI_TalonSRX(7);
		
		rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
		rearLeft.setSensorPhase(true);
		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rearRight.setSensorPhase(true);
		
		leftSet = new SpeedControllerGroup(frontLeft, rearLeft);
		rightSet = new SpeedControllerGroup(frontRight, rearRight);
		
		drivetrain = new DifferentialDrive(leftSet, rightSet);
		drivetrain.setSafetyEnabled(false);
		drivetrain.setExpiration(0.1);
		drivetrain.setMaxOutput(1.0);
		
		gyro = new ADXRS450_Gyro();
		
		leftElevator = new WPI_TalonSRX(3);
		leftElevator.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftElevator.setSensorPhase(true);
		rightElevator = new WPI_TalonSRX(10);
		
		leadScrew = new WPI_TalonSRX(9);
		
		gearShift = new DoubleSolenoid(0,0,1);
		pdp = new PowerDistributionPanel();
		
		climberSolenoid1 = new DoubleSolenoid(0,2,3);
		
		intakeRight = new WPI_TalonSRX(2);
		intakeLeft = new WPI_TalonSRX(11);
		intakeRelease = new WPI_TalonSRX(1);
		
		intakeSolenoid = new DoubleSolenoid(0,4,5);
		
		ultrasonic = new Ultrasonic(0,1);
		
	}

}
