/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import org.usfirst.frc.team4188.robot.RobotMap.RobotType;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousDoNothing;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousLeftScale;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousLeftSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMiddleFrontSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMiddleScale;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMiddleSideSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMoveForward;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousRightScale;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousRightSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousTesting;
import org.usfirst.frc.team4188.robot.subsystems.Climber;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import org.usfirst.frc.team4188.robot.subsystems.Elevator;
import org.usfirst.frc.team4188.robot.subsystems.Intake;
import org.usfirst.frc.team4188.robot.subsystems.JevoisCamera;
import org.usfirst.frc.team4188.robot.subsystems.Wings;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static RobotType m_name = RobotMap.RobotType.SCRAPPY;
	
	public static double ROBOT_LENGTH;
	public static double ROBOT_WIDTH;
	
	public static OI m_oi;
	public static Drivetrain m_drivetrain;
	public static JevoisCamera m_jevoisCamera;
	public static Elevator m_elevator;
	public static Climber m_climber;
	public static Intake m_intake;
	public static Wings m_wings;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	String gameMessage = "";

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		switch(m_name) {
		case SCRAPPY:
			ROBOT_LENGTH = 39.0/12.0;
			ROBOT_WIDTH = 25.0/12.0;
			break;
		case BREAKOUT:
			ROBOT_LENGTH = 39.0/12.0;
			ROBOT_WIDTH = 25.0/12.0;
			break;
		}
		RobotMap.init();
		m_drivetrain = new Drivetrain();
		m_oi = new OI();
		m_drivetrain.setPIDInput(PIDInput.none);
		RobotMap.gyro.calibrate();
		
		m_chooser.setName("Autonomous");
		m_chooser.addObject("Test Auto", new AutonomousTesting());
		m_chooser.addObject("Start Left End Switch", new AutonomousLeftSwitch());
		m_chooser.addObject("Start Left End Scale", new AutonomousLeftScale());
		m_chooser.addObject("Start Right End Switch", new AutonomousRightSwitch());
		m_chooser.addObject("Start Right End Scale", new AutonomousRightScale());
		m_chooser.addObject("Start Middle End Front Switch", new AutonomousMiddleFrontSwitch());
		m_chooser.addObject("Start Middle End Side Switch", new AutonomousMiddleSideSwitch());
		m_chooser.addObject("Start Middle End Scale", new AutonomousMiddleScale());
		m_chooser.addObject("Start Anywhere Move Forward", new AutonomousMoveForward());
		m_chooser.addDefault("Do nothing", new AutonomousDoNothing());
		
		SmartDashboard.putData("Auto mode", m_chooser);
		m_elevator = new Elevator();
		m_jevoisCamera = new JevoisCamera();
		m_climber = new Climber();
		m_intake = new Intake();
		m_wings = new Wings();
		RobotMap.ultrasonic.setAutomaticMode(true);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		gameMessage = DriverStation.getInstance().getGameSpecificMessage();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		gameMessage = "LLL";
		
		m_autonomousCommand = (Command)m_chooser.getSelected();
		
		try {
			if(m_autonomousCommand.getClass() != AutonomousMoveForward.class) {
				m_autonomousCommand = (Command)m_autonomousCommand.getClass().getConstructor(String.class).newInstance(gameMessage);
			}
		} catch(Exception e) {
			m_autonomousCommand = new AutonomousMoveForward();
		}
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		// schedule the autonomous command (example)
		Robot.m_drivetrain.gyroReset();
		Robot.m_drivetrain.resetEncoders();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot.m_drivetrain.resetEncoders();
		Robot.m_drivetrain.gyroReset();
		Robot.m_drivetrain.setClosedloopRamp(0.1);
		//Robot.m_drivetrain.enableCurrentLimit();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	int counter = 0;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData("Drive Train", Robot.m_drivetrain);
		SmartDashboard.putData(RobotMap.gyro);
		SmartDashboard.putNumber("Velocity", RobotMap.rearRight.getSelectedSensorVelocity(0)*Robot.m_drivetrain.SENSOR_UNITS_PER_ROTATION);
		Robot.m_drivetrain.getRightEncoderRotation();
		Robot.m_drivetrain.getLeftEncoderRotation();
		SmartDashboard.putData(RobotMap.pdp);
		/*
		if(counter++%100 ==0) {
			m_jevoisCamera.setHSV((int) SmartDashboard.getNumber("hMin", m_jevoisCamera.H_MIN),
				(int) SmartDashboard.getNumber("hMax", m_jevoisCamera.H_MAX),
				(int) SmartDashboard.getNumber("sMin", m_jevoisCamera.S_MIN),
				(int) SmartDashboard.getNumber("sMax", m_jevoisCamera.S_MAX),
				(int) SmartDashboard.getNumber("vMin", m_jevoisCamera.V_MIN),
				(int) SmartDashboard.getNumber("vMax", m_jevoisCamera.V_MAX));
		}
		*/
		SmartDashboard.putNumber("Ultrasonic Sensor", RobotMap.ultrasonic.getRangeInches());
		Robot.m_drivetrain.setClosedloopRamp(10);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		SmartDashboard.putData("Drive Train", Robot.m_drivetrain);
		SmartDashboard.putData(Robot.m_drivetrain.getPIDController());
	}
	
}
