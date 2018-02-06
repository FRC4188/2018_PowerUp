/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import org.usfirst.frc.team4188.robot.commandgroups.AutonomousTesting;
import org.usfirst.frc.team4188.robot.subsystems.Climber;
import org.usfirst.frc.team4188.robot.subsystems.Elevator;
import org.usfirst.frc.team4188.robot.subsystems.Intake;
import org.usfirst.frc.team4188.robot.subsystems.JevoisCamera;
import org.usfirst.frc.team4188.robot.subsystems.PIDDriveTrain;
import org.usfirst.frc.team4188.robot.subsystems.PIDDriveTrain.PIDInput;

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
	
	public static OI m_oi;
	public static PIDDriveTrain m_pidDriveTrain;
	public static JevoisCamera m_jevoisCamera;
	public static Elevator m_elevator;
	public static Climber m_climber;
	public static Intake m_intake;
	
	

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		m_pidDriveTrain = new PIDDriveTrain();
		m_oi = new OI();
		m_pidDriveTrain.setPIDInput(PIDInput.none);
		RobotMap.gyro.calibrate();
		m_chooser.addObject("Test Auto", new AutonomousTesting());
		
		
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		m_elevator = new Elevator();
		m_jevoisCamera = new JevoisCamera();
		m_climber = new Climber();
		m_intake = new Intake();
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
		//String frcNonsense = DriverStation.getInstance().getGameSpecificMessage();
		String frcNonsense = "RLR";
		char mySwitch = frcNonsense.charAt(0);
		char scale = frcNonsense.charAt(1);
		char theirSwitch = frcNonsense.charAt(2);
		
		m_autonomousCommand = m_chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		
		// schedule the autonomous command (example)
		Robot.m_pidDriveTrain.gyroReset();
		Robot.m_pidDriveTrain.resetEncoders();
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
		Robot.m_pidDriveTrain.resetEncoders();
		Robot.m_pidDriveTrain.gyroReset();
		Robot.m_pidDriveTrain.setClosedloopRamp(0.1);
		//Robot.m_pidDriveTrain.enableCurrentLimit();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	int counter = 0;
	@Override
	public void teleopPeriodic() {
		
		
		
		Scheduler.getInstance().run();
		SmartDashboard.putData(Robot.m_pidDriveTrain.getPIDController());
		SmartDashboard.putData("Drive Train", Robot.m_pidDriveTrain);
		SmartDashboard.putData(RobotMap.gyro);
		SmartDashboard.putNumber("Velocity", RobotMap.rearRight.getSelectedSensorVelocity(0)*Robot.m_pidDriveTrain.SENSOR_UNITS_PER_ROTATION);
		Robot.m_pidDriveTrain.getRightEncoderRotation();
		Robot.m_pidDriveTrain.getLeftEncoderRotation();
		SmartDashboard.putData(RobotMap.pdp);
		if(counter++%100 ==0) {
			m_jevoisCamera.setHSV((int) SmartDashboard.getNumber("hMin", m_jevoisCamera.H_MIN),
				(int) SmartDashboard.getNumber("hMax", m_jevoisCamera.H_MAX),
				(int) SmartDashboard.getNumber("sMin", m_jevoisCamera.S_MIN),
				(int) SmartDashboard.getNumber("sMax", m_jevoisCamera.S_MAX),
				(int) SmartDashboard.getNumber("vMin", m_jevoisCamera.V_MIN),
				(int) SmartDashboard.getNumber("vMax", m_jevoisCamera.V_MAX));
		}
		SmartDashboard.putNumber("Ultrasonic Sensor", RobotMap.ultrasonic.getRangeInches());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	
		SmartDashboard.putData("Drive Train", Robot.m_pidDriveTrain);
	
	}
}
