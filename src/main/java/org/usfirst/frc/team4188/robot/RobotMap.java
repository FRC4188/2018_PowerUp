/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
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
	
	public enum RobotType{
		SCRAPPY,
		BREAKOUT
	}
	
	// constants for autonomous
	public static final double SWITCH_HEIGHT = 24.0;
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
	public static WPI_TalonSRX intakeReleaseLeft;
	//public static WPI_TalonSRX intakeReleaseRight;
	public static WPI_VictorSPX intakeReleaseRight;

	public static DoubleSolenoid intakeSolenoid;
	
	public static WPI_TalonSRX climberMotor;
	
	public static Servo leftWingFront;
	public static Servo leftWingBack;
	public static Servo rightWingFront;
	public static Servo rightWingBack;
	
	public static DigitalInput intakeReleaseTopLimit;
	public static DigitalInput intakeReleaseBottomLimit;
	public static DigitalInput magneticSwitch;
	
	public static double brownoutMultiplier, climbVoltageCutoff;
	
	public static void init() {
		
		/* current motor mappings
		1: intake wheel left (Victor)
		2: intake release left
		3: outer elevator left
		4: intake release right (temp climber)
		5: front left drive
		6: rear left drive
		7: rear right drive
		8: front right drive
		9: inner elevator
		10: outer elevator right
		11: intake release right (Victor)
		12: intake wheel right (Victor)
		*/
		
		frontLeft = new WPI_TalonSRX(5);
		rearLeft = new WPI_TalonSRX(6);
		frontRight = new WPI_TalonSRX(8);
		rearRight = new WPI_TalonSRX(7);
		
		rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
		rearLeft.setSensorPhase(true);
		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rearRight.setSensorPhase(true);
		//rearRight.setInverted(true);
		
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
		outerElevatorLeft.overrideLimitSwitchesEnable(true);
		outerElevatorLeft.configForwardLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen, 10, 10);
		outerElevatorLeft.configReverseLimitSwitchSource(RemoteLimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen, 10, 10);
		
		outerElevatorRight = new WPI_TalonSRX(10);
		outerElevatorRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		outerElevatorRight.setSensorPhase(true);
		outerElevatorRight.overrideLimitSwitchesEnable(true);
		outerElevatorRight.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		outerElevatorRight.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
    	outerElevatorRight.setInverted(false);
    	outerElevatorRight.follow(outerElevatorLeft);
		
		innerElevator = new WPI_TalonSRX(9);
		innerElevator.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		innerElevator.setSensorPhase(true);
		innerElevator.overrideLimitSwitchesEnable(true);
		innerElevator.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		innerElevator.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 10);
		
		gearShift = new DoubleSolenoid(20,0,1);
		pdp = new PowerDistributionPanel();
		
		climberSolenoid1 = new DoubleSolenoid(20,2,3);
		
		intakeLeft = new WPI_VictorSPX(1);
		intakeRight = new WPI_VictorSPX(12);
		intakeReleaseLeft = new WPI_TalonSRX(2);
		intakeReleaseLeft.setInverted(true);
		//intakeReleaseRight = new WPI_TalonSRX(4);
		intakeReleaseRight = new WPI_VictorSPX(11);
		intakeReleaseRight.setInverted(true);
		
		
		intakeSolenoid = new DoubleSolenoid(20,4,5);
		
		climberMotor = new WPI_TalonSRX(4);
		
		ultrasonic = new Ultrasonic(0,1);
		
		leftWingFront = new Servo(6);
		leftWingBack = new Servo(7);
		rightWingFront = new Servo(8); 
		rightWingBack = new Servo(9);
		
		magneticSwitch = new DigitalInput(2);
		
		brownoutMultiplier = 1.0;
		climbVoltageCutoff = 9.5;

		
	}

}