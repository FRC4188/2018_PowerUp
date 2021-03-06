/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team4188.robot.RobotMap.RobotType;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeAutoClose;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousDoNothing;
import org.usfirst.frc.team4188.robot.commandgroups.AutonomousMoveForward;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingLeftDouble;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingLeftDoubleWithSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingRightDouble;
import org.usfirst.frc.team4188.robot.commandgroups.left.scale.AutonomousLeftScaleGoingRightDoubleWithSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch.AutonomousLeftSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.left.sideswitch.AutonomousLeftSwitchGoingLeftDouble;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingLeftDouble;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.middle.frontswitch.AutonomousMiddleFrontSwitchGoingRightDouble;
import org.usfirst.frc.team4188.robot.commandgroups.middle.scale.AutonomousMiddleScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.scale.AutonomousMiddleScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.middle.sideswitch.AutonomousMiddleSideSwitchGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.middle.sideswitch.AutonomousMiddleSideSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingLeft;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingLeftDouble;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingLeftDoubleWithSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingRightDouble;
import org.usfirst.frc.team4188.robot.commandgroups.right.scale.AutonomousRightScaleGoingRightDoubleWithSwitch;
import org.usfirst.frc.team4188.robot.commandgroups.right.sideswitch.AutonomousRightSwitchGoingRight;
import org.usfirst.frc.team4188.robot.commandgroups.right.sideswitch.AutonomousRightSwitchGoingRightDouble;
import org.usfirst.frc.team4188.robot.subsystems.Climber;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain;
import org.usfirst.frc.team4188.robot.subsystems.Drivetrain.PIDInput;
import org.usfirst.frc.team4188.robot.subsystems.Elevator;
import org.usfirst.frc.team4188.robot.subsystems.Intake;
import org.usfirst.frc.team4188.robot.subsystems.JevoisCamera;
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
	
	public enum PowerState{NORMAL, POWERCONSERVING}
	public static PowerState powerState = PowerState.NORMAL; 
	
	public enum InnerElevatorStatus{GOOD, BROKEN};
	public static InnerElevatorStatus innerElevatorStatus = InnerElevatorStatus.GOOD;
	
	public static double ROBOT_LENGTH;
	public static double ROBOT_WIDTH;

	public static final double MAX_VELOCITY = 2400;
	public static final double UNITS_PER_ROTATION = 4096;
	
	public static double INNER_ELEVATOR_FLAT_POWER;
	public static double OUTER_ELEVATOR_FLAT_POWER;
	
	public static OI m_oi;
	public static Drivetrain m_drivetrain;
	public static JevoisCamera m_jevoisCamera;
	public static Elevator m_elevator;
	public static Climber m_climber;
	public static Intake m_intake;
	
	Command m_autonomousCommand;
	int m_selectedCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();
	public static String gameMessage = "NNN";
	
	public static char switchSide = gameMessage.charAt(0);
	public static char scaleSide = gameMessage.charAt(1);
	public static char enemySwitchSide = gameMessage.charAt(2);
	
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
		//RobotMap.gyro.calibrate();
		
		
		m_chooser.setName("REAL ONE");
		m_chooser.addObject("Start Left End Switch", 0);
		m_chooser.addObject("Start Left End Scale", 1);
		m_chooser.addObject("Start Middle End Front Switch", 2);
		//m_chooser.addObject("Start Middle End Side Switch", 3);
		//m_chooser.addObject("Start Middle End Scale", 4);
		m_chooser.addObject("Start Right End Switch", 5);
		m_chooser.addObject("Start Right End Scale", 6);
		m_chooser.addDefault("Start Anywhere Move Forward", 7);
		m_chooser.addObject("Do Nothing", 8);
		//m_chooser.addObject("Start Left Switch Priority", 9);
		//m_chooser.addObject("Start Right Switch Priority", 10);
		m_chooser.addObject("Start Left Scale Priority",  11);	
		m_chooser.addObject("Start Right Scale Priority", 12);
		m_chooser.addObject("Start Middle End Front Switch Double", 13);
		m_chooser.addObject("Start Left End Scale Double", 14);
		m_chooser.addObject("Start Right End Scale Double", 15);
		m_chooser.addObject("Start Left Double Switch Priority", 16);
		m_chooser.addObject("Start Right Double Switch Priority", 17);
		
		SmartDashboard.putData("FAKE", m_chooser);
		
		CameraServer.getInstance().startAutomaticCapture();
		
		m_elevator = new Elevator();
		//m_jevoisCamera = new JevoisCamera();
		m_climber = new Climber();
		m_intake = new Intake();
		m_oi = new OI();
		RobotMap.ultrasonic.setAutomaticMode(true);
		
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
		RobotMap.gyro.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putData(RobotMap.gyro);
		gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		m_selectedCommand = (int) m_chooser.getSelected();
		System.out.println((int) m_chooser.getSelected());
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
		
		// set all motors to brake
		Robot.m_drivetrain.setBrake();
		
		gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		
		m_selectedCommand = (int) m_chooser.getSelected();
		
		System.out.println(m_selectedCommand);
		
		char switchSide = gameMessage.charAt(0);
		char scaleSide = gameMessage.charAt(1);
		char enemySwitchSide = gameMessage.charAt(2);
		
		switch(m_selectedCommand) {
			case 0: //start left end switch
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftSwitchGoingLeft();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 1: //start left end scale
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
			case 2: //start middle end front switch
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
			case 3: //start middle end side switch
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
			case 4: //start middle end scale
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
			case 5: //start right end switch
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 6: //start right end scale
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
			case 7: //move forward
				m_autonomousCommand = new AutonomousMoveForward();
				break;
			case 8: //do nothing
				m_autonomousCommand = new AutonomousDoNothing();
				break;
			case 9: //start left switch priority, scale if switch not available
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftSwitchGoingLeft();
					break;
				case 'R':
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
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 10: //start right switch priority, scale if switch not available
				switch(switchSide) {
				case 'L':
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
				case 'R':
					m_autonomousCommand = new AutonomousRightSwitchGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 11: //start left scale priority, switch if scale not available
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftScaleGoingLeft();
					break;
				case 'R':
					switch(switchSide) {
					case 'L':
						m_autonomousCommand = new AutonomousLeftSwitchGoingLeft();
						break;
					case 'R':
						m_autonomousCommand = new AutonomousMoveForward();
						break;
					default:
						m_autonomousCommand = new AutonomousMoveForward();
					}
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 12: //start right scale priority, switch if scale not available
				switch(scaleSide) {
				case 'L':
					switch(switchSide) {
					case 'L':
						m_autonomousCommand = new AutonomousMoveForward();
						break;
					case 'R':
						m_autonomousCommand = new AutonomousRightSwitchGoingRight();
						break;
					default:
						m_autonomousCommand = new AutonomousMoveForward();
					}
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightScaleGoingRight();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			default:
				m_autonomousCommand = new AutonomousMoveForward();
				break;
			case 13: //start middle end front switch double
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousMiddleFrontSwitchGoingLeftDouble();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousMiddleFrontSwitchGoingRightDouble();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 14: // start left end scale double
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftScaleGoingLeftDouble();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousLeftScaleGoingRightDouble();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 15: // start right end scale double
				switch(scaleSide) {
				case 'L':
					m_autonomousCommand = new AutonomousRightScaleGoingLeftDouble();
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightScaleGoingRightDouble();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 16: //start left double switch priority, double scale if switch not available
				switch(switchSide) {
				case 'L':
					m_autonomousCommand = new AutonomousLeftSwitchGoingLeftDouble();
					break;
				case 'R':
					switch(scaleSide) {
					case 'L':
						m_autonomousCommand = new AutonomousLeftScaleGoingLeftDouble();
						break;
					case 'R':
						m_autonomousCommand = new AutonomousLeftScaleGoingRightDouble();
						break;
					default:
						m_autonomousCommand = new AutonomousMoveForward();
						break;						
					}
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
				break;
			case 17: //start right double switch priority, double scale if switch not available
				switch(switchSide) {
				case 'L':
					switch(scaleSide) {
					case 'L':
						m_autonomousCommand = new AutonomousRightScaleGoingLeftDouble();
						break;
					case 'R':
						m_autonomousCommand = new AutonomousRightScaleGoingRightDouble();
						break;
					default:
						m_autonomousCommand = new AutonomousMoveForward();
						break;						
					}
					break;
				case 'R':
					m_autonomousCommand = new AutonomousRightSwitchGoingRightDouble();
					break;
				default:
					m_autonomousCommand = new AutonomousMoveForward();
					break;
				}
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
		
		m_elevator.newElevatorRun(0.06);
		m_intake.runIntakeRelease(-.05);
		Scheduler.getInstance().run();
	}

	public double kF = MAX_VELOCITY / 1023;
	public double kP = 0;
	public double kI = 0;
	public double kD = 0;
	@Override
	public void teleopInit() { 
		// This makes sure that the autonomous stops running when 
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		Robot.m_drivetrain.resetEncoders();
		Robot.m_drivetrain.gyroReset(); // hahahahahahahhahahaha 420
		Robot.m_drivetrain.setClosedloopRamp(0.1);
		Robot.m_elevator.resetEncoders();
		Robot.m_drivetrain.setCoast();
		//Robot.m_drivetrain.enableCurrentLimit();

		/*
		RobotMap.rearRight.config_kF(0, kF, 30);
		RobotMap.rearRight.config_kP(0, kP, 30);
		RobotMap.rearRight.config_kI(0, kI, 30);
		RobotMap.rearRight.config_kD(0, kD, 30);

		RobotMap.rearLeft.config_kF(0, kF, 30);
		RobotMap.rearLeft.config_kP(0, kP, 30);
		RobotMap.rearLeft.config_kI(0, kI, 30);
		RobotMap.rearLeft.config_kD(0, kD, 30);
		*/
		
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
		Robot.m_drivetrain.getLeftEncoderRotation();
		SmartDashboard.putData(RobotMap.pdp);
		SmartDashboard.putNumber("Ultrasonic Sensor", RobotMap.ultrasonic.getRangeInches());
		Robot.m_drivetrain.setOpenloopRampRate(0.2);
		SmartDashboard.putBoolean("Magnetic limit switch", RobotMap.magneticSwitch.get());
		SmartDashboard.putNumber("Right Speed", -RobotMap.rearRight.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Left Speed", RobotMap.rearLeft.getSelectedSensorVelocity(0));
		//Robot.m_drivetrain.enableCurrentLimit();
		// testing data
		
		//SmartDashboard.putNumber("Right Elevator Encoder", RobotMap.outerElevatorRight.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Elevator Up Power", Robot.m_oi.coPilotXboxController.getY(Hand.kLeft));
		SmartDashboard.putNumber("Inner Elevator Encoder", Math.abs(RobotMap.innerElevator.getSelectedSensorPosition(0) * INCHES_PER_UNIT));
		SmartDashboard.putNumber("Outer Encoder", Math.abs(RobotMap.outerElevatorRight.getSelectedSensorPosition(0) * INCHES_PER_UNIT));

		if(powerState == PowerState.NORMAL){
	        if(RobotMap.pdp.getVoltage() < 6.3) {
	        	Robot.m_drivetrain.conservePower(true);
	        	powerState = PowerState.POWERCONSERVING;
	        }
	    }
	    if(powerState == PowerState.POWERCONSERVING){
	        if(Math.abs(Robot.m_oi.pilotXboxController.getY(Hand.kLeft))<0.3){
	       		Robot.m_drivetrain.conservePower(false);
	        	powerState = PowerState.NORMAL;
	        }
	   }
/*
		double xSpeed = m_oi.pilotXboxController.getY(Hand.kLeft) * MAX_VELOCITY;
		double zTurn = m_oi.pilotXboxController.getX(Hand.kRight) * MAX_VELOCITY;
		double turnRatio;
		double leftInput = xSpeed - zTurn;
		double rightInput = xSpeed + zTurn;
		if(leftInput > 2420 || rightInput > 2420) {
			if(rightInput > leftInput) {
				turnRatio = leftInput / rightInput;
				rightInput = 2400;
				leftInput = 2400 * turnRatio;
			} else if (leftInput > rightInput) {
				turnRatio = rightInput / leftInput;
				leftInput = 2400;
				rightInput = 2400 * turnRatio;
			}
		} else if(leftInput < -2420 || rightInput < -2420) {
			if(rightInput < leftInput) {
				turnRatio = leftInput / rightInput;
				rightInput = -2400;
				leftInput = -2400 * turnRatio;
			} else if (leftInput < rightInput) {
				turnRatio = rightInput / leftInput;
				leftInput = -2400;
				rightInput = -2400 * turnRatio;
			}
		}
		RobotMap.rearRight.set(ControlMode.Velocity, rightInput);
		RobotMap.rearLeft.set(ControlMode.Velocity, leftInput);
		System.out.println("right: " + rightInput + " left: " + leftInput); 
*/
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		SmartDashboard.putData("Drive Train", Robot.m_drivetrain);
		SmartDashboard.putData(Robot.m_drivetrain.getPIDController());
		SmartDashboard.putBoolean("Outer Top", RobotMap.outerElevatorRight.getSensorCollection().isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean("Outer Bottom", RobotMap.outerElevatorRight.getSensorCollection().isRevLimitSwitchClosed());
		SmartDashboard.putBoolean("Inner Top", RobotMap.innerElevator.getSensorCollection().isFwdLimitSwitchClosed());
		SmartDashboard.putBoolean("Inner Bottom", RobotMap.innerElevator.getSensorCollection().isRevLimitSwitchClosed());
	}
	
}

