/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import org.usfirst.frc.team4188.robot.RobotMap.RobotType;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousDoNothing;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMoveForward;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch.AutonomousLeftSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch.AutonomousLeftSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.middle.scale.AutonomousMiddleScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.scale.AutonomousMiddleScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.middle.sideswitch.AutonomousMiddleSideSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.sideswitch.AutonomousMiddleSideSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.right.sideswitch.AutonomousRightSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.right.sideswitch.AutonomousRightSwitchGoingRight;
import org.usfirst.frc.team4188.robot.subsystems.Climber;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import org.usfirst.frc.team4188.robot.subsystems.Elevator;
import org.usfirst.frc.team4188.robot.subsystems.Intake;
import org.usfirst.frc.team4188.robot.subsystems.JevoisCamera;
import org.usfirst.frc.team4188.robot.subsystems.Wings;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
	
	public static RobotType m_name = RobotMap.RobotType.BREAKOUT;
	
	public static double ROBOT_LENGTH;
	public static double ROBOT_WIDTH;
	
	public static double INNER_ELEVATOR_FLAT_POWER;
	public static double OUTER_ELEVATOR_FLAT_POWER;
	
	public static OI m_oi;
	public static Drivetrain m_drivetrain;
	public static JevoisCamera m_jevoisCamera;
	public static Elevator m_elevator;
	public static Climber m_climber;
	public static Intake m_intake;
	public static Wings m_wings;
	
	Command m_autonomousCommand;
	int m_selectedCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();
	String gameMessage = "NNN";

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
			INNER_ELEVATOR_FLAT_POWER = 0.05;
			OUTER_ELEVATOR_FLAT_POWER = -0.075;
			break;
		case BREAKOUT:
			ROBOT_LENGTH = 39.0/12.0;     
			ROBOT_WIDTH = 25.0/12.0;
			INNER_ELEVATOR_FLAT_POWER = 0.05;
			OUTER_ELEVATOR_FLAT_POWER = -0.075;
			break;
		}
		RobotMap.init();
		m_drivetrain = new Drivetrain();
		m_drivetrain.setPIDInput(PIDInput.none);
		RobotMap.gyro.calibrate();
		
		
		m_chooser.setName("Autonomous Selector");
		m_chooser.addObject("Start Left End Switch", 0);
		m_chooser.addObject("Start Left End Scale", 1);
		m_chooser.addObject("Start Right End Switch", 2);
		m_chooser.addObject("Start Right End Scale", 3);
		m_chooser.addDefault("Start Middle End Front Switch", 4);
		m_chooser.addObject("Start Middle End Side Switch", 5);
		m_chooser.addObject("Start Middle End Scale", 6);
		m_chooser.addObject("Start Anywhere Move Forward", 7);
		m_chooser.addObject("Do Nothing", 8);
		
		SmartDashboard.putData("Autonomous Selector", m_chooser);
		
		m_elevator = new Elevator();
		m_jevoisCamera = new JevoisCamera();
		m_climber = new Climber();
		m_intake = new Intake();
		m_wings = new Wings();
		m_oi = new OI();
		RobotMap.ultrasonic.setAutomaticMode(true);
		
		CameraServer.getInstance().startAutomaticCapture();
		m_selectedCommand = (int) m_chooser.getSelected();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		m_selectedCommand = (int) m_chooser.getSelected();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		m_selectedCommand = (int) m_chooser.getSelected();
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
		// TODO REMOVE
		gameMessage = "LLL";
		m_selectedCommand = (int) m_chooser.getSelected();
		
		System.out.println(m_selectedCommand);
		
		char switchSide = gameMessage.charAt(0);
		char scaleSide = gameMessage.charAt(1);
		
		switch(m_selectedCommand) {
			case 0:
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftSwitchGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousLeftSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 1:
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftScaleGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousLeftScaleGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 2:
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousMiddleFrontSwitchGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousMiddleFrontSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 3:
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousMiddleSideSwitchGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousMiddleSideSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 4:
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousMiddleScaleGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousMiddleScaleGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 5:
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousRightSwitchGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 6:
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousRightScaleGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightScaleGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 7:
				m_autonomousCommand = new AutonomousMoveForward();
				break;
			case 8:
				m_autonomousCommand = new AutonomousDoNothing();
				break;
			default:
				m_autonomousCommand = new AutonomousMoveForward();
				break;
	}
		
		
		
		// schedule the autonomous command (example)
		Robot.m_drivetrain.gyroReset();
		Robot.m_drivetrain.resetEncoders();
		//if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		//}
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
		Robot.m_elevator.resetEncoders();
		//Robot.m_drivetrain.enableCurrentLimit();
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	private final double SENSOR_UNITS = 1.0/4096.0;
	private final double INCHES_PER_ROTATION = 1.375*Math.PI;
	private final double INCHES_PER_UNIT = SENSOR_UNITS * INCHES_PER_ROTATION;
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run(); 
		SmartDashboard.putData("Drive Train", Robot.m_drivetrain);
		SmartDashboard.putData(RobotMap.gyro);
		SmartDashboard.putNumber("Velocity", RobotMap.rearRight.getSelectedSensorVelocity(0)*Robot.m_drivetrain.SENSOR_UNITS_PER_ROTATION);
		Robot.m_drivetrain.getRightEncoderRotation();
		Robot.m_drivetrain.getLeftEncoderRotation();
		SmartDashboard.putData(RobotMap.pdp);
		SmartDashboard.putNumber("Ultrasonic Sensor", RobotMap.ultrasonic.getRangeInches());
		Robot.m_drivetrain.setClosedloopRamp(10);
		// testing data
		
		SmartDashboard.putNumber("Elevator Up Power", Robot.m_oi.coPilotXboxController.getY(Hand.kLeft));
		SmartDashboard.putNumber("Inner Elevator Encoder", RobotMap.innerElevator.getSelectedSensorPosition(0) * INCHES_PER_UNIT);
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
