/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4188.robot;

import org.usfirst.frc.team4188.robot.commands.climber.CylindersIn;
import org.usfirst.frc.team4188.robot.commands.climber.CylindersOff;
import org.usfirst.frc.team4188.robot.commands.climber.CylindersOut;
import org.usfirst.frc.team4188.robot.commands.climber.LeadScrewRun;
import org.usfirst.frc.team4188.robot.commands.climber.LeadScrewStop;
import org.usfirst.frc.team4188.robot.commands.drive.AutoDriveDistanceBased;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftIn;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftOff;
import org.usfirst.frc.team4188.robot.commands.drive.GearShiftOut;
import org.usfirst.frc.team4188.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorRun;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorStop;
import org.usfirst.frc.team4188.robot.commands.elevator.ElevatorToSwitch;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeIn;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsForward;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsReverse;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeMotorsStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeOut;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseDown;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseStop;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeReleaseUp;
import org.usfirst.frc.team4188.robot.commands.intake.IntakeSolenoidOff;
import org.usfirst.frc.team4188.robot.commands.wings.LowerWings;
import org.usfirst.frc.team4188.robot.commands.wings.RaiseWings;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public XboxController pilotXboxController;
	public XboxController coPilotXboxController;
	
	public JoystickButton pilot1;
	public JoystickButton pilot2;
	public JoystickButton pilot3;
	public JoystickButton pilot4;
	public JoystickButton pilot5;
	public JoystickButton pilot6;
	public JoystickButton pilot7;
	public JoystickButton pilot8;
	public JoystickButton pilot9;
	public JoystickButton pilot10;
	public JoystickButton pilot11;
	public JoystickButton pilot12;
	
	public JoystickButton coPilot1;
	public JoystickButton coPilot2;
	public JoystickButton coPilot3;
	public JoystickButton coPilot4;
	public JoystickButton coPilot5;
	public JoystickButton coPilot6;
	public JoystickButton coPilot7;
	public JoystickButton coPilot8;
	public JoystickButton coPilot9;
	public JoystickButton coPilot10;
	public JoystickButton coPilot11;
	public JoystickButton coPilot12;
	
	public OI(){
	
	pilotXboxController = new XboxController(0);
	pilot1 = new JoystickButton(pilotXboxController, 1);
    pilot2 = new JoystickButton(pilotXboxController, 2);
    pilot3 = new JoystickButton(pilotXboxController, 3);
    pilot4 = new JoystickButton(pilotXboxController, 4);
    pilot5 = new JoystickButton(pilotXboxController, 5);
    pilot6 = new JoystickButton(pilotXboxController, 6);
    pilot7 = new JoystickButton(pilotXboxController, 7);
    pilot8 = new JoystickButton(pilotXboxController, 8);
    pilot9 = new JoystickButton(pilotXboxController, 9);
    pilot10 = new JoystickButton(pilotXboxController, 10);
    pilot11 = new JoystickButton(pilotXboxController, 11);
    pilot12 = new JoystickButton(pilotXboxController, 12);
    
	pilot1.whenPressed(new AutoDriveDistanceBased(5.0,0.1));
	SmartDashboard.putData("Distance Based PID", new AutoDriveDistanceBased(5.0,0.1));
	pilot2.whenPressed(new TurnToAngle(90,1.0));
	SmartDashboard.putData("Turn To Angle PID", new TurnToAngle(90,1.0));
	pilot3.whileHeld(new CylindersIn());
	pilot3.whenReleased(new CylindersOff());
	pilot4.whileHeld(new CylindersOut());
	pilot4.whenReleased(new CylindersOff());
	
	
	pilot7.whenPressed(new LowerWings());
	pilot8.whenPressed(new RaiseWings());
	
	 
	pilot9.whileHeld(new GearShiftIn());
    pilot9.whenReleased(new GearShiftOff());
    pilot10.whileHeld(new GearShiftOut());
    pilot10.whenReleased(new GearShiftOff());
	
	coPilotXboxController = new XboxController(1);
	coPilot1 = new JoystickButton(coPilotXboxController, 1);
	coPilot2 = new JoystickButton(coPilotXboxController, 2);
	coPilot3 = new JoystickButton(coPilotXboxController, 3);
	coPilot4 = new JoystickButton(coPilotXboxController, 4);
	coPilot5 = new JoystickButton(coPilotXboxController, 5);
	coPilot6 = new JoystickButton(coPilotXboxController, 6);
	coPilot7 = new JoystickButton(coPilotXboxController, 7);
	coPilot8 = new JoystickButton(coPilotXboxController, 8);
	coPilot9 = new JoystickButton(coPilotXboxController, 9);
	coPilot10 = new JoystickButton(coPilotXboxController, 10);
	coPilot11 = new JoystickButton(coPilotXboxController, 11);
	coPilot12 = new JoystickButton(coPilotXboxController, 12);
	
	coPilot6.whileHeld(new ElevatorRun());
	coPilot6.whenReleased(new ElevatorStop());
	
	coPilot1.whileHeld(new IntakeMotorsReverse());
	coPilot1.whenReleased(new IntakeMotorsStop());
	coPilot2.whileHeld(new IntakeMotorsForward());
	coPilot2.whenReleased(new IntakeMotorsStop());
	
	coPilot3.whileHeld(new IntakeIn());
	coPilot3.whenReleased(new IntakeSolenoidOff());
	coPilot4.whileHeld(new IntakeOut());
	coPilot4.whenReleased(new IntakeSolenoidOff());
	
	coPilot5.whileHeld(new LeadScrewRun());
	coPilot5.whenReleased(new LeadScrewStop());
	
	coPilot7.whileHeld(new IntakeReleaseDown());
	coPilot7.whenReleased(new IntakeReleaseStop());
	coPilot8.whenPressed(new IntakeReleaseUp());
	coPilot8.whenReleased(new IntakeReleaseStop());
	
	//coPilot9.whenPressed(new ElevatorToSwitch(2.0, .1));
	
	}

}
