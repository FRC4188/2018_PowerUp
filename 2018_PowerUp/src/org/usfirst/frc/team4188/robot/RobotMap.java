/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
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
	
	public static WPI_TalonSRX outerElevatorLeft;
	public static WPI_TalonSRX outerElevatorRight;
	public static WPI_TalonSRX innerElevator;
	
	public static PowerDistributionPanel pdp;
	
	public static DoubleSolenoid climberSolenoid1;
	public static DoubleSolenoid climberSolenoid2;
	public static DoubleSolenoid climberSolenoid3;
	
	public static WPI_VictorSPX intakeRight;
	public static WPI_VictorSPX intakeLeft;
	public static WPI_VictorSPX intakeRelease;

	public static DoubleSolenoid intakeSolenoid;
	
	public static Servo leftWingFront;
	public static Servo leftWingBack;
	public static Servo rightWingFront;
	public static Servo rightWingBack;
	
	
	public static void init() {
		frontLeft = new WPI_TalonSRX(5);
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
		
		outerElevatorLeft = new WPI_TalonSRX(3);
		outerElevatorLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		outerElevatorLeft.setSensorPhase(true);
		outerElevatorRight = new WPI_TalonSRX(10);
		outerElevatorRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		outerElevatorRight.setSensorPhase(true);
		
		innerElevator = new WPI_TalonSRX(9);
		innerElevator.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		innerElevator.setSensorPhase(true);
		
		gearShift = new DoubleSolenoid(0,0,1);
		pdp = new PowerDistributionPanel();
		
		climberSolenoid1 = new DoubleSolenoid(0,2,3);
		
		intakeRight = new WPI_VictorSPX(12);
		intakeLeft = new WPI_VictorSPX(1);
		intakeRelease = new WPI_VictorSPX(11);
		
		intakeSolenoid = new DoubleSolenoid(0,4,5);
		
		ultrasonic = new Ultrasonic(0,1);
		
		leftWingFront = new Servo(6);
		leftWingBack = new Servo(7);
		rightWingFront = new Servo(8);
		rightWingBack = new Servo(9);
		
	}

}